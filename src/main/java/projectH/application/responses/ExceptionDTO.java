package projectH.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "exception")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExceptionDTO {

    public final String code;
    public final String message;

    protected ExceptionDTO() {
        this.code = "";
        this.message = "";
    }

    public ExceptionDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
