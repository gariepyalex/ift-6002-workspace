package ca.ulaval.ift6002.m2.domain.operation;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;

public class RegularOperation extends Operation {

    protected RegularOperation(Builder builder) {
        super(builder);
    }

    @Override
    protected boolean isInstrumentElligibleToOperation(Instrument instrument) {
        return !instrument.isAnonymous();
    }

}
