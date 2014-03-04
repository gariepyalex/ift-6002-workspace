package ca.ulaval.ift6002.m2.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "exception")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExceptionResponse {

    public final String code;
    public final String message;

    protected ExceptionResponse() {
        // Protected constructor for jaxb
        this.code = "";
        this.message = "";
    }

    public ExceptionResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
