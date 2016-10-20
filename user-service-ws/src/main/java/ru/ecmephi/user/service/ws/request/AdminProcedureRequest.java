package ru.ecmephi.user.service.ws.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for adminProcedureRequest complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="adminProcedureRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adminId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="targetId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adminProcedureRequest", propOrder = {
        "adminId",
        "targetId"
})
@NoArgsConstructor
@AllArgsConstructor
public class AdminProcedureRequest {

    protected Long adminId;
    protected Long targetId;

    /**
     * Gets the value of the adminId property.
     *
     * @return possible object is
     * {@link Long }
     */
    public Long getAdminId() {
        return adminId;
    }

    /**
     * Sets the value of the adminId property.
     *
     * @param value allowed object is
     *              {@link Long }
     */
    public void setAdminId(Long value) {
        this.adminId = value;
    }

    /**
     * Gets the value of the targetId property.
     *
     * @return possible object is
     * {@link Long }
     */
    public Long getTargetId() {
        return targetId;
    }

    /**
     * Sets the value of the targetId property.
     *
     * @param value allowed object is
     *              {@link Long }
     */
    public void setTargetId(Long value) {
        this.targetId = value;
    }

}
