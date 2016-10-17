package me.ecmephi.task.common.response;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for baseResponse complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="baseResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responseCode" type="{http://service.task.ecmephi.me/}responseCode" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseResponse", propOrder = {
        "responseCode"
})
@XmlSeeAlso({
        UserListResponse.class,
        UserResponse.class,
        MessageResponse.class
})
public class BaseResponse {

    @XmlSchemaType(name = "string")
    protected ResponseCode responseCode;

    /**
     * Gets the value of the responseCode property.
     *
     * @return possible object is
     * {@link ResponseCode }
     */
    public ResponseCode getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the value of the responseCode property.
     *
     * @param value allowed object is
     *              {@link ResponseCode }
     */
    public void setResponseCode(ResponseCode value) {
        this.responseCode = value;
    }

}
