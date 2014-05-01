package ca.ulaval.ift6002.m2.domain.room;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Room {

    private final String value;

    public Room(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
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
