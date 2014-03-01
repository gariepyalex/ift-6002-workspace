package ca.ulaval.ift6002.m2.domain.operation;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

public class EyeOperation extends DangerousOperation {
    public EyeOperation(String description, Surgeon surgeon, Date date, Room room, OperationStatus status,
            Patient patient) {
        super(description, surgeon, date, room, status, patient);
    }
}
