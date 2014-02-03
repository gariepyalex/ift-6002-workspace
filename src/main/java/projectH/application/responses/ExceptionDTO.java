package projectH.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "exception")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExceptionDTO {

	public String code;
	public String message;

	public ExceptionDTO() {
	}

	public ExceptionDTO(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
