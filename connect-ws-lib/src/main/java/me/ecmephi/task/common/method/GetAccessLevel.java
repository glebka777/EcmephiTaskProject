package me.ecmephi.task.common.method;

import me.ecmephi.task.common.request.IdRequest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAccessLevel complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="getAccessLevel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://service.task.ecmephi.me/}idRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAccessLevel", propOrder = {
        "arg0"
})
public class GetAccessLevel {

    protected IdRequest arg0;

    /**
     * Gets the value of the arg0 property.
     *
     * @return possible object is
     * {@link IdRequest }
     */
    public IdRequest getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     *
     * @param value allowed object is
     *              {@link IdRequest }
     */
    public void setArg0(IdRequest value) {
        this.arg0 = value;
    }

}
