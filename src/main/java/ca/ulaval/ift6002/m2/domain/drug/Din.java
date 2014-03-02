package ca.ulaval.ift6002.m2.domain.drug;

import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Din {

    private final String value;

    public Din(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
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
        Din other = (Din) obj;
        return new EqualsBuilder().append(value, other.value).isEquals();
    }

    @Override
    public String toString() {
        return value;
    }
}
