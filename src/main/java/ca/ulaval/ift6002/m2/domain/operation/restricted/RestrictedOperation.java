package ca.ulaval.ift6002.m2.domain.operation.restricted;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationData;

public abstract class RestrictedOperation extends Operation {

    protected RestrictedOperation(OperationData operationData) {
        super(operationData);
    }

    @Override
    protected boolean isInstrumentElligible(Instrument instrument) {
        return instrument.hasASerial();
    }

}
