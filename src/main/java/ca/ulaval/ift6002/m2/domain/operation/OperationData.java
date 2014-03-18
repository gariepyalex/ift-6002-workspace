package ca.ulaval.ift6002.m2.domain.operation;

import java.util.Collection;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;

public interface OperationData {

    int getNumber();

    Collection<Instrument> getInstruments();

    void addInstrument(Instrument instrument);

    boolean hasInstrument();
}
