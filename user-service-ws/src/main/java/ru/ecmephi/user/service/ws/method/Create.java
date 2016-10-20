package ru.ecmephi.user.service.ws.method;

import ru.ecmephi.user.service.ws.request.CreateUserRequest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for create complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="create">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://ws.service.user.ecmephi.ru/}createUserRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "create", propOrder = {
        "arg0"
})
public class Create {

    protected CreateUserRequest arg0;

    /**
     * Gets the value of the arg0 property.
     *
     * @return possible object is
     * {@link CreateUserRequest }
     */
    public CreateUserRequest getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     *
     * @param value allowed object is
     *              {@link CreateUserRequest }
     */
    public void setArg0(CreateUserRequest value) {
        this.arg0 = value;
    }

}
