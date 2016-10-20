package ru.ecmephi.user.service.test;

import lombok.val;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.ecmephi.user.service.ws.UserModel;
import ru.ecmephi.user.service.ws.endpoint.UserWS;
import ru.ecmephi.user.service.ws.request.AdminProcedureRequest;
import ru.ecmephi.user.service.ws.request.CreateUserRequest;
import ru.ecmephi.user.service.ws.request.IdRequest;
import ru.ecmephi.user.service.ws.request.LoginRequest;
import ru.ecmephi.user.service.ws.response.ResponseCode;
import ru.ecmephi.user.service.ws.response.UserListResponse;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RunWith(Arquillian.class)
public class UserManagerTester {

    private static final Long ADMIN_ID = 1L;
    private static final String ADMIN_USERNAME = "admin";
    private static final String TEST_USERNAME = "test";
    private static final String ADMINISTRATOR_RIGHTS = "administrator";
    private static final String STANDARD_USER_RIGHTS = "standard user";
    private static final String USER_ROLE = "User";
    private static final String ADMIN_ROLE = "Administrator";

    @Inject
    private UserWS userService;

    @Inject
    private LogSimulator logSimulator;

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive javaArchive = ShrinkWrap.create(JavaArchive.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("persistence.xml")
                .addAsManifestResource("arquillian.xml")
                .addPackage("ru.ecmephi.user.service")
                .addPackage("ru.ecmephi.user.service.dao")
                .addPackage("ru.ecmephi.user.service.entity")
                .addPackage("ru.ecmephi.user.service.interceptor")
                .addPackage("ru.ecmephi.user.service.ws")
                .addPackage("ru.ecmephi.user.service.ws.method")
                .addPackage("ru.ecmephi.user.service.ws.request")
                .addPackage("ru.ecmephi.user.service.ws.response")
                .addPackage("ru.ecmephi.user.service.ws.endpoint")
                .addClass(LogSimulator.class);
        System.out.println(javaArchive.toString(true));
        return javaArchive;
    }

    @Before
    @After
    public void clearDB() {
        val getAllRequest = new IdRequest();
        getAllRequest.setId(ADMIN_ID);
        UserListResponse response = userService.getAllUsers(getAllRequest);
        List<UserModel> userList = response.getUserList();
        userList.forEach(user -> {
            AdminProcedureRequest deleteRequest = new AdminProcedureRequest();
            deleteRequest.setAdminId(ADMIN_ID);
            deleteRequest.setTargetId(user.getId());
            userService.delete(deleteRequest);
        });
        response = userService.getAllUsers(getAllRequest);
        userList = response.getUserList();
        Assert.assertTrue(userList.size() == 1);
        val clearLogRequest = new AdminProcedureRequest();
        clearLogRequest.setAdminId(ADMIN_ID);
        clearLogRequest.setTargetId(ADMIN_ID);
        val baseResponse = userService.clearHistory(clearLogRequest);
        Assert.assertTrue(baseResponse.getResponseCode() == ResponseCode.SUCCESS);
    }

    @Test
    public void changeAccessLevelToSuperUser() throws Exception {
        val createUserRequest = new CreateUserRequest();
        createUserRequest.setAdminId(ADMIN_ID);
        createUserRequest.setUsername(TEST_USERNAME);
        createUserRequest.setPassword(TEST_USERNAME);
        UserModel user = userService.create(createUserRequest).getUser();
        logSimulator.simulateCreation(TEST_USERNAME);
        val changeRightRequest = new AdminProcedureRequest();
        changeRightRequest.setAdminId(ADMIN_ID);
        changeRightRequest.setTargetId(user.getId());
        val response = userService.grantAdministratorAccessLevel(changeRightRequest);
        logSimulator.simulateRightsChange(ADMIN_USERNAME, ADMINISTRATOR_RIGHTS);
        user = getUpdatedUser(user.getId());
        val logRequest = new IdRequest();
        logRequest.setId(user != null ? user.getId() : null);
        val actualLog = userService.getLog(logRequest).getMessage();
        val expectedLog = logSimulator.getLog();
        Assert.assertEquals(expectedLog, actualLog);
        Assert.assertEquals(ADMIN_ROLE, user != null ? user.getRole() : null);
        Assert.assertTrue(response.getResponseCode() == ResponseCode.SUCCESS);
    }

    @Test
    public void changeAccessLevelToUser() throws Exception {
        val createUserRequest = new CreateUserRequest();
        createUserRequest.setAdminId(ADMIN_ID);
        createUserRequest.setUsername(TEST_USERNAME);
        createUserRequest.setPassword(TEST_USERNAME);
        UserModel user = userService.create(createUserRequest).getUser();
        logSimulator.simulateCreation(TEST_USERNAME);
        val changeRightRequest = new AdminProcedureRequest();
        changeRightRequest.setAdminId(ADMIN_ID);
        changeRightRequest.setTargetId(user.getId());
        userService.grantAdministratorAccessLevel(changeRightRequest);
        logSimulator.simulateRightsChange(ADMIN_USERNAME, ADMINISTRATOR_RIGHTS);
        val response = userService.grantStandardUserAccessLevel(changeRightRequest);
        logSimulator.simulateRightsChange(ADMIN_USERNAME, STANDARD_USER_RIGHTS);
        user = getUpdatedUser(user.getId());
        val logRequest = new IdRequest();
        logRequest.setId(user != null ? user.getId() : null);
        val actualLog = userService.getLog(logRequest).getMessage();
        val expectedLog = logSimulator.getLog();
        Assert.assertTrue(response.getResponseCode() == ResponseCode.SUCCESS);
        Assert.assertEquals(USER_ROLE, user != null ? user.getRole() : null);
        Assert.assertEquals(expectedLog, actualLog);
    }

