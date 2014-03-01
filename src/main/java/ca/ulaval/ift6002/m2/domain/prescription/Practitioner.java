package ca.ulaval.ift6002.m2.domain.prescription;

import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

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
        if (obj == null) {
            return false;
        }
        if (obj instanceof Practitioner) {
            Practitioner other = (Practitioner) obj;
            return new EqualsBuilder().append(name, other.name).isEquals();
        } else {
            return false;
        }

    }

    @Override
    public String toString() {
        return name;
    }
}
