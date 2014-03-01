package ca.ulaval.ift6002.m2.application.validator.response;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;

public class OperationResponseValidatorTest {

    private static final String VALID_DESCRIPTION = "description";
    private static final String EMPTY = "";
    private static final int SURGEON_NUMBER = 101224;
    private static final String DATE = "0000-00-00T24:01:00";
    private static final String ROOM = "blocB";
    private static final Integer PATIENT_NUMBER = 1234;

    private OperationResponseValidator operationResponseValidator;
    private OperationResponse operationResponse;

    @Before
    public void setUp() {
        operationResponseValidator = new OperationResponseValidator();
    }

    @Test(expected = InvalidResponseException.class)
    public void whenValidateIfDescriptionIsEmptyShouldThrowInvalidResponseException() throws InvalidResponseException {
        operationResponse = new OperationResponse(EMPTY, SURGEON_NUMBER, DATE, ROOM, OperationType.EYE,
                OperationStatus.PLANNED, PATIENT_NUMBER);

        operationResponseValidator.validate(operationResponse);
    }
}
