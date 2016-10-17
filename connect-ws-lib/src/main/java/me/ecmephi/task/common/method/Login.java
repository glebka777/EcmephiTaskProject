package me.ecmephi.task.common.method;

import me.ecmephi.task.common.request.LoginRequest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for login complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="login">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://service.task.ecmephi.me/}loginRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "login", propOrder = {
        "arg0"
})
public class Login {

    protected LoginRequest arg0;

    /**
     * Gets the value of the arg0 property.
     *
     * @return possible object is
     * {@link LoginRequest }
     */
    public LoginRequest getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     *
     * @param value allowed object is
     *              {@link LoginRequest }
     */
    public void setArg0(LoginRequest value) {
        this.arg0 = value;
    }

}
