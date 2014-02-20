package ca.ulaval.ift6002.m2.domain.instrument;

import java.util.Objects;

public class Instrument {

    private InstrumentStatus status;
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

    public void setStatus(InstrumentStatus status) {
        this.status = status;
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
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Instrument)) {
            return false;
        }

        Instrument instrument = (Instrument) obj;
        return !isAnonymous() && serial.equals(instrument.serial);
    }

    @Override
    public String toString() {
        return "[" + status + "] Serial:" + serial;
    }

    public void setStatus(String status) {
        // TODO Auto-generated method stub
        return;
    }
}
