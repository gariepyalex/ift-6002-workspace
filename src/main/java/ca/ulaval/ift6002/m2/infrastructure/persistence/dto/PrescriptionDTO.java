package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PrescriptionDTO {

    // TODO To be continued with drug
    // TODO have a real id also...
    @Id
    public String practitioner;
    public String date;
    public Integer renewals;

    public PrescriptionDTO(String practitioner, String date, Integer renewals) {
        this.practitioner = practitioner;
        this.date = date;
        this.renewals = renewals;
    }
}
