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

    public Instrument(Typecode typecode, InstrumentStatus status) {
        this(typecode, status, new Serial(""));
    }

    public void setStatus(String status) {
        InstrumentStatus newStatus = InstrumentStatus.valueOf(status);
        this.status = newStatus;
    }

    public boolean isAnonymous() {
        return serial.isEmpty();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(status).append(serial).append(typecode).toHashCode();
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

    public String getStatus() {
        return this.status.toString();
    }

    public String getTypecode() {
        return this.typecode.toString();
    }

    public String getSerial() {
        return serial.toString();
    }
}
