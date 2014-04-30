package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.factory;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryData;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryFactory;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryStatus;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryType;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.SurgeryHibernateData;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.PatientHibernate;

@RunWith(MockitoJUnitRunner.class)
public class SurgeryHibernateFactoryTest {

    private static final SurgeryType TYPE = SurgeryType.EYE;
    private static final SurgeryStatus STATUS = SurgeryStatus.PLANNED;
    private static final String DESCRIPTION = "description";
    private static final Surgeon SURGEON = new Surgeon(12345);
    private static final Room ROOM = new Room("room");
    private static final Date DATE = new Date();

    @Mock
    private PatientHibernate patient;

    private SurgeryFactory surgeryHibernateFactory;

    @Before
    public void setUp() {
        surgeryHibernateFactory = new SurgeryHibernateFactory();
    }

    @Test
    public void whenCreatingSurgeryDataShouldReturnAnHibernateInstance() {
        SurgeryData surgeryData = surgeryHibernateFactory.createSurgeryData(TYPE, DESCRIPTION, SURGEON, DATE,
                ROOM, STATUS, patient);

        assertEquals(SurgeryHibernateData.class, surgeryData.getClass());
    }
}
