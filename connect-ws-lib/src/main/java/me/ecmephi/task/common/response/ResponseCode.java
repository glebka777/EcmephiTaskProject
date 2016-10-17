package me.ecmephi.task.common.response;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for responseCode.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="responseCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="UNKNOWN_ERROR"/>
 *     &lt;enumeration value="INCORRECT_INPUT"/>
 *     &lt;enumeration value="ACCESS_DENIED"/>
 *     &lt;enumeration value="USER_DOES_NOT_EXIST"/>
 *     &lt;enumeration value="SAME_RIGHTS"/>
 *     &lt;enumeration value="OPERATION_ON_CURRENT_USER"/>
 *     &lt;enumeration value="EXISTING_USERNAME"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "responseCode")
@XmlEnum
public enum ResponseCode {

    SUCCESS,
    UNKNOWN_ERROR,
    INCORRECT_INPUT,
    ACCESS_DENIED,
    USER_DOES_NOT_EXIST,
    SAME_RIGHTS,
    OPERATION_ON_CURRENT_USER,
    EXISTING_USERNAME;

    public static ResponseCode fromValue(String v) {
        return valueOf(v);
    }

    public String value() {
        return name();
    }

}
