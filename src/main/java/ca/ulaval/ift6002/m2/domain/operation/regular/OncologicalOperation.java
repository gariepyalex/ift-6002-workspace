package ca.ulaval.ift6002.m2.domain.operation.regular;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.operation.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.patient.Patient;

public class OncologicalOperation extends RegularOperation {

    public OncologicalOperation(String description, Surgeon surgeon, Date date, Room room, OperationStatus status,
            Patient patient) {
        super(description, surgeon, date, room, status, patient);
    }

    public OncologicalOperation(String description, Surgeon surgeon, Date date, Room room, OperationStatus status,
            Patient patient, Integer number) {
        super(description, surgeon, date, room, status, patient, number);
    }

    @Override
    public OperationType getType() {
        return OperationType.ONCOLOGY;
    }
}
