package ca.ulaval.ift6002.m2.domain.operation.regular;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationData;

public abstract class RegularOperation extends Operation {

    public RegularOperation(OperationData operationData) {
        super(operationData);
    }

    @Override
    protected boolean isInstrumentElligible(Instrument instrument) {
        return true;
    }

}
