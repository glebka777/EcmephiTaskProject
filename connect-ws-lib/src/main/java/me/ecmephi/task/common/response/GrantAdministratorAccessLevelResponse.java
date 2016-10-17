package me.ecmephi.task.common.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for grantAdministratorAccessLevelResponse complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="grantAdministratorAccessLevelResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.task.ecmephi.me/}baseResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "grantAdministratorAccessLevelResponse", propOrder = {
        "_return"
})
public class GrantAdministratorAccessLevelResponse {

    @XmlElement(name = "return")
    protected BaseResponse _return;

    /**
     * Gets the value of the return property.
     *
     * @return possible object is
     * {@link BaseResponse }
     */
    public BaseResponse getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value allowed object is
     *              {@link BaseResponse }
     */
    public void setReturn(BaseResponse value) {
        this._return = value;
    }

}
