package ru.ecmephi.user.service.ws.response;

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
 *         &lt;element name="responseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    private ResponseCode responseCode;

    /**
     * Gets the value of the responseCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public ResponseCode getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the value of the responseCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setResponseCode(ResponseCode value) {
        this.responseCode = value;
    }

}
