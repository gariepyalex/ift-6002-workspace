package ca.ulaval.ift6002.m2.domain.instrument;


public interface InstrumentRepository {

    Instrument get(int serial);

    void store(Instrument instrument);

}
