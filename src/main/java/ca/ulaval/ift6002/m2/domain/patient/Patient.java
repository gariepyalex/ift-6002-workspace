package ca.ulaval.ift6002.m2.domain.patient;

import java.util.Collection;

import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionNotFoundException;

public abstract class Patient {

    public void receivesPrescription(Prescription prescription) {
        if (isDead()) {
            throw new DeadPatientException("A dead patient cannot receive a prescription.");
        }

        if (isPrescriptionInteractingWithCurrentPrescriptions(prescription)) {
            throw new OccuringInteractionException();
        }

        addPrescription(prescription);
    }

    private boolean isPrescriptionInteractingWithCurrentPrescriptions(Prescription otherPrescription) {
        for (Prescription prescription : getPrescriptions()) {
            if (prescription.isInteractingWith(otherPrescription)) {
                return true;
            }
        }

        return false;
    }

    public void consumesPrescription(int prescriptionNumber, Consumption consumption) {
        Prescription prescription = findPrescription(prescriptionNumber);
        prescription.addConsumption(consumption);
    }

    protected Prescription findPrescription(int prescriptionNumber) {
        for (Prescription prescription : getPrescriptions()) {
            if (prescription.hasNumber(prescriptionNumber)) {
                return prescription;
            }
        }

        throw new PrescriptionNotFoundException("No prescription found for number: " + prescriptionNumber);
    }

    protected abstract void addPrescription(Prescription prescription);

    public abstract boolean isDead();

    public abstract void declareDead();

    public abstract Collection<Prescription> getPrescriptions();

    public abstract String getHealthInsuranceNumber();

}
