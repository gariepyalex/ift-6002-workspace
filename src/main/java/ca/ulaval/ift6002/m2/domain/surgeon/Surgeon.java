package ca.ulaval.ift6002.m2.domain.surgeon;

import java.util.Objects;

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

        if (!(obj instanceof Surgeon)) {
            return false;
        }

        Surgeon other = (Surgeon) obj;

        return Objects.equals(license, other.license);
    }
}
