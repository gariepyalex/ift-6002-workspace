package ca.ulaval.ift6002.m2.domain.instrument;

import java.util.Objects;

public class Typecode {

    private final String value;

    public Typecode(String value) {
        this.value = value;
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

        if (!(obj instanceof Typecode)) {
            return false;
        }

        Typecode other = (Typecode) obj;

        return Objects.equals(value, other.value);
    }

}
