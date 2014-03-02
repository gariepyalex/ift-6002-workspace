package ca.ulaval.ift6002.m2.application.validator.response;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;

public class OperationResponseValidatorTest {

    private static final String VALID_DESCRIPTION = "description";
    private static final String EMPTY = "";
    private static final int SURGEON_NUMBER = 101224;
    private static final String DATE = "0000-00-00T24:01:00";
    private static final String VALID_ROOM = "blocB";
    private static final Integer PATIENT_NUMBER = 1234;
    private static final String TYPE = "eye";
    private static final String STATUS = "planned";

    private OperationResponseValidator operationResponseValidator;
    private OperationResponse operationResponse;

    @Before
    public void setUp() {
        operationResponseValidator = new OperationResponseValidator();
    }

    @Test(expected = InvalidResponseException.class)
    public void whenValidateIfDescriptionIsEmptyShouldThrowInvalidResponseException() throws InvalidResponseException {
        operationResponse = new OperationResponse(EMPTY, SURGEON_NUMBER, DATE, VALID_ROOM, TYPE, STATUS, PATIENT_NUMBER);

        operationResponseValidator.validate(operationResponse);
    }

    @Test(expected = InvalidResponseException.class)
    public void whenValidateIfRoomIsEmptyShouldThrowInvalidResponseException() throws InvalidResponseException {
        operationResponse = new OperationResponse(VALID_DESCRIPTION, SURGEON_NUMBER, DATE, EMPTY, TYPE, STATUS,
                PATIENT_NUMBER);

        operationResponseValidator.validate(operationResponse);
    }
}
