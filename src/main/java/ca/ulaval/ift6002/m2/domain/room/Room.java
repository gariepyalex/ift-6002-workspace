package ca.ulaval.ift6002.m2.domain.room;

import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Room {

    public final String value;

    public Room(String value) {
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
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Room other = (Room) obj;
        return new EqualsBuilder().append(value, other.value).isEquals();
    }

}
