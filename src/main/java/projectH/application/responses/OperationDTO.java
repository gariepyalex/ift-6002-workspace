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
    public String description;
    public Integer surgeon;
    public String date;
    public String room;
    public OperationType type;
    public OperationStatus status;
    public Integer patientNumber;

    protected OperationDTO() {
    }

    public Operation toOperation() {

        return new Operation(description, surgeon, date, room, type, status, patientNumber);

    }
}
