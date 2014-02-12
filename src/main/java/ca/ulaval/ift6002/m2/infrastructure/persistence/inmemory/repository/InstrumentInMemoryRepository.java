package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentRepository;

// TODO: To be implemented and tested
public class InstrumentInMemoryRepository implements InstrumentRepository {

    private boolean isEmpty = true;

    @Override
    public void save(String noIntervention, Instrument instrument) {
        isEmpty = false;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public Instrument findBySerial(String serial) {
        return null;
    }

    @Override
    public boolean containsSerial(String serial) {
        return false;
    }

}
