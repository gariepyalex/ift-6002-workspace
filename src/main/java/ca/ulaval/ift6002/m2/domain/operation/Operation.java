package ca.ulaval.ift6002.m2.domain.operation;

import java.util.NoSuchElementException;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;

public abstract class Operation {

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

    public int getNumber() {
        return operationData.getNumber();
    }

    public OperationData getData() {
        return operationData;
    }

    public boolean hasInstruments() {
        return operationData.hasInstruments();
    }

    protected abstract boolean isInstrumentElligible(Instrument instrument);
}
