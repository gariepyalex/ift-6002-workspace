package ca.ulaval.ift6002.m2.application.assemblers;

import org.junit.Test;
import org.junit.runner.RunWith;
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
    private static final String A_DATE = "2013-10-10T10:10:10";
    private static final int SURGEON_NUMBER = 2;
    private static final String RANDOM_DESCRIPTOR = "random descriptor";

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

    private OperationResponseAssembler operationAssembler;
    private Operation anOperation;

    private final OperationResponse operationResponse = new OperationResponse(RANDOM_DESCRIPTOR, SURGEON_NUMBER,
            A_DATE, A_ROOM, OperationType.EYE, OperationStatus.PLANNED, PATIENT_NUMBER);

    @Test
    public void whenToOperationCallPatientRepositoryGetShouldBeCall() {
        // willreturn
        // Date aDate = formatterDate1.parse(A_DATE);
        //
        // operationAssembler.toOperation(operationResponse);
        //
        // verify(patientRepository).get(PATIENT_NUMBER);
    }
    // @Test
    // public void whenToOperationCallASDFGetShouldBeCall() {
    // operationAssembler.toOperation(operationResponse);
    //
    // verify(patientRepository).get(PATIENT_NUMBER);
    // }

}
