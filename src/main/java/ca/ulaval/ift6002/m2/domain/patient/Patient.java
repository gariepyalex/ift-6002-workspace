package ca.ulaval.ift6002.m2.domain.patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class Patient {

    private final int number;
    private final Collection<Prescription> prescriptions;

    public Patient(int number) {
        this.number = number;
        this.prescriptions = new ArrayList<Prescription>();
    }

    public Patient(int number, Collection<Prescription> prescriptions) {
        this.number = number;
        this.prescriptions = prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
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

        return Objects.equals(number, other.number);
    }

    @Override
    public String toString() {
        return "Patient #" + String.valueOf(number);
    }
}
