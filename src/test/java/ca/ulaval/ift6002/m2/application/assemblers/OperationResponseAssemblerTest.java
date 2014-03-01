package ca.ulaval.ift6002.m2.application.assemblers;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;

public class OperationResponseAssemblerTest {

    private static final int PATIENT_NUMBER = 3;
    private static final String A_ROOM = "une room";
    private static final String A_DATE = "une date";
    private static final int SURGEON_NUMBER = 2;
    private static final String RANDOM_DESCRIPTOR = "random descriptor";

    private OperationResponseAssembler operationAssembler;
    private Operation anOperation;

    private final OperationResponse operationResponse = new OperationResponse(RANDOM_DESCRIPTOR, SURGEON_NUMBER,
            A_DATE, A_ROOM, OperationType.EYE, OperationStatus.PLANNED, PATIENT_NUMBER);

    @Before
    public void setUp() {
        operationAssembler = new OperationResponseAssembler();
    }

    @Test
    public void GivenOperationResponseOperationShouldBeReturn() {
        anOperation = operationAssembler.toResponse(operationResponse);
    }

}
