package ca.ulaval.ift6002.m2.domain.instrument;


public abstract class Instrument {

    public Instrument() {
    }

    public boolean hasASerial() {
        return !isAnonymous();
    }

    public boolean hasSameSerial(Instrument other) {
        return hasSerial(other.getSerial());
    }

    public boolean isAnonymous() {
        return getSerial().isEmpty();
    }

    public boolean hasSerial(Serial serial) {
        return getSerial().equals(serial);
    }

    public void changeTo(InstrumentStatus status) {
        setStatus(status);
    }

    public abstract InstrumentStatus getStatus();

    protected abstract void setStatus(InstrumentStatus status);

    public abstract Typecode getTypecode();

    public abstract Serial getSerial();

}
