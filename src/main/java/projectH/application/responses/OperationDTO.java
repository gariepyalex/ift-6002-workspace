package projectH.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import projectH.domain.operation.Operation;
import projectH.domain.operation.OperationStatus;
import projectH.domain.operation.OperationType;

@XmlRootElement(name = "operation")
@XmlAccessorType(XmlAccessType.FIELD)
public class OperationDTO {
	private String description;
	private Integer surgeon;
	private String date;
	private String room;
	private OperationType type;
	private OperationStatus status;
	private Integer patientNumber;

	protected OperationDTO() {
	}

	public Operation toOperation() {

		return new Operation(description, surgeon, date, room, type, status, patientNumber);

	}
}