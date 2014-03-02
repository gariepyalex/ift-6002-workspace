package ca.ulaval.ift6002.m2.domain.surgeon;

import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Surgeon {

    private final int license;

    public Surgeon(int license) {
        this.license = license;
    }

    @Override
    public int hashCode() {
        return Objects.hash(license);
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
        Surgeon other = (Surgeon) obj;
        return new EqualsBuilder().append(license, other.license).isEquals();
    }
}
