package ca.ulaval.ift6002.m2.application.assemblers;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.requests.SurgeryRequest;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryFactory;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryStatus;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryType;

@RunWith(MockitoJUnitRunner.class)
public class SurgeryAssemblerTest {

    private static final String INVALID_STATUS = "Vivant";
    private static final String INVALID_TYPE = "House";
    private static final int PATIENT_NUMBER = 3;
    private static final String ROOM = "une room";
    private static final String DATE_AS_STRING = "2013-10-10T10:10:10";
    private static final int SURGEON_NUMBER = 2;
    private static final String RANDOM_DESCRIPTOR = "random descriptor";
    private static final String TYPE = "oeil";
    private static final String STATUS = "planifiee";
    private static final Date DATE = new Date();

    private final SurgeryRequest surgeryRequest = new SurgeryRequest(RANDOM_DESCRIPTOR, SURGEON_NUMBER,
            DATE_AS_STRING, ROOM, TYPE, STATUS, PATIENT_NUMBER);

    @Mock
    private SurgeryFactory surgeryFactory;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private DateFormatter formatterDate;

    @InjectMocks
    private SurgeryAssembler surgeryAssembler;

    @Before
    public void setUp() {
        willReturn(DATE).given(formatterDate).parse(DATE_AS_STRING);
    }

    @Test
    public void whenFromRequestShouldCallGetInPatientRepository() {
        surgeryAssembler.fromRequest(surgeryRequest);

        verify(patientRepository).get(PATIENT_NUMBER);
    }

    @Test
    public void whenFromRequestShouldCallParseInDateFormatter() {
        surgeryAssembler.fromRequest(surgeryRequest);

        verify(formatterDate).parse(DATE_AS_STRING);
    }

    @Test
    public void whenFromRequestShouldCallCreateInSurgeryFactory() {
        SurgeryRequest request = new SurgeryRequest(RANDOM_DESCRIPTOR, SURGEON_NUMBER, DATE_AS_STRING, ROOM, TYPE,
                STATUS, PATIENT_NUMBER);
        surgeryAssembler.fromRequest(request);
        verify(surgeryFactory).create(any(SurgeryType.class), anyString(), any(Surgeon.class), any(Date.class),
                any(Room.class), any(SurgeryStatus.class), any(Patient.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidTypeWhenFromRequestShouldThrowException() {
        SurgeryRequest request = new SurgeryRequest(RANDOM_DESCRIPTOR, SURGEON_NUMBER, DATE_AS_STRING, ROOM,
                INVALID_TYPE, STATUS, PATIENT_NUMBER);

        surgeryAssembler.fromRequest(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidStatusWhenFromRequestShouldThrowException() {
        SurgeryRequest request = new SurgeryRequest(RANDOM_DESCRIPTOR, SURGEON_NUMBER, DATE_AS_STRING, ROOM, TYPE,
                INVALID_STATUS, PATIENT_NUMBER);

        surgeryAssembler.fromRequest(request);
    }
}
