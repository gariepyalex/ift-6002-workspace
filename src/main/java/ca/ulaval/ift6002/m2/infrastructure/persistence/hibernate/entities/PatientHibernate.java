package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

@Entity
@Table(name = "tbl_patient")
public class PatientHibernate extends Patient {

    @Id
    public Integer number;

    @OneToMany(cascade = { CascadeType.ALL })
    public Collection<PrescriptionHibernate> prescriptions;
    public String healthInsuranceNumber;
    public Boolean isDead;

    public PatientHibernate(Integer number, String healthInsuranceNumber) {
        this.number = number;
        this.healthInsuranceNumber = healthInsuranceNumber;
        this.isDead = false;
        this.prescriptions = new ArrayList<>();
    }

    @Override
    protected void addPrescription(Prescription prescription) {
        PrescriptionHibernate prescriptionHibernate = (PrescriptionHibernate) prescription;
        prescriptions.add(prescriptionHibernate);
    }

    @Override
    public void declareDead() {
        isDead = true;
    }

    @Override
    public Collection<Prescription> getPrescriptions() {
        return new ArrayList<Prescription>(prescriptions);
    }

    @Override
    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    protected PatientHibernate() {
        this(0, "");
    }
}
