package ca.ulaval.ift6002.m2.domain.operation.regular;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
<<<<<<< HEAD
=======
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
>>>>>>> e6774234509697e8778ceb56ec81660f8d8ded2e
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

public abstract class RegularOperation extends Operation {

    public RegularOperation(String description, Surgeon surgeon, Date date, Room room, OperationStatus status,
            Patient patient) {
        super(description, surgeon, date, room, status, patient);
    }

    @Override
    protected boolean isInstrumentElligible(Instrument instrument) {
        return true;
    }

}
