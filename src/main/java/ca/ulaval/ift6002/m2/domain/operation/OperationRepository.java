package ca.ulaval.ift6002.m2.domain.operation;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;

public interface OperationRepository {

    void store(Operation operation);

    Operation get(int operationId);

    void storeInstrument(Operation operation, Instrument instrument);
}
