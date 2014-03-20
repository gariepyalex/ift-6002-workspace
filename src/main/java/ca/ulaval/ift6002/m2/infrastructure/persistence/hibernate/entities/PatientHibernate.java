package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionNotFoundException;

@Entity
public class PatientHibernate extends Patient {

    @Id
    public Integer number;

    @OneToMany(cascade = { CascadeType.ALL })
    // TODO use prescription hibernate
    public Collection<Prescription> prescriptions;
    public String healthInsuranceNumber;
    public Boolean isDead;

    public PatientHibernate(Integer number, String healthInsuranceNumber) {
        this(number, healthInsuranceNumber, new ArrayList<Prescription>());
    }

    public PatientHibernate(Integer number, String healthInsuranceNumber, Collection<Prescription> prescriptions) {
        this.number = number;
        this.prescriptions = prescriptions;
        this.healthInsuranceNumber = healthInsuranceNumber;
        this.isDead = false;
    }

    @Override
    protected void addPrescription(Prescription prescription) {
        // TODO cast in PrescriptionHibernate
        prescriptions.add(prescription);
    }

    @Override
    public int countPrescriptions() {
        return prescriptions.size();
    }

    @Override
    public void declareDead() {
        isDead = true;
    }

    @Override
    public Collection<Prescription> getPrescriptions() {
        // TODO Convert into domain prescription
        return prescriptions;
    }

    @Override
    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    protected Prescription findPrescription(int prescriptionNumber) {
        // TODO do something with HibernatePrescription
        for (Prescription prescription : prescriptions) {
            if (prescription.hasNumber(prescriptionNumber)) {
                return prescription;
            }
        }

        throw new PrescriptionNotFoundException("No prescription found for number: " + prescriptionNumber);
    }

    protected PatientHibernate() {
        this(0, "", new ArrayList<Prescription>());
    }
}
