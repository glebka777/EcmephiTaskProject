package ru.ecmephi.user.service.ws.response;

import ru.ecmephi.user.service.ws.UserModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for userListResponse complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="userListResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.service.user.ecmephi.ru/}baseResponse">
 *       &lt;sequence>
 *         &lt;element name="userList" type="{http://ws.service.user.ecmephi.ru/}userModel" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userListResponse", propOrder = {
        "userList"
})
public class UserListResponse
        extends BaseResponse {

    @XmlElement(nillable = true)
    private List<UserModel> userList;

    /**
     * Gets the value of the userList property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userList property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserList().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserModel }
     */
    public List<UserModel> getUserList() {
        if (userList == null) {
            userList = new ArrayList<>();
        }
        return this.userList;
    }

}
