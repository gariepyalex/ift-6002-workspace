package ca.ulaval.ift6002.m2.domain.prescription;

import java.util.Objects;

public class Practitioner {

    private final String name;

    public Practitioner(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Practitioner)) {
            return false;
        }

        Practitioner other = (Practitioner) obj;

        return Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
