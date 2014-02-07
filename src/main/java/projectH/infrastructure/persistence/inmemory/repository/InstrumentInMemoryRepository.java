package projectH.infrastructure.persistence.inmemory.repository;

import projectH.domain.instrument.Instrument;
import projectH.domain.instrument.InstrumentRepository;

public class InstrumentInMemoryRepository implements InstrumentRepository {

	private boolean isEmpty = true;

	@Override
	public void saveInstrument(String noIntervention, Instrument instrument) {
		isEmpty = false;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

}
