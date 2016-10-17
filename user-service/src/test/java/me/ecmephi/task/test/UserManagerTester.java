package me.ecmephi.task.test;

import lombok.val;
import me.ecmephi.task.common.UserModel;
import me.ecmephi.task.common.endpoint.UserWS;
import me.ecmephi.task.common.request.AdminProcedureRequest;
import me.ecmephi.task.common.request.CreateUserRequest;
import me.ecmephi.task.common.request.IdRequest;
import me.ecmephi.task.common.request.LoginRequest;
import me.ecmephi.task.common.response.ResponseCode;
import me.ecmephi.task.common.response.UserListResponse;
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

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RunWith(Arquillian.class)
public class UserManagerTester {

    private static final Long adminId = 1L;
    private static final String adminUsername = "admin";
    private static final String testUsername = "test";
    private static final String administratorRights = "administrator";
    private static final String nonAdministratorRights = "standard user";
    private static final String userRole = "User";
    private static final String adminRole = "Administrator";

    @Inject
    UserWS userService;

    @Inject
    LogSimulator logSimulator;

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive javaArchive = ShrinkWrap.create(JavaArchive.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("persistence.xml")
                .addAsManifestResource("arquillian.xml")
                .addAsResource("hibernate.cfg.xml")
                .addPackage("me.ecmephi.task.service")
                .addPackage("me.ecmephi.task.service.dao")
                .addPackage("me.ecmephi.task.service.entity")
                .addPackage("me.ecmephi.task.service.interceptor")
                .addPackage("me.ecmephi.task.common")
                .addPackage("me.ecmephi.task.common.method")
                .addPackage("me.ecmephi.task.common.request")
                .addPackage("me.ecmephi.task.common.response")
                .addPackage("me.ecmephi.task.common.endpoint")
                .addClass(LogSimulator.class);
        System.out.println(javaArchive.toString(true));
        return javaArchive;
    }

    @Before
    @After
    public void clearDB() {
        val getAllRequest = new IdRequest();
        getAllRequest.setId(adminId);
        UserListResponse response = userService.getAllUsers(getAllRequest);
        List<UserModel> userList = response.getUserList();
        userList.forEach(user->{
            AdminProcedureRequest deleteRequest = new AdminProcedureRequest();
            deleteRequest.setAdminId(adminId);
            deleteRequest.setTargetId(user.getId());
            userService.delete(deleteRequest);
        });
        response = userService.getAllUsers(getAllRequest);
        userList = response.getUserList();
        Assert.assertTrue(userList.size() == 1);
        val clearLogRequest = new AdminProcedureRequest();
        clearLogRequest.setAdminId(adminId);
        clearLogRequest.setTargetId(adminId);
        val baseResponse = userService.clearHistory(clearLogRequest);
        Assert.assertTrue(baseResponse.getResponseCode() == ResponseCode.SUCCESS);
    }

    @Test
    public void changeAccessLevelToSuperUser() throws Exception {
        val createUserRequest = new CreateUserRequest();
        createUserRequest.setAdminId(adminId);
        createUserRequest.setUsername(testUsername);
        createUserRequest.setPassword(testUsername);
        UserModel user = userService.create(createUserRequest).getUser();
        logSimulator.simulateCreation(testUsername);
        val changeRightRequest = new AdminProcedureRequest();
        changeRightRequest.setAdminId(adminId);
        changeRightRequest.setTargetId(user.getId());
        val response = userService.grantAdministratorAccessLevel(changeRightRequest);
        logSimulator.simulateRightsChange(adminUsername, administratorRights);
        user = getUpdatedUser(user.getId());
        val logRequest = new IdRequest();
        logRequest.setId(user != null ? user.getId() : null);
        val actualLog = userService.getLog(logRequest).getMessage();
        val expectedLog = logSimulator.getLog();
        Assert.assertEquals(expectedLog, actualLog);
        Assert.assertEquals(adminRole, user != null ? user.getRole() : null);
        Assert.assertTrue(response.getResponseCode() == ResponseCode.SUCCESS);
    }

    @Test
    public void changeAccessLevelToUser() throws Exception {
        val createUserRequest = new CreateUserRequest();
        createUserRequest.setAdminId(adminId);
        createUserRequest.setUsername(testUsername);
        createUserRequest.setPassword(testUsername);
        UserModel user = userService.create(createUserRequest).getUser();
        logSimulator.simulateCreation(testUsername);
        val changeRightRequest = new AdminProcedureRequest();
        changeRightRequest.setAdminId(adminId);
        changeRightRequest.setTargetId(user.getId());
        userService.grantAdministratorAccessLevel(changeRightRequest);
        logSimulator.simulateRightsChange(adminUsername, administratorRights);
        val response = userService.grantStandardUserAccessLevel(changeRightRequest);
        logSimulator.simulateRightsChange(adminUsername, nonAdministratorRights);
        user = getUpdatedUser(user.getId());
        val logRequest = new IdRequest();
        logRequest.setId(user != null ? user.getId() : null);
        val actualLog = userService.getLog(logRequest).getMessage();
        val expectedLog = logSimulator.getLog();
        Assert.assertTrue(response.getResponseCode() == ResponseCode.SUCCESS);
        Assert.assertEquals(userRole, user != null ? user.getRole() : null);
        Assert.assertEquals(expectedLog, actualLog);
    }

