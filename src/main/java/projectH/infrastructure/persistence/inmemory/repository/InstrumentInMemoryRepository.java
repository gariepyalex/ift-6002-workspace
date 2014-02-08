package projectH.infrastructure.persistence.inmemory.repository;

import projectH.domain.instrument.Instrument;
import projectH.domain.instrument.InstrumentRepository;

// TODO: To be implemented and tested
public class InstrumentInMemoryRepository implements InstrumentRepository {

	private boolean isEmpty = true;

	@Override
	public void saveInstrument(String noIntervention, Instrument instrument) {
		isEmpty = false;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	@Override
	public Instrument findInstrumentBySerial(String serial) {
		return null;
	}

	@Override
	public boolean containSerial(String serial) {
		return false;
	}

}
