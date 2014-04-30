package ca.ulaval.ift6002.m2.domain.surgery;

import java.util.NoSuchElementException;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;

public abstract class Surgery {

    private SurgeryData surgeryData;

    protected Surgery(SurgeryData surgeryData) {
        this.surgeryData = surgeryData;
    }

    public void bookmarkInstrumentToStatus(Serial serial, InstrumentStatus status) {
        Instrument instrument = findInstrument(serial);

        instrument.changeTo(status);
    }

    private Instrument findInstrument(Serial serial) {
        for (Instrument instrument : surgeryData.getInstruments()) {
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
        for (Instrument current : surgeryData.getInstruments()) {
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

        surgeryData.addInstrument(instrument);
    }

    public int getNumber() {
        return surgeryData.getNumber();
    }

    public SurgeryData getData() {
        return surgeryData;
    }

    public boolean hasInstruments() {
        return surgeryData.hasInstruments();
    }

    public boolean hasStatus(SurgeryStatus status) {
        return surgeryData.getStatus().equals(status);
    }

    protected abstract boolean isInstrumentElligible(Instrument instrument);
}
