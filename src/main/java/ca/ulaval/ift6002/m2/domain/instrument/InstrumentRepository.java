package ca.ulaval.ift6002.m2.domain.instrument;

public interface InstrumentRepository {

    public void save(String noIntervention, Instrument instrument);

    public Instrument findBySerial(String serial);

    public boolean containsSerial(String serial);
}
