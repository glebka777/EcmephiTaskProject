package ru.ecmephi.user.service.ws.method;

import ru.ecmephi.user.service.ws.request.AdminProcedureRequest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for grantStandardUserAccessLevel complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="grantStandardUserAccessLevel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://ws.service.user.ecmephi.ru/}adminProcedureRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "grantStandardUserAccessLevel", propOrder = {
        "arg0"
})
public class GrantStandardUserAccessLevel {

    protected AdminProcedureRequest arg0;

    /**
     * Gets the value of the arg0 property.
     *
     * @return possible object is
     * {@link AdminProcedureRequest }
     */
    public AdminProcedureRequest getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     *
     * @param value allowed object is
     *              {@link AdminProcedureRequest }
     */
    public void setArg0(AdminProcedureRequest value) {
        this.arg0 = value;
    }

}
