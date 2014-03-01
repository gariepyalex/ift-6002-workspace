package ca.ulaval.ift6002.m2.domain.room;

import java.util.Objects;

public class Room {

    private final String value;

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

        if (!(obj instanceof Room)) {
            return false;
        }

        Room other = (Room) obj;

        return Objects.equals(value, other.value);
    }

}
