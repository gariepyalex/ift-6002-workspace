package ca.ulaval.ift6002.m2.domain.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;
import ca.ulaval.ift6002.m2.domain.patient.Patient;

public abstract class Operation {

    private final String description;
    private final Surgeon surgeon;
    private final Date date;
    private final Room room;
    private final OperationStatus status;
    private final Patient patient;
    private final List<Instrument> instruments;

    // Builder implementation suggested by Joshua Bloch in Effective Java
    public static class Builder {
        private String description;
        private Surgeon surgeon;
        private Date date;
        private Room room;
        private final OperationType operationType;
        private OperationStatus status;
        private Patient patient;

        // Mandatory parameters
        public Builder(OperationType operationType) {
            this.operationType = operationType;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder surgeon(Surgeon surgeon) {
            this.surgeon = surgeon;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder room(Room room) {
            this.room = room;
            return this;
        }

        public Builder status(OperationStatus status) {
            this.status = status;
            return this;
        }

        public Builder patient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public Operation build() {
            if (operationType.isCarefulOperationType()) {
                return new DangerousOperation(this);
            } else {
                return new RegularOperation(this);
            }
        }
    }

    protected Operation(Builder builder) {
        this.description = builder.description;
        this.surgeon = builder.surgeon;
        this.date = builder.date;
        this.room = builder.room;
        this.status = builder.status;
        this.patient = builder.patient;
        this.instruments = new ArrayList<>();
    }

    public boolean hasInstrument(Instrument instrument) {
        return instruments.contains(instrument);
    }

    public int countInstruments() {
        return instruments.size();
    }

    public void addInstrument(Instrument instrument) {
        if (hasInstrument(instrument)) {
            throw new InvalidInstrumentException("This instrument is invalid: " + instrument);
        }

        if (!isInstrumentElligibleToOperation(instrument)) {
            throw new InvalidInstrumentException("Instrument " + instrument + " is not elligible.");
        }

        instruments.add(instrument);
    }

    protected abstract boolean isInstrumentElligibleToOperation(Instrument instrument);
}
