package ca.ulaval.ift6002.m2.domain.instrument;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

<<<<<<< HEAD
    public void setStatus(String status) {
        InstrumentStatus newStatus = InstrumentStatus.valueOf(status);
        this.status = newStatus;
    }

=======
>>>>>>> f85816b5e378bceda8ea23ce2afacd0cdd5d7ec1
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
}