    @Test
    public void clearLogHistory() throws Exception {
        val expectedLog = "";
        val loginRequest = new LoginRequest();
        loginRequest.setUsername(adminUsername);
        loginRequest.setPassword(adminUsername);
        userService.login(loginRequest);
        val clearLogRequest = new AdminProcedureRequest();
        clearLogRequest.setAdminId(adminId);
        clearLogRequest.setTargetId(adminId);
        userService.clearHistory(clearLogRequest);
        val getLogRequest = new IdRequest();
        getLogRequest.setId(adminId);
        val response = userService.getLog(getLogRequest);
        val actualLog = response.getMessage();
        Assert.assertTrue(response.getResponseCode() == ResponseCode.SUCCESS);
        Assert.assertEquals(expectedLog, actualLog);
    }

    @Test
    public void complexLogTest() throws Exception {
        val createUserRequest = new CreateUserRequest();
        createUserRequest.setAdminId(adminId);
        createUserRequest.setUsername(testUsername);
        createUserRequest.setPassword(testUsername);
        val user = userService.create(createUserRequest).getUser();
        logSimulator.simulateCreation(testUsername);
        val adminProcedureRequest = new AdminProcedureRequest();
        adminProcedureRequest.setAdminId(adminId);
        adminProcedureRequest.setTargetId(user.getId());
        userService.grantAdministratorAccessLevel(adminProcedureRequest);
        logSimulator.simulateRightsChange(adminUsername, administratorRights);
        val loginRequest = new LoginRequest();
        loginRequest.setUsername(testUsername);
        loginRequest.setPassword(testUsername);
        userService.login(loginRequest);
        logSimulator.simulateLogon();
        userService.login(loginRequest);
        logSimulator.simulateLogon();
        userService.grantStandardUserAccessLevel(adminProcedureRequest);
        logSimulator.simulateRightsChange(adminUsername, nonAdministratorRights);
        val getLogRequest = new IdRequest();
        getLogRequest.setId(user.getId());
        val expectedLog = logSimulator.getLog();
        val actualLog = userService.getLog(getLogRequest).getMessage();
        Assert.assertEquals(expectedLog, actualLog);
    }

    @Test
    public void createUser() throws Exception {
        val createUserRequest = new CreateUserRequest();
        createUserRequest.setAdminId(adminId);
        createUserRequest.setUsername(testUsername);
        createUserRequest.setPassword(testUsername);
        val user = userService.create(createUserRequest).getUser();
        logSimulator.simulateCreation(testUsername);
        val getLogRequest = new IdRequest();
        getLogRequest.setId(user.getId());
        val expectedLog = logSimulator.getLog();
        val actualLog = userService.getLog(getLogRequest).getMessage();
        Assert.assertEquals(expectedLog, actualLog);
    }

    @Test
    public void deleteUser() throws Exception {
        val createUserRequest = new CreateUserRequest();
        createUserRequest.setAdminId(adminId);
        createUserRequest.setUsername(testUsername);
        createUserRequest.setPassword(testUsername);
        val user = userService.create(createUserRequest).getUser();
        val deleteRequest = new AdminProcedureRequest();
        deleteRequest.setAdminId(adminId);
        deleteRequest.setTargetId(user.getId());
        val response = userService.delete(deleteRequest);
        IdRequest getAllRequest = new IdRequest();
        getAllRequest.setId(adminId);
        val userList = userService.getAllUsers(getAllRequest).getUserList();
        userList.stream().filter(userFromList->userFromList.getId().equals(user.getId())).forEach(userFromList->Assert.fail());
        Assert.assertTrue(response.getResponseCode() == ResponseCode.SUCCESS);
    }

    @Test
    public void loginUser() throws Exception {
        val loginRequest = new LoginRequest();
        loginRequest.setUsername(adminUsername);
        loginRequest.setPassword(adminUsername);
        userService.login(loginRequest);
        logSimulator.simulateLogon();
        val getLogRequest = new IdRequest();
        getLogRequest.setId(adminId);
        val expectedLog = logSimulator.getLog();
        val actualLog = userService.getLog(getLogRequest).getMessage();
        Assert.assertEquals(expectedLog, actualLog);
    }

    private UserModel getUpdatedUser(Long id) {
        val getAllRequest = new IdRequest();
        getAllRequest.setId(adminId);
        val response = userService.getAllUsers(getAllRequest);
        val userList = response.getUserList();
        Optional<UserModel> first = userList.stream().filter(userModel->userModel.getId().equals(id)).findFirst();
        if (first.isPresent())
            return first.get();
        return null;
    }

}
