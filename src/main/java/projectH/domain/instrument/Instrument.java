package projectH.domain.instrument;

import java.util.Objects;

public class Instrument {

    private InstrumentStatus status;
    private final String serial;
    private final String typecode;

    public Instrument(String typecode, InstrumentStatus status) {
        this(typecode, status, "");
    }

    public Instrument(String typecode, InstrumentStatus status, String serialNumber) {
        if (typecode == null || typecode.isEmpty()) {
            throw new IllegalArgumentException("Typecode cannot be empty");
        }
        if (serialNumber == null) {
            throw new IllegalArgumentException("Serial number cannot be 'null'");
        }

        this.status = status;
        this.typecode = typecode;
        this.serial = serialNumber;
    }

    public InstrumentStatus getStatus() {
        return status;
    }

    public void setStatus(String status) {
        // TODO it might be moved to a "service"
        InstrumentStatus statusFound = InstrumentStatus.valueOf(status);

        setStatus(statusFound);
    }

    public void setStatus(InstrumentStatus status) {
        this.status = status;
    }

    public String getSerial() {
        return serial;
    }

    public boolean isAnonymous() {
        return serial.isEmpty();
    }

    public String getTypecode() {
        return typecode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, serial, typecode);
    }

    @Override
    public boolean equals(Object obj) {
        Instrument instrument = (Instrument) obj;
        return !isAnonymous() && this.serial.equals(instrument.serial);
    }
}
