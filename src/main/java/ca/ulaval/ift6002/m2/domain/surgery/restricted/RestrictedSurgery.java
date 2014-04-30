package ca.ulaval.ift6002.m2.domain.surgery.restricted;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.surgery.Surgery;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryData;

public abstract class RestrictedSurgery extends Surgery {

    protected RestrictedSurgery(SurgeryData surgeryData) {
        super(surgeryData);
    }

    @Override
    protected boolean isInstrumentElligible(Instrument instrument) {
        return instrument.hasASerial();
    }

}
