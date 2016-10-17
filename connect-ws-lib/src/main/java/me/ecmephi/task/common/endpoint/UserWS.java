package me.ecmephi.task.common.endpoint;

import me.ecmephi.task.common.ObjectFactory;
import me.ecmephi.task.common.request.AdminProcedureRequest;
import me.ecmephi.task.common.request.CreateUserRequest;
import me.ecmephi.task.common.request.IdRequest;
import me.ecmephi.task.common.request.LoginRequest;
import me.ecmephi.task.common.response.BaseResponse;
import me.ecmephi.task.common.response.MessageResponse;
import me.ecmephi.task.common.response.UserListResponse;
import me.ecmephi.task.common.response.UserResponse;

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
@WebService(name = "UserWS", targetNamespace = "http://service.task.ecmephi.me/")
@XmlSeeAlso({
        ObjectFactory.class
})
public interface UserWS {


    /**
     * @param arg0
     * @return returns me.ecmephi.task.service.BaseResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "delete", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.DeleteResponse")
    public BaseResponse delete(
            @WebParam(name = "arg0", targetNamespace = "")
                    AdminProcedureRequest arg0);

    /**
     * @param arg0
     * @return returns me.ecmephi.task.service.UserResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.LoginResponse")
    public UserResponse login(
            @WebParam(name = "arg0", targetNamespace = "")
                    LoginRequest arg0);

    /**
     * @param arg0
     * @return returns me.ecmephi.task.service.UserResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "create", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.Create")
    @ResponseWrapper(localName = "createResponse", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.CreateResponse")
    public UserResponse create(
            @WebParam(name = "arg0", targetNamespace = "")
                    CreateUserRequest arg0);

    /**
     * @param arg0
     * @return returns me.ecmephi.task.service.BaseResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "grantAdministratorAccessLevel", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.GrantAdministratorAccessLevel")
    @ResponseWrapper(localName = "grantAdministratorAccessLevelResponse", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.GrantAdministratorAccessLevelResponse")
    public BaseResponse grantAdministratorAccessLevel(
            @WebParam(name = "arg0", targetNamespace = "")
                    AdminProcedureRequest arg0);

    /**
     * @param arg0
     * @return returns me.ecmephi.task.service.BaseResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "grantStandardUserAccessLevel", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.GrantStandardUserAccessLevel")
    @ResponseWrapper(localName = "grantStandardUserAccessLevelResponse", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.GrantStandardUserAccessLevelResponse")
    public BaseResponse grantStandardUserAccessLevel(
            @WebParam(name = "arg0", targetNamespace = "")
                    AdminProcedureRequest arg0);

    /**
     * @param arg0
     * @return returns me.ecmephi.task.service.MessageResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getLog", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.GetLog")
    @ResponseWrapper(localName = "getLogResponse", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.GetLogResponse")
    public MessageResponse getLog(
            @WebParam(name = "arg0", targetNamespace = "")
                    IdRequest arg0);

    /**
     * @param arg0
     * @return returns me.ecmephi.task.service.UserListResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllUsers", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.GetAllUsers")
    @ResponseWrapper(localName = "getAllUsersResponse", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.GetAllUsersResponse")
    public UserListResponse getAllUsers(
            @WebParam(name = "arg0", targetNamespace = "")
                    IdRequest arg0);

    /**
     * @param arg0
     * @return returns me.ecmephi.task.service.MessageResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAccessLevel", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.GetAccessLevel")
    @ResponseWrapper(localName = "getAccessLevelResponse", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.GetAccessLevelResponse")
    public MessageResponse getAccessLevel(
            @WebParam(name = "arg0", targetNamespace = "")
                    IdRequest arg0);

    /**
     * @param arg0
     * @return returns me.ecmephi.task.service.BaseResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "clearHistory", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.ClearHistory")
    @ResponseWrapper(localName = "clearHistoryResponse", targetNamespace = "http://service.task.ecmephi.me/", className = "me.ecmephi.task.service.ClearHistoryResponse")
    public BaseResponse clearHistory(
            @WebParam(name = "arg0", targetNamespace = "")
                    AdminProcedureRequest arg0);

}
