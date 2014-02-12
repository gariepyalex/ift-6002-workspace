package ca.ulaval.ift6002.m2.domain.instrument;

public interface InstrumentRepository {

	void save(String noIntervention, Instrument instrument);

	Instrument findBySerial(String serial);

	boolean containsSerial(String serial);
}
