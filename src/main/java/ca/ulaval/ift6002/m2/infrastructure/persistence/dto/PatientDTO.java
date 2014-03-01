package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import javax.persistence.Entity;

@Entity
public class PatientDTO extends BaseDTO {

    public Integer number;

    public PatientDTO(Integer number) {
        this.number = number;
    }

    // TODO To be continued with prescriptions...
}
