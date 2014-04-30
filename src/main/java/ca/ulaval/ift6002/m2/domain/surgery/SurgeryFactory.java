package ca.ulaval.ift6002.m2.domain.surgery;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.surgery.regular.OncologicalSurgery;
import ca.ulaval.ift6002.m2.domain.surgery.regular.OtherSurgery;
import ca.ulaval.ift6002.m2.domain.surgery.restricted.EyeSurgery;
import ca.ulaval.ift6002.m2.domain.surgery.restricted.HeartSurgery;
import ca.ulaval.ift6002.m2.domain.surgery.restricted.MarrowSurgery;

public abstract class SurgeryFactory {

    public Surgery create(SurgeryType type, SurgeryData surgeryData) {
        if (type == SurgeryType.EYE) {
            return new EyeSurgery(surgeryData);
        } else if (type == SurgeryType.HEART) {
            return new HeartSurgery(surgeryData);
        } else if (type == SurgeryType.MARROW) {
            return new MarrowSurgery(surgeryData);
        } else if (type == SurgeryType.ONCOLOGY) {
            return new OncologicalSurgery(surgeryData);
        } else {
            return new OtherSurgery(surgeryData);
        }
    }

    public Surgery create(SurgeryType type, String description, Surgeon surgeon, Date date, Room room,
            SurgeryStatus status, Patient patient) {
        SurgeryData surgeryData = createSurgeryData(type, description, surgeon, date, room, status, patient);
        return create(type, surgeryData);
    }

    public abstract SurgeryData createSurgeryData(SurgeryType type, String description, Surgeon surgeon,
            Date date, Room room, SurgeryStatus status, Patient patient);
}
