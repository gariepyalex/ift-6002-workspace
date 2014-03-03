package ca.ulaval.ift6002.m2.domain.instrument;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Instrument {

    private InstrumentStatus status;
    private final Serial serial;
    private final Typecode typecode;

    public Instrument(Typecode typecode, InstrumentStatus status, Serial serialNumber) {
        this.status = status;
        this.typecode = typecode;
        this.serial = serialNumber;
    }

    public boolean isAnonymous() {
        return serial.isEmpty();
    }

    public boolean hasSerial(Serial serial) {
        return this.serial.equals(serial);
    }

    public void changeTo(InstrumentStatus status) {
        this.status = status;
    }

    public InstrumentStatus getStatus() {
        return status;
    }

    public Typecode getTypecode() {
        return typecode;
    }

    public Serial getSerial() {
        return serial;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return "[" + status + "] Serial:" + serial;
    }
}
