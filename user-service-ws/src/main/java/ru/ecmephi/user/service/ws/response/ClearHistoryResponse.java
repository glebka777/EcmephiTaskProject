package ru.ecmephi.user.service.ws.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for clearHistoryResponse complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="clearHistoryResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ws.service.user.ecmephi.ru/}baseResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clearHistoryResponse", propOrder = {
        "_return"
})
public class ClearHistoryResponse {

    @XmlElement(name = "return")
    private BaseResponse _return;

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
