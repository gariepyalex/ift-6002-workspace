package ca.ulaval.ift6002.m2.domain.operation.restricted;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

public abstract class RestrictedOperation extends Operation {

    protected RestrictedOperation(String description, Surgeon surgeon, Date date, Room room, OperationStatus status,
            Patient patient) {
        super(description, surgeon, date, room, status, patient);
    }

    @Override
    public void add(Instrument instrument) {
        if (!isInstrumentElligible(instrument)) {
            throw new InvalidInstrumentException("Instrument " + instrument + " is not elligible.");
        }
        super.add(instrument);
    }

    private boolean isInstrumentElligible(Instrument instrument) {
        return !instrument.isAnonymous();
    }

}
