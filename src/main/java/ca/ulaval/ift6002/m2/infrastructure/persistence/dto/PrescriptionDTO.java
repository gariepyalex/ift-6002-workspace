package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import javax.persistence.Entity;

@Entity
public class PrescriptionDTO extends BaseDTO {

    // TODO have a real id also...

    public String practitioner;
    public String date;
    public Integer renewals;

    public PrescriptionDTO(String practitioner, String date, Integer renewals) {
        this.practitioner = practitioner;
        this.date = date;
        this.renewals = renewals;
    }
}
