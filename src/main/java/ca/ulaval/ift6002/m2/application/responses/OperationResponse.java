package ca.ulaval.ift6002.m2.application.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "operation")
@XmlAccessorType(XmlAccessType.FIELD)
public class OperationResponse {
    public String description;

    @XmlElement(name = "chirurgien")
    public Integer surgeon;

    public String date;

    @XmlElement(name = "salle")
    public String room;
    public String type;

    @XmlElement(name = "statut")
    public String status;

    @XmlElement(name = "patient")
    public Integer patientNumber;

    protected OperationResponse() {
        // Protected constructor for jaxb
        this.surgeon = 0;
        this.date = "";
        this.room = "";
        this.type = "";
        this.status = "";
        this.patientNumber = 0;
    }

    public OperationResponse(String description, Integer surgeon, String date, String room, String type, String status,
            Integer patientNumber) {
        this.description = description;
        this.surgeon = surgeon;
        this.date = date;
        this.room = room;
        this.type = type;
        this.status = status;
        this.patientNumber = patientNumber;
    }

}
