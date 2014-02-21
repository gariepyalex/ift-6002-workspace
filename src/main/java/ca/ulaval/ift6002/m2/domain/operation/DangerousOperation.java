package ca.ulaval.ift6002.m2.domain.operation;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;

public class DangerousOperation extends Operation {

    protected DangerousOperation(Builder builder) {
        super(builder);
    }

    @Override
    protected boolean isInstrumentElligibleToOperation(Instrument instrument) {
        return false;
    }

}
