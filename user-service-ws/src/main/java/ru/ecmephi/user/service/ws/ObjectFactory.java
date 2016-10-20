package ru.ecmephi.user.service.ws;

import ru.ecmephi.user.service.ws.method.*;
import ru.ecmephi.user.service.ws.response.*;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the ru.ecmephi.user.service.ws package.
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

    private final static QName _ClearHistory_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "clearHistory");
    private final static QName _DeleteResponse_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "deleteResponse");
    private final static QName _Create_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "create");
    private final static QName _GetAccessLevelResponse_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "getAccessLevelResponse");
    private final static QName _GrantStandardUserAccessLevelResponse_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "grantStandardUserAccessLevelResponse");
    private final static QName _GetLog_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "getLog");
    private final static QName _GrantStandardUserAccessLevel_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "grantStandardUserAccessLevel");
    private final static QName _GrantAdministratorAccessLevel_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "grantAdministratorAccessLevel");
    private final static QName _ClearHistoryResponse_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "clearHistoryResponse");
    private final static QName _Delete_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "delete");
    private final static QName _Login_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "login");
    private final static QName _GetAllUsersResponse_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "getAllUsersResponse");
    private final static QName _GetAccessLevel_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "getAccessLevel");
    private final static QName _GrantAdministratorAccessLevelResponse_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "grantAdministratorAccessLevelResponse");
    private final static QName _LoginResponse_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "loginResponse");
    private final static QName _GetAllUsers_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "getAllUsers");
    private final static QName _CreateResponse_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "createResponse");
    private final static QName _GetLogResponse_QNAME = new QName("http://ws.service.user.ecmephi.ru/", "getLogResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.ecmephi.user.service.ws
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearHistory }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "clearHistory")
    public JAXBElement<ClearHistory> createClearHistory(ClearHistory value) {
        return new JAXBElement<ClearHistory>(_ClearHistory_QNAME, ClearHistory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Create }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "create")
    public JAXBElement<Create> createCreate(Create value) {
        return new JAXBElement<Create>(_Create_QNAME, Create.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccessLevelResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "getAccessLevelResponse")
    public JAXBElement<GetAccessLevelResponse> createGetAccessLevelResponse(GetAccessLevelResponse value) {
        return new JAXBElement<GetAccessLevelResponse>(_GetAccessLevelResponse_QNAME, GetAccessLevelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrantStandardUserAccessLevelResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "grantStandardUserAccessLevelResponse")
    public JAXBElement<GrantStandardUserAccessLevelResponse> createGrantStandardUserAccessLevelResponse(GrantStandardUserAccessLevelResponse value) {
        return new JAXBElement<GrantStandardUserAccessLevelResponse>(_GrantStandardUserAccessLevelResponse_QNAME, GrantStandardUserAccessLevelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLog }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "getLog")
    public JAXBElement<GetLog> createGetLog(GetLog value) {
        return new JAXBElement<GetLog>(_GetLog_QNAME, GetLog.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrantStandardUserAccessLevel }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "grantStandardUserAccessLevel")
    public JAXBElement<GrantStandardUserAccessLevel> createGrantStandardUserAccessLevel(GrantStandardUserAccessLevel value) {
        return new JAXBElement<GrantStandardUserAccessLevel>(_GrantStandardUserAccessLevel_QNAME, GrantStandardUserAccessLevel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrantAdministratorAccessLevel }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "grantAdministratorAccessLevel")
    public JAXBElement<GrantAdministratorAccessLevel> createGrantAdministratorAccessLevel(GrantAdministratorAccessLevel value) {
        return new JAXBElement<GrantAdministratorAccessLevel>(_GrantAdministratorAccessLevel_QNAME, GrantAdministratorAccessLevel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearHistoryResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "clearHistoryResponse")
    public JAXBElement<ClearHistoryResponse> createClearHistoryResponse(ClearHistoryResponse value) {
        return new JAXBElement<ClearHistoryResponse>(_ClearHistoryResponse_QNAME, ClearHistoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUsersResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "getAllUsersResponse")
    public JAXBElement<GetAllUsersResponse> createGetAllUsersResponse(GetAllUsersResponse value) {
        return new JAXBElement<GetAllUsersResponse>(_GetAllUsersResponse_QNAME, GetAllUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccessLevel }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "getAccessLevel")
    public JAXBElement<GetAccessLevel> createGetAccessLevel(GetAccessLevel value) {
        return new JAXBElement<GetAccessLevel>(_GetAccessLevel_QNAME, GetAccessLevel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrantAdministratorAccessLevelResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "grantAdministratorAccessLevelResponse")
    public JAXBElement<GrantAdministratorAccessLevelResponse> createGrantAdministratorAccessLevelResponse(GrantAdministratorAccessLevelResponse value) {
        return new JAXBElement<GrantAdministratorAccessLevelResponse>(_GrantAdministratorAccessLevelResponse_QNAME, GrantAdministratorAccessLevelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUsers }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "getAllUsers")
    public JAXBElement<GetAllUsers> createGetAllUsers(GetAllUsers value) {
        return new JAXBElement<GetAllUsers>(_GetAllUsers_QNAME, GetAllUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "createResponse")
    public JAXBElement<CreateResponse> createCreateResponse(CreateResponse value) {
        return new JAXBElement<CreateResponse>(_CreateResponse_QNAME, CreateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLogResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.service.user.ecmephi.ru/", name = "getLogResponse")
    public JAXBElement<GetLogResponse> createGetLogResponse(GetLogResponse value) {
        return new JAXBElement<GetLogResponse>(_GetLogResponse_QNAME, GetLogResponse.class, null, value);
    }

}
