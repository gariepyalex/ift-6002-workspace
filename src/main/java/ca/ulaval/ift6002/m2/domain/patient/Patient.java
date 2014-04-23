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

        checkForInteraction(prescription);

        addPrescription(prescription);
    }

    private void checkForInteraction(Prescription newPrescription) {
        Collection<Prescription> prescriptions = getPrescriptions();
        for (Prescription prescription : prescriptions) {
            if (prescription.isInteractingWith(newPrescription)) {
                throw new OccuringInteractionException();
            }
        }
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
