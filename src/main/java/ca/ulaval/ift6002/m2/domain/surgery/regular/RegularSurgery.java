package ca.ulaval.ift6002.m2.domain.surgery.regular;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.surgery.Surgery;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryData;

public abstract class RegularSurgery extends Surgery {

    public RegularSurgery(SurgeryData surgeryData) {
        super(surgeryData);
    }

    @Override
    protected boolean isInstrumentElligible(Instrument instrument) {
        return true;
    }

}
