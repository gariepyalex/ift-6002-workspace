package ca.ulaval.ift6002.m2.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;

@XmlRootElement(name = "operation")
@XmlAccessorType(XmlAccessType.FIELD)
public class OperationResponse {
    public String description;
    public Integer surgeon;
    public String date;
    public String room;
    public OperationType type;
    public OperationStatus status;
    public Integer patientNumber;

    protected OperationResponse() {

    }

    public OperationResponse(String description, Integer surgeon, String date, String room, OperationType type,
            OperationStatus status, Integer patientNumber) {
        this.description = description;
        this.surgeon = surgeon;
        this.date = date;
        this.room = room;
        this.type = type;
        this.status = status;
        this.patientNumber = patientNumber;
    }

}
