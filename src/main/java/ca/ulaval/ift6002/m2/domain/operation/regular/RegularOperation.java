package ca.ulaval.ift6002.m2.domain.operation.regular;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.patient.Patient;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.operation.surgeon.Surgeon;

public abstract class RegularOperation extends Operation {

    public RegularOperation(String description, Surgeon surgeon, Date date, Room room, OperationStatus status,
            Patient patient) {
        super(description, surgeon, date, room, status, patient);
    }

    public RegularOperation(String description, Surgeon surgeon, Date date, Room room, OperationStatus status,
            Patient patient, Integer number) {
        super(description, surgeon, date, room, status, patient, number);
    }

    @Override
    protected boolean isInstrumentElligible(Instrument instrument) {
        return true;
    }

}
