package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.application.validator.response.InvalidResponseException;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.operation.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;

@RunWith(MockitoJUnitRunner.class)
public class OperationResponseAssemblerTest {

    private static final String INVALID_STATUS = "Vivant";
    private static final String INVALID_TYPE = "House";
    private static final int PATIENT_NUMBER = 3;
    private static final String ROOM = "une room";
    private static final String DATE_AS_STRING = "2013-10-10T10:10:10";
    private static final int SURGEON_NUMBER = 2;
    private static final String RANDOM_DESCRIPTOR = "random descriptor";
    private static final String TYPE = "oeil";
    private static final String STATUS = "planifiee";
    private static final Date DATE = new Date(1220227200L * 1000);

    @Mock
    private OperationFactory operationFactory;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private DateFormatter formatterDate;

    @InjectMocks
    OperationResponseAssembler operationAssembler;

    private final OperationResponse operationResponse = new OperationResponse(RANDOM_DESCRIPTOR, SURGEON_NUMBER,
            DATE_AS_STRING, ROOM, TYPE, STATUS, PATIENT_NUMBER);

    @Before
    public void setUp() {
        willReturn(DATE).given(formatterDate).parse(DATE_AS_STRING);
    }

    @Test
    public void whenFromResponseCallPatientRepositoryGetShouldBeCall() throws InvalidResponseException {
        operationAssembler.fromResponse(operationResponse);

        verify(patientRepository).get(PATIENT_NUMBER);
    }

    @Test
    public void whenFromResponseCallFormatterDateParseShouldBeCall() throws InvalidResponseException {
        operationAssembler.fromResponse(operationResponse);

        verify(formatterDate).parse(DATE_AS_STRING);

    }

    @Test
    public void whenFromResponseCallFactoryCreateShouldBeCalled() throws InvalidResponseException {
        OperationResponse response = new OperationResponse(RANDOM_DESCRIPTOR, SURGEON_NUMBER, DATE_AS_STRING, ROOM,
                TYPE, STATUS, PATIENT_NUMBER);
        operationAssembler.fromResponse(response);
        verify(operationFactory).create(any(OperationType.class), any(String.class), any(Surgeon.class),
                any(Date.class), any(Room.class), any(OperationStatus.class), any(Patient.class));
    }

    @Test(expected = InvalidResponseException.class)
    public void whenFromResponseCallWithInvalidTypeShouldThrowException() throws InvalidResponseException {
        OperationResponse response = new OperationResponse(RANDOM_DESCRIPTOR, SURGEON_NUMBER, DATE_AS_STRING, ROOM,
                INVALID_TYPE, STATUS, PATIENT_NUMBER);
        operationAssembler.fromResponse(response);
    }

    @Test(expected = InvalidResponseException.class)
    public void whenFromResponseCallWithInvalidStatusShouldThrowException() throws InvalidResponseException {
        OperationResponse response = new OperationResponse(RANDOM_DESCRIPTOR, SURGEON_NUMBER, DATE_AS_STRING, ROOM,
                TYPE, INVALID_STATUS, PATIENT_NUMBER);

        operationAssembler.fromResponse(response);
    }

    @Test
    public void whenFromResponseCallWithNoStatusStatusIsSetToEmpty() throws InvalidResponseException {
        OperationResponse response = new OperationResponse(RANDOM_DESCRIPTOR, SURGEON_NUMBER, DATE_AS_STRING, ROOM,
                TYPE, null, PATIENT_NUMBER);

        operationAssembler.fromResponse(response);

        assertEquals("", response.status);
    }
}
