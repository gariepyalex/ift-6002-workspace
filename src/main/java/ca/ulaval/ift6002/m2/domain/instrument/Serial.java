package ca.ulaval.ift6002.m2.domain.instrument;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Serial {

    private final String value;

    public Serial(String value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(value).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Serial other = (Serial) obj;
        return new EqualsBuilder().append(value, other.value).isEquals();
    }

    @Override
    public String toString() {
        return value;
    }

}
