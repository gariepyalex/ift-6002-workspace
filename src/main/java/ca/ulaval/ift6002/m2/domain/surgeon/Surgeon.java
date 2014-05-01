package ca.ulaval.ift6002.m2.domain.surgeon;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Surgeon {

    private final int license;

    public Surgeon(int license) {
        this.license = license;
    }

    public int getLicense() {
        return license;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
