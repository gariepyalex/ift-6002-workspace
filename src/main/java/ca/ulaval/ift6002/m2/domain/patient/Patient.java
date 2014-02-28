package ca.ulaval.ift6002.m2.domain.patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class Patient {

    private final int number;
    private final Collection<Prescription> prescriptions;

    public Patient(int number) {
        this(number, new ArrayList<Prescription>());
    }

    public Patient(int number, Collection<Prescription> prescriptions) {
        this.number = number;
        this.prescriptions = prescriptions;
    }

    public int getNumber() {
        return number;
    }

    public void receivesPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    public int countPrescriptions() {
        return prescriptions.size();
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, prescriptions);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Patient)) {
            return false;
        }

        Patient other = (Patient) obj;

        return Objects.equals(number, other.number) && Objects.equals(prescriptions, other.prescriptions);
    }

    @Override
    public String toString() {
        return "Patient #" + String.valueOf(number);
    }
}
