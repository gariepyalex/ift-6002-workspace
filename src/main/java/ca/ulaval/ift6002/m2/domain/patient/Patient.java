package ca.ulaval.ift6002.m2.domain.patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

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
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Patient other = (Patient) obj;
        return new EqualsBuilder().append(number, other.number).append(prescriptions, other.prescriptions).isEquals();

    }

}
