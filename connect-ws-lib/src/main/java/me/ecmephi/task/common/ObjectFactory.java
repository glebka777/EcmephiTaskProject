package me.ecmephi.task.common;

import me.ecmephi.task.common.method.*;
import me.ecmephi.task.common.request.AdminProcedureRequest;
import me.ecmephi.task.common.request.CreateUserRequest;
import me.ecmephi.task.common.request.IdRequest;
import me.ecmephi.task.common.request.LoginRequest;
import me.ecmephi.task.common.response.*;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the me.ecmephi.task.common package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Create_QNAME = new QName("http://common.task.ecmephi.me/", "create");
    private final static QName _GrantAdministratorAccessLevelResponse_QNAME = new QName("http://common.task.ecmephi.me/", "grantAdministratorAccessLevelResponse");
    private final static QName _DeleteResponse_QNAME = new QName("http://common.task.ecmephi.me/", "deleteResponse");
    private final static QName _GetAccessLevel_QNAME = new QName("http://common.task.ecmephi.me/", "getAccessLevel");
    private final static QName _ClearHistory_QNAME = new QName("http://common.task.ecmephi.me/", "clearHistory");
    private final static QName _GetAllUsersResponse_QNAME = new QName("http://common.task.ecmephi.me/", "getAllUsersResponse");
    private final static QName _Delete_QNAME = new QName("http://common.task.ecmephi.me/", "delete");
    private final static QName _GetLogResponse_QNAME = new QName("http://common.task.ecmephi.me/", "getLogResponse");
    private final static QName _Login_QNAME = new QName("http://common.task.ecmephi.me/", "login");
    private final static QName _ClearHistoryResponse_QNAME = new QName("http://common.task.ecmephi.me/", "clearHistoryResponse");
    private final static QName _CreateResponse_QNAME = new QName("http://common.task.ecmephi.me/", "createResponse");
    private final static QName _GrantAdministratorAccessLevel_QNAME = new QName("http://common.task.ecmephi.me/", "grantAdministratorAccessLevel");
    private final static QName _GrantStandardUserAccessLevel_QNAME = new QName("http://common.task.ecmephi.me/", "grantStandardUserAccessLevel");
    private final static QName _GetLog_QNAME = new QName("http://common.task.ecmephi.me/", "getLog");
    private final static QName _GetAllUsers_QNAME = new QName("http://common.task.ecmephi.me/", "getAllUsers");
    private final static QName _LoginResponse_QNAME = new QName("http://common.task.ecmephi.me/", "loginResponse");
    private final static QName _GetAccessLevelResponse_QNAME = new QName("http://common.task.ecmephi.me/", "getAccessLevelResponse");
    private final static QName _GrantStandardUserAccessLevelResponse_QNAME = new QName("http://common.task.ecmephi.me/", "grantStandardUserAccessLevelResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: me.ecmephi.task.common
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllUsers }
     */
    public GetAllUsers createGetAllUsers() {
        return new GetAllUsers();
    }

    /**
     * Create an instance of {@link GetAccessLevelResponse }
     */
    public GetAccessLevelResponse createGetAccessLevelResponse() {
        return new GetAccessLevelResponse();
    }

    /**
     * Create an instance of {@link GrantStandardUserAccessLevelResponse }
     */
    public GrantStandardUserAccessLevelResponse createGrantStandardUserAccessLevelResponse() {
        return new GrantStandardUserAccessLevelResponse();
    }

    /**
     * Create an instance of {@link LoginResponse }
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link GrantStandardUserAccessLevel }
     */
    public GrantStandardUserAccessLevel createGrantStandardUserAccessLevel() {
        return new GrantStandardUserAccessLevel();
    }

    /**
     * Create an instance of {@link GetLog }
     */
    public GetLog createGetLog() {
        return new GetLog();
    }

    /**
     * Create an instance of {@link GrantAdministratorAccessLevel }
     */
    public GrantAdministratorAccessLevel createGrantAdministratorAccessLevel() {
        return new GrantAdministratorAccessLevel();
    }

    /**
     * Create an instance of {@link CreateResponse }
     */
    public CreateResponse createCreateResponse() {
        return new CreateResponse();
    }

    /**
     * Create an instance of {@link GetLogResponse }
     */
    public GetLogResponse createGetLogResponse() {
        return new GetLogResponse();
    }

    /**
     * Create an instance of {@link Login }
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link Delete }
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link ClearHistoryResponse }
     */
    public ClearHistoryResponse createClearHistoryResponse() {
        return new ClearHistoryResponse();
    }

    /**
     * Create an instance of {@link GetAccessLevel }
     */
    public GetAccessLevel createGetAccessLevel() {
        return new GetAccessLevel();
    }

    /**
     * Create an instance of {@link GetAllUsersResponse }
     */
    public GetAllUsersResponse createGetAllUsersResponse() {
        return new GetAllUsersResponse();
    }

    /**
     * Create an instance of {@link ClearHistory }
     */
    public ClearHistory createClearHistory() {
        return new ClearHistory();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link GrantAdministratorAccessLevelResponse }
     */
    public GrantAdministratorAccessLevelResponse createGrantAdministratorAccessLevelResponse() {
        return new GrantAdministratorAccessLevelResponse();
    }

    /**
     * Create an instance of {@link Create }
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link CreateUserRequest }
     */
    public CreateUserRequest createCreateUserRequest() {
        return new CreateUserRequest();
    }

    /**
     * Create an instance of {@link BaseResponse }
     */
    public BaseResponse createBaseResponse() {
        return new BaseResponse();
    }

    /**
     * Create an instance of {@link UserListResponse }
     */
    public UserListResponse createUserListResponse() {
        return new UserListResponse();
    }

    /**
     * Create an instance of {@link UserResponse }
     */
    public UserResponse createUserResponse() {
        return new UserResponse();
    }

    /**
     * Create an instance of {@link UserModel }
     */
    public UserModel createUserModel() {
        return new UserModel();
    }

    /**
     * Create an instance of {@link LoginRequest }
     */
    public LoginRequest createLoginRequest() {
        return new LoginRequest();
    }

    /**
     * Create an instance of {@link AdminProcedureRequest }
     */
    public AdminProcedureRequest createAdminProcedureRequest() {
        return new AdminProcedureRequest();
    }

    /**
     * Create an instance of {@link MessageResponse }
     */
    public MessageResponse createMessageResponse() {
        return new MessageResponse();
    }

    /**
     * Create an instance of {@link IdRequest }
     */
    public IdRequest createIdRequest() {
        return new IdRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Create }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "create")
    public JAXBElement<Create> createCreate(Create value) {
        return new JAXBElement<Create>(_Create_QNAME, Create.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrantAdministratorAccessLevelResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "grantAdministratorAccessLevelResponse")
    public JAXBElement<GrantAdministratorAccessLevelResponse> createGrantAdministratorAccessLevelResponse(GrantAdministratorAccessLevelResponse value) {
        return new JAXBElement<GrantAdministratorAccessLevelResponse>(_GrantAdministratorAccessLevelResponse_QNAME, GrantAdministratorAccessLevelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccessLevel }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "getAccessLevel")
    public JAXBElement<GetAccessLevel> createGetAccessLevel(GetAccessLevel value) {
        return new JAXBElement<GetAccessLevel>(_GetAccessLevel_QNAME, GetAccessLevel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearHistory }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "clearHistory")
    public JAXBElement<ClearHistory> createClearHistory(ClearHistory value) {
        return new JAXBElement<ClearHistory>(_ClearHistory_QNAME, ClearHistory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUsersResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "getAllUsersResponse")
    public JAXBElement<GetAllUsersResponse> createGetAllUsersResponse(GetAllUsersResponse value) {
        return new JAXBElement<GetAllUsersResponse>(_GetAllUsersResponse_QNAME, GetAllUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLogResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "getLogResponse")
    public JAXBElement<GetLogResponse> createGetLogResponse(GetLogResponse value) {
        return new JAXBElement<GetLogResponse>(_GetLogResponse_QNAME, GetLogResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearHistoryResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "clearHistoryResponse")
    public JAXBElement<ClearHistoryResponse> createClearHistoryResponse(ClearHistoryResponse value) {
        return new JAXBElement<ClearHistoryResponse>(_ClearHistoryResponse_QNAME, ClearHistoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "createResponse")
    public JAXBElement<CreateResponse> createCreateResponse(CreateResponse value) {
        return new JAXBElement<CreateResponse>(_CreateResponse_QNAME, CreateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrantAdministratorAccessLevel }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "grantAdministratorAccessLevel")
    public JAXBElement<GrantAdministratorAccessLevel> createGrantAdministratorAccessLevel(GrantAdministratorAccessLevel value) {
        return new JAXBElement<GrantAdministratorAccessLevel>(_GrantAdministratorAccessLevel_QNAME, GrantAdministratorAccessLevel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrantStandardUserAccessLevel }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "grantStandardUserAccessLevel")
    public JAXBElement<GrantStandardUserAccessLevel> createGrantStandardUserAccessLevel(GrantStandardUserAccessLevel value) {
        return new JAXBElement<GrantStandardUserAccessLevel>(_GrantStandardUserAccessLevel_QNAME, GrantStandardUserAccessLevel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLog }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "getLog")
    public JAXBElement<GetLog> createGetLog(GetLog value) {
        return new JAXBElement<GetLog>(_GetLog_QNAME, GetLog.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUsers }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "getAllUsers")
    public JAXBElement<GetAllUsers> createGetAllUsers(GetAllUsers value) {
        return new JAXBElement<GetAllUsers>(_GetAllUsers_QNAME, GetAllUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccessLevelResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "getAccessLevelResponse")
    public JAXBElement<GetAccessLevelResponse> createGetAccessLevelResponse(GetAccessLevelResponse value) {
        return new JAXBElement<GetAccessLevelResponse>(_GetAccessLevelResponse_QNAME, GetAccessLevelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrantStandardUserAccessLevelResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://common.task.ecmephi.me/", name = "grantStandardUserAccessLevelResponse")
    public JAXBElement<GrantStandardUserAccessLevelResponse> createGrantStandardUserAccessLevelResponse(GrantStandardUserAccessLevelResponse value) {
        return new JAXBElement<GrantStandardUserAccessLevelResponse>(_GrantStandardUserAccessLevelResponse_QNAME, GrantStandardUserAccessLevelResponse.class, null, value);
    }

}
