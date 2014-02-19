package ca.ulaval.ift6002.m2.domain.instrument;

import ca.ulaval.ift6002.m2.domain.operation.Operation;

public interface InstrumentRepository {

    /**
     * TODO should it be moved to OperationRepository? You could get the
     * operation via it noIntervention, then add all your instruments and store
     * it back to OperationRepository
     */
    void store(Operation operation, Instrument instrument);

    Instrument get(String serial);

    boolean contains(String serial);
}
