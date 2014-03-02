package ca.ulaval.ift6002.m2.domain.instrument;

import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Instrument {

    private final InstrumentStatus status;
    private final Serial serial;
    private final Typecode typecode;

    public Instrument(Typecode typecode, InstrumentStatus status, Serial serialNumber) {
        this.status = status;
        this.typecode = typecode;
        this.serial = serialNumber;
    }

    public Instrument(Typecode typecode, InstrumentStatus status) {
        this(typecode, status, new Serial(""));
    }

    public boolean isAnonymous() {
        return serial.isEmpty();
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, serial, typecode);
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
        Instrument other = (Instrument) obj;
        return new EqualsBuilder().append(isAnonymous(), false).append(serial, other.serial).isEquals();
    }

    @Override
    public String toString() {
        return "[" + status + "] Serial:" + serial;
    }

}
