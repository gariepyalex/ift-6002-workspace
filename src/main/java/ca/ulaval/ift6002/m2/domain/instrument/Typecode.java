package ca.ulaval.ift6002.m2.domain.instrument;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Typecode {

    private final String value;

    public Typecode(String value) {
        this.value = value;
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
        Typecode other = (Typecode) obj;
        return new EqualsBuilder().append(value, other.value).isEquals();
    }

    @Override
    public String toString() {
        return value;
    }
}
