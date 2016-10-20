package ru.ecmephi.user.service.ws.endpoint;

import ru.ecmephi.user.service.ws.request.AdminProcedureRequest;
import ru.ecmephi.user.service.ws.request.CreateUserRequest;
import ru.ecmephi.user.service.ws.request.IdRequest;
import ru.ecmephi.user.service.ws.request.LoginRequest;
import ru.ecmephi.user.service.ws.response.BaseResponse;
import ru.ecmephi.user.service.ws.response.MessageResponse;
import ru.ecmephi.user.service.ws.response.UserListResponse;
import ru.ecmephi.user.service.ws.response.UserResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebService(name = "UserWS", targetNamespace = "http://service.user.ecmephi.ru/}")
@XmlSeeAlso({
        ru.ecmephi.user.service.ws.ObjectFactory.class
})
public interface UserWS {


    /**
     * @param arg0
     * @return returns ru.ecmephi.user.service.ws.UserResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.LoginResponse")
    public UserResponse login(
            @WebParam(name = "arg0", targetNamespace = "")
                    LoginRequest arg0);

    /**
     * @param arg0
     * @return returns ru.ecmephi.user.service.ws.BaseResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "delete", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.DeleteResponse")
    public BaseResponse delete(
            @WebParam(name = "arg0", targetNamespace = "")
                    AdminProcedureRequest arg0);

    /**
     * @param arg0
     * @return returns ru.ecmephi.user.service.ws.BaseResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "grantAdministratorAccessLevel", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.GrantAdministratorAccessLevel")
    @ResponseWrapper(localName = "grantAdministratorAccessLevelResponse", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.GrantAdministratorAccessLevelResponse")
    public BaseResponse grantAdministratorAccessLevel(
            @WebParam(name = "arg0", targetNamespace = "")
                    AdminProcedureRequest arg0);

    /**
     * @param arg0
     * @return returns ru.ecmephi.user.service.ws.UserResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "create", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.Create")
    @ResponseWrapper(localName = "createResponse", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.CreateResponse")
    public UserResponse create(
            @WebParam(name = "arg0", targetNamespace = "")
                    CreateUserRequest arg0);

    /**
     * @param arg0
     * @return returns ru.ecmephi.user.service.ws.MessageResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getLog", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.GetLog")
    @ResponseWrapper(localName = "getLogResponse", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.GetLogResponse")
    public MessageResponse getLog(
            @WebParam(name = "arg0", targetNamespace = "")
                    IdRequest arg0);

    /**
     * @param arg0
     * @return returns ru.ecmephi.user.service.ws.BaseResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "grantStandardUserAccessLevel", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.GrantStandardUserAccessLevel")
    @ResponseWrapper(localName = "grantStandardUserAccessLevelResponse", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.GrantStandardUserAccessLevelResponse")
    public BaseResponse grantStandardUserAccessLevel(
            @WebParam(name = "arg0", targetNamespace = "")
                    AdminProcedureRequest arg0);

    /**
     * @param arg0
     * @return returns ru.ecmephi.user.service.ws.BaseResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "clearHistory", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.ClearHistory")
    @ResponseWrapper(localName = "clearHistoryResponse", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.ClearHistoryResponse")
    public BaseResponse clearHistory(
            @WebParam(name = "arg0", targetNamespace = "")
                    AdminProcedureRequest arg0);

    /**
     * @param arg0
     * @return returns ru.ecmephi.user.service.ws.MessageResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAccessLevel", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.GetAccessLevel")
    @ResponseWrapper(localName = "getAccessLevelResponse", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.GetAccessLevelResponse")
    public MessageResponse getAccessLevel(
            @WebParam(name = "arg0", targetNamespace = "")
                    IdRequest arg0);

    /**
     * @param arg0
     * @return returns ru.ecmephi.user.service.ws.UserListResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllUsers", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.GetAllUsers")
    @ResponseWrapper(localName = "getAllUsersResponse", targetNamespace = "http://service.user.ecmephi.ru/}", className = "ru.ecmephi.user.service.__.GetAllUsersResponse")
    public UserListResponse getAllUsers(
            @WebParam(name = "arg0", targetNamespace = "")
                    IdRequest arg0);

}