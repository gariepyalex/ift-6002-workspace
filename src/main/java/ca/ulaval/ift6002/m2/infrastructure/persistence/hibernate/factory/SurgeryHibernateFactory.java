package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.factory;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryData;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryFactory;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryStatus;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryType;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.SurgeryHibernateData;

public class SurgeryHibernateFactory extends SurgeryFactory {

    @Override
    public SurgeryData createSurgeryData(SurgeryType type, String description, Surgeon surgeon, Date date,
            Room room, SurgeryStatus status, Patient patient) {
        return new SurgeryHibernateData(description, surgeon, date, room, status, patient, type);
    }

}
