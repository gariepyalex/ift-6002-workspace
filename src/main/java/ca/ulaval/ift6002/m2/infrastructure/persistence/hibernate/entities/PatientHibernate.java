package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

@Entity
public class PatientHibernate {

    @Id
    public Integer number;

    @OneToMany(cascade = { CascadeType.ALL })
    // TODO use prescription hibernate
    // public Collection<PrescriptionDTO> prescriptions;
    public String healthInsuranceNumber;

    public PatientHibernate(Integer number, String healthInsuranceNumber) {
        this(number, healthInsuranceNumber, new ArrayList<Prescription>());
    }

    public PatientHibernate(Integer number, String healthInsuranceNumber, Collection<Prescription> prescriptions) {
        this.number = number;
        // this.prescriptions = prescriptions;
        this.healthInsuranceNumber = healthInsuranceNumber;
    }

    protected PatientHibernate() {
        this.number = 0;
        // this.prescriptions = new ArrayList<PrescriptionDTO>();
        this.healthInsuranceNumber = "";
    }
}
