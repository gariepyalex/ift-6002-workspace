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

}
