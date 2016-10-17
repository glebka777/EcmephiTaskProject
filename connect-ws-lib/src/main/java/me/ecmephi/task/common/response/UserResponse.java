package me.ecmephi.task.common.response;

import me.ecmephi.task.common.UserModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userResponse complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="userResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.task.ecmephi.me/}baseResponse">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://service.task.ecmephi.me/}userModel" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userResponse", propOrder = {
        "user"
})
public class UserResponse
        extends BaseResponse {

    protected UserModel user;

    /**
     * Gets the value of the user property.
     *
     * @return possible object is
     * {@link UserModel }
     */
    public UserModel getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     *
     * @param value allowed object is
     *              {@link UserModel }
     */
    public void setUser(UserModel value) {
        this.user = value;
    }

}
