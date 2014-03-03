package ca.ulaval.ift6002.m2.domain.operation.restricted;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

public class EyeOperation extends RestrictedOperation {
    public EyeOperation(String description, Surgeon surgeon, Date date, Room room, OperationStatus status,
            Patient patient) {
        super(description, surgeon, date, room, status, patient);
    }

    @Override
    public OperationType getType() {
        return OperationType.EYE;
    }
}
