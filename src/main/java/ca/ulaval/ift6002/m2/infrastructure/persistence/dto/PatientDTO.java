package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PatientDTO {

    @Id
    public Integer number;

    public PatientDTO(Integer number) {
        this.number = number;
    }

    // TODO To be continued with prescriptions...
}
