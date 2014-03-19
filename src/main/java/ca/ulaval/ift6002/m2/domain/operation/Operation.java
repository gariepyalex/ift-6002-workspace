package ca.ulaval.ift6002.m2.domain.operation;

import java.util.Collection;
import java.util.NoSuchElementException;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;

public abstract class Operation {

    public static final int EMPTY_NUMBER = -1;

    private OperationData operationData;

    protected Operation(OperationData operationData) {
        this.operationData = operationData;
    }

    public void bookmarkInstrumentToStatus(Serial serial, InstrumentStatus status) {
        Instrument instrument = findInstrument(serial);

        instrument.changeTo(status);
    }

    private Instrument findInstrument(Serial serial) {
        for (Instrument instrument : operationData.getInstruments()) {
            if (instrument.hasSerial(serial)) {
                return instrument;
            }
        }

        throw new NoSuchElementException("There are no instrument corresponding to: " + serial);
    }

    public boolean has(Instrument instrument) {
        if (instrument.hasASerial()) {
            if (hasAlreadySerial(instrument)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasAlreadySerial(Instrument instrument) {
        for (Instrument current : operationData.getInstruments()) {
            if (instrument.hasSameSerial(current)) {
                return true;
            }
        }

        return false;
    }

    public void add(Instrument instrument) {
        if (has(instrument)) {
            throw new IllegalStateException("Instrument with same serial already exists: " + instrument);
        }

        if (!isInstrumentElligible(instrument)) {
            throw new InvalidInstrumentException("Instrument '" + instrument + "' is not elligible");
        }

        operationData.addInstrument(instrument);
    }

    public void add(Collection<Instrument> instruments) {
        for (Instrument instrument : instruments) {
            add(instrument);
        }
    }

    public int getNumber() {
        return operationData.getNumber();
    }

    public OperationData getData() {
        return operationData;
    }

    protected abstract boolean isInstrumentElligible(Instrument instrument);

    // TODO Remove this useless method
    public abstract OperationType getType();

    public void updateNumber(int number) {
        // TODO Remove this useless method
        // this.number = number;
    }

    public boolean hasInstruments() {
        return operationData.hasInstruments();
    }

    public boolean hasNumber() {
        // TODO REmove this useless method
        return true; // number != EMPTY_NUMBER;
    }
}
