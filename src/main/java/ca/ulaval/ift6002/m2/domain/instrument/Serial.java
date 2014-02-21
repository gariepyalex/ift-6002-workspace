package ca.ulaval.ift6002.m2.domain.instrument;

import java.util.Objects;

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
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Serial)) {
            return false;
        }

        Serial other = (Serial) obj;

        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return value;
    }

}
