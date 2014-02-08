package projectH.domain.instrument;

public interface InstrumentRepository {
	public void saveInstrument(String noIntervention, Instrument instrument);

	public Instrument findInstrumentBySerial(String serial);

	public boolean containSerial(String serial);
}
