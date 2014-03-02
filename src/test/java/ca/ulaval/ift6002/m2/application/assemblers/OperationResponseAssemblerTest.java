package ca.ulaval.ift6002.m2.application.assemblers;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.room.RoomRepository;
import ca.ulaval.ift6002.m2.domain.surgeon.SurgeonRepository;

@RunWith(MockitoJUnitRunner.class)
public class OperationResponseAssemblerTest {

    private static final int PATIENT_NUMBER = 3;
    private static final String A_ROOM = "une room";
    private static final String ASTRING_DATE = "2013-10-10T10:10:10";
    private static final int SURGEON_NUMBER = 2;
    private static final String RANDOM_DESCRIPTOR = "random descriptor";
    private final static Date ADATE = new Date(1220227200L * 1000);

    @Mock
    OperationFactory operationFactory;
    @Mock
    PatientRepository patientRepository;
    @Mock
    SurgeonRepository surgeonRepository;
    @Mock
    RoomRepository roomRepository;
    @Mock
    DateFormatter formatterDate;

    @InjectMocks
    OperationResponseAssembler operationAssembler;

    private Operation anOperation;

    private final OperationResponse operationResponse = new OperationResponse(RANDOM_DESCRIPTOR, SURGEON_NUMBER,
            ASTRING_DATE, A_ROOM, OperationType.EYE, OperationStatus.PLANNED, PATIENT_NUMBER);

    @Before
    public void setUp() {
        willReturn(ADATE).given(formatterDate).parse(ASTRING_DATE);
    }

    @Test
    public void whenFromResponseCallPatientRepositoryGetShouldBeCall() {

        operationAssembler.fromResponse(operationResponse);

        verify(patientRepository).get(PATIENT_NUMBER);
    }

    @Test
    public void whenToOperationCallFormatterDateParseShouldBeCall() {

        operationAssembler.fromResponse(operationResponse);

        verify(formatterDate).parse(ASTRING_DATE);

    }

    @Test
    public void whenToOperationCallSurgeonRepositoryGetShouldBeCall() {
        operationAssembler.fromResponse(operationResponse);

        verify(surgeonRepository).get(SURGEON_NUMBER);
    }

    @Test
    public void whenToOperationCallRoomRepositoryGetShouldBeCall() {
        operationAssembler.fromResponse(operationResponse);

        verify(roomRepository).get(A_ROOM);

    }

}
