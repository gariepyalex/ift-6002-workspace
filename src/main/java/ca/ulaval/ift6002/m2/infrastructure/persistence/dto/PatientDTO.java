package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PatientDTO {

    @Id
    public Integer number;

    @OneToMany(cascade = { CascadeType.ALL })
    public Collection<PrescriptionDTO> prescriptions;

    public PatientDTO(Integer number, Collection<PrescriptionDTO> prescriptions) {
        this.number = number;
        this.prescriptions = prescriptions;
    }

    protected PatientDTO() {
        this.number = 0;
        this.prescriptions = new ArrayList<PrescriptionDTO>();
    }
}
