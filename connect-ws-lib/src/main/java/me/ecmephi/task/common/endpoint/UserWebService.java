package me.ecmephi.task.common.endpoint;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebServiceClient(name = "UserWebService", targetNamespace = "http://service.task.ecmephi.me/", wsdlLocation = "http://localhost:8080/endpoint/UserWebService/UserManager?wsdl")
public class UserWebService
        extends Service {

    private final static URL USERWEBSERVICE_WSDL_LOCATION;
    private final static WebServiceException USERWEBSERVICE_EXCEPTION;
    private final static QName USERWEBSERVICE_QNAME = new QName("http://service.task.ecmephi.me/", "UserWebService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/endpoint/UserWebService/UserManager?wsdl");
        } catch(MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        USERWEBSERVICE_WSDL_LOCATION = url;
        USERWEBSERVICE_EXCEPTION = e;
    }

    public UserWebService() {
        super(__getWsdlLocation(), USERWEBSERVICE_QNAME);
    }

    public UserWebService(WebServiceFeature... features) {
        super(__getWsdlLocation(), USERWEBSERVICE_QNAME, features);
    }

    public UserWebService(URL wsdlLocation) {
        super(wsdlLocation, USERWEBSERVICE_QNAME);
    }

    public UserWebService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, USERWEBSERVICE_QNAME, features);
    }

    public UserWebService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UserWebService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    private static URL __getWsdlLocation() {
        if (USERWEBSERVICE_EXCEPTION != null) {
            throw USERWEBSERVICE_EXCEPTION;
        }
        return USERWEBSERVICE_WSDL_LOCATION;
    }

    /**
     * @return returns UserWS
     */
    @WebEndpoint(name = "UserWSPort")
    public UserWS getUserWSPort() {
        return super.getPort(new QName("http://service.task.ecmephi.me/", "UserWSPort"), UserWS.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns UserWS
     */
    @WebEndpoint(name = "UserWSPort")
    public UserWS getUserWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.task.ecmephi.me/", "UserWSPort"), UserWS.class, features);
    }

}