    @Test
    public void clearLogHistory() throws Exception {
        val expectedLog = "";
        val loginRequest = new LoginRequest();
        loginRequest.setUsername(ADMIN_USERNAME);
        loginRequest.setPassword(ADMIN_USERNAME);
        userService.login(loginRequest);
        val clearLogRequest = new AdminProcedureRequest();
        clearLogRequest.setAdminId(ADMIN_ID);
        clearLogRequest.setTargetId(ADMIN_ID);
        userService.clearHistory(clearLogRequest);
        val getLogRequest = new IdRequest();
        getLogRequest.setId(ADMIN_ID);
        val response = userService.getLog(getLogRequest);
        val actualLog = response.getMessage();
        Assert.assertTrue(response.getResponseCode() == ResponseCode.SUCCESS);
        Assert.assertEquals(expectedLog, actualLog);
    }

    @Test
    public void complexLogTest() throws Exception {
        val createUserRequest = new CreateUserRequest();
        createUserRequest.setAdminId(ADMIN_ID);
        createUserRequest.setUsername(TEST_USERNAME);
        createUserRequest.setPassword(TEST_USERNAME);
        val user = userService.create(createUserRequest).getUser();
        logSimulator.simulateCreation(TEST_USERNAME);
        val adminProcedureRequest = new AdminProcedureRequest();
        adminProcedureRequest.setAdminId(ADMIN_ID);
        adminProcedureRequest.setTargetId(user.getId());
        userService.grantAdministratorAccessLevel(adminProcedureRequest);
        logSimulator.simulateRightsChange(ADMIN_USERNAME, ADMINISTRATOR_RIGHTS);
        val loginRequest = new LoginRequest();
        loginRequest.setUsername(TEST_USERNAME);
        loginRequest.setPassword(TEST_USERNAME);
        userService.login(loginRequest);
        logSimulator.simulateLogon();
        userService.login(loginRequest);
        logSimulator.simulateLogon();
        userService.grantStandardUserAccessLevel(adminProcedureRequest);
        logSimulator.simulateRightsChange(ADMIN_USERNAME, STANDARD_USER_RIGHTS);
        val getLogRequest = new IdRequest();
        getLogRequest.setId(user.getId());
        val expectedLog = logSimulator.getLog();
        val actualLog = userService.getLog(getLogRequest).getMessage();
        Assert.assertEquals(expectedLog, actualLog);
    }

    @Test
    public void createUser() throws Exception {
        val createUserRequest = new CreateUserRequest();
        createUserRequest.setAdminId(ADMIN_ID);
        createUserRequest.setUsername(TEST_USERNAME);
        createUserRequest.setPassword(TEST_USERNAME);
        val user = userService.create(createUserRequest).getUser();
        logSimulator.simulateCreation(TEST_USERNAME);
        val getLogRequest = new IdRequest();
        getLogRequest.setId(user.getId());
        val expectedLog = logSimulator.getLog();
        val actualLog = userService.getLog(getLogRequest).getMessage();
        Assert.assertEquals(expectedLog, actualLog);
    }

    @Test
    public void deleteUser() throws Exception {
        val createUserRequest = new CreateUserRequest();
        createUserRequest.setAdminId(ADMIN_ID);
        createUserRequest.setUsername(TEST_USERNAME);
        createUserRequest.setPassword(TEST_USERNAME);
        val user = userService.create(createUserRequest).getUser();
        val deleteRequest = new AdminProcedureRequest();
        deleteRequest.setAdminId(ADMIN_ID);
        deleteRequest.setTargetId(user.getId());
        val response = userService.delete(deleteRequest);
        IdRequest getAllRequest = new IdRequest();
        getAllRequest.setId(ADMIN_ID);
        val userList = userService.getAllUsers(getAllRequest).getUserList();
        userList.stream().filter(userFromList -> matchUser(userFromList, user.getId())).forEach(userFromList -> Assert.fail());
        Assert.assertTrue(response.getResponseCode() == ResponseCode.SUCCESS);
    }

    @Test
    public void loginUser() throws Exception {
        val loginRequest = new LoginRequest();
        loginRequest.setUsername(ADMIN_USERNAME);
        loginRequest.setPassword(ADMIN_USERNAME);
        userService.login(loginRequest);
        logSimulator.simulateLogon();
        val getLogRequest = new IdRequest();
        getLogRequest.setId(ADMIN_ID);
        val expectedLog = logSimulator.getLog();
        val actualLog = userService.getLog(getLogRequest).getMessage();
        Assert.assertEquals(expectedLog, actualLog);
    }

    private boolean matchUser(UserModel userFromList, Long id) {
        return userFromList.getId().equals(id);
    }

    private UserModel getUpdatedUser(Long id) {
        val getAllRequest = new IdRequest();
        getAllRequest.setId(ADMIN_ID);
        val response = userService.getAllUsers(getAllRequest);
        val userList = response.getUserList();
        Optional<UserModel> first = userList.stream().filter(userModel -> matchUser(userModel, id)).findFirst();
        if (first.isPresent())
            return first.get();
        return null;
    }

}
