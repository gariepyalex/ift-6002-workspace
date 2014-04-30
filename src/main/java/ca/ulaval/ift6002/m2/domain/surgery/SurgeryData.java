package ca.ulaval.ift6002.m2.domain.surgery;

import java.util.Collection;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;

public interface SurgeryData {

    int getNumber();

    Collection<Instrument> getInstruments();

    void addInstrument(Instrument instrument);

    boolean hasInstruments();

    SurgeryStatus getStatus();
}
