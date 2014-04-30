package ca.ulaval.ift6002.m2.domain.surgery;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.surgery.regular.OncologicalSurgery;
import ca.ulaval.ift6002.m2.domain.surgery.regular.OtherSurgery;
import ca.ulaval.ift6002.m2.domain.surgery.restricted.EyeSurgery;
import ca.ulaval.ift6002.m2.domain.surgery.restricted.HeartSurgery;
import ca.ulaval.ift6002.m2.domain.surgery.restricted.MarrowSurgery;

@RunWith(MockitoJUnitRunner.class)
public class SurgeryFactoryTest {

    private static final SurgeryType A_TYPE = SurgeryType.EYE;
    private static final String A_DESCRIPTION = "description";
    private static final Surgeon A_SURGEON = new Surgeon(12345);
    private static final Date A_DATE = new Date();
    private static final Room A_ROOM = new Room("room");
    private static final SurgeryStatus A_STATUS = SurgeryStatus.IN_PROGRESS;
    private static final Patient A_PATIENT = null;

    @Mock
    private SurgeryData surgeryData;

    private SurgeryFactory surgeryFactory;

    @Before
    public void setUp() {
        surgeryFactory = mock(SurgeryFactory.class, CALLS_REAL_METHODS);
    }

    @Test
    public void whenTypeIsEyeShouldReturnEyeSurgery() {
        Surgery surgery = surgeryFactory.create(SurgeryType.EYE, surgeryData);

        assertEquals(EyeSurgery.class, surgery.getClass());
    }

    @Test
    public void whenTypeIsHeartShouldReturnHeartSurgery() {
        Surgery surgery = surgeryFactory.create(SurgeryType.HEART, surgeryData);

        assertEquals(HeartSurgery.class, surgery.getClass());
    }

    @Test
    public void whenTypeIsMarrowShouldReturnMarrowSurgery() {
        Surgery surgery = surgeryFactory.create(SurgeryType.MARROW, surgeryData);

        assertEquals(MarrowSurgery.class, surgery.getClass());
    }

    @Test
    public void whenTypeIsOncolyShouldReturnOncologicalSurgery() {
        Surgery surgery = surgeryFactory.create(SurgeryType.ONCOLOGY, surgeryData);

        assertEquals(OncologicalSurgery.class, surgery.getClass());
    }

    @Test
    public void whenTypeIsOtherShouldReturnOtherSurgery() {
        Surgery surgery = surgeryFactory.create(SurgeryType.OTHER, surgeryData);

        assertEquals(OtherSurgery.class, surgery.getClass());
    }

    @Test
    public void whenCreateWithManyArgumentsShouldCreateASurgeryData() {
        willReturn(surgeryData).given(surgeryFactory).createSurgeryData(A_TYPE, A_DESCRIPTION, A_SURGEON, A_DATE,
                A_ROOM, A_STATUS, A_PATIENT);
        surgeryFactory.create(A_TYPE, A_DESCRIPTION, A_SURGEON, A_DATE, A_ROOM, A_STATUS, A_PATIENT);
        verify(surgeryFactory).createSurgeryData(A_TYPE, A_DESCRIPTION, A_SURGEON, A_DATE, A_ROOM, A_STATUS, A_PATIENT);
    }

}
