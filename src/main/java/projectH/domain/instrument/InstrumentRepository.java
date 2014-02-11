package projectH.domain.instrument;

public interface InstrumentRepository {

	public void save(String noIntervention, Instrument instrument);

	public Instrument findBySerial(String serial);

	public boolean containsSerial(String serial);
}
