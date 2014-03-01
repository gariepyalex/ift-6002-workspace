package ca.ulaval.ift6002.m2.domain.operation;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;

public class DangerousOperation extends Operation {

    protected DangerousOperation(Builder builder) {
        super(builder);
    }

    @Override
    public void add(Instrument instrument) {
        if (has(instrument)) {
            throw new InvalidInstrumentException("This instrument is invalid: " + instrument);
        }

        if (!instrumentIsElligible(instrument)) {
            throw new InvalidInstrumentException("Instrument " + instrument + " is not elligible.");
        }

        instruments.add(instrument);
    }

    private boolean instrumentIsElligible(Instrument instrument) {
        return !instrument.isAnonymous();
    }

}
