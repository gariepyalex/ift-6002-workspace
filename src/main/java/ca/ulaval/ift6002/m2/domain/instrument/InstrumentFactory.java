package ca.ulaval.ift6002.m2.domain.instrument;

public interface InstrumentFactory {

    Instrument create(Typecode typecode, InstrumentStatus status, Serial serialNumber);
}
