package ca.ulaval.ift6002.m2.application.assemblers;

import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;

public class InstrumentResponseAssembler {

    public Instrument fromResponse(InstrumentResponse response) {
        InstrumentStatus status = InstrumentStatus.determineFrom(response.status);
        Typecode typecode = new Typecode(response.typecode);
        Serial serial = new Serial(response.serial);

        return new Instrument(typecode, status, serial);
    }
}
