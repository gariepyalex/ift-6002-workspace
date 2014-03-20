package ca.ulaval.ift6002.m2.domain.patient;

import java.util.Collection;

import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public abstract class Patient {

    public void receivesPrescription(Prescription prescription) {
        if (isDead()) {
            throw new DeadPatientException("A dead patient cannot receives a prescription.");
        }

        addPrescription(prescription);
    }

    public void consumesPrescription(int prescriptionNumber, Consumption consumption) {
        Prescription prescription = findPrescription(prescriptionNumber);
        prescription.addConsumption(consumption);
    }

    protected abstract void addPrescription(Prescription prescription);

    public abstract int countPrescriptions();

    public abstract boolean isDead();

    public abstract void declareDead();

    public abstract Collection<Prescription> getPrescriptions();

    public abstract String getHealthInsuranceNumber();

    protected abstract Prescription findPrescription(int prescriptionNumber);

}
