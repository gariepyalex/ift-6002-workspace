package ca.ulaval.ift6002.m2.application.validator.response;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;

public class OperationResponseValidatorTest {

    private static final String INVALID = "invalid";

    private static final String DESCRIPTION = "description";
    private static final int SURGEON_NUMBER = 101224;
    private static final String VALID_DATE = "0000-00-00T24:01:00";
    private static final String ROOM = "blocB";
    private static final Integer PATIENT_NUMBER = 1234;
    private static final String TYPE = "oeil";
    private static final String STATUS = "en_cours";

    private OperationResponseValidator operationResponseValidator;
    private OperationResponse operationResponse;

    @Before
    public void setUp() {
        operationResponseValidator = new OperationResponseValidator();
    }

    @Test(expected = InvalidResponseException.class)
    public void givenEmptyDescriptionWhenValidatingShouldThrowException() {
        operationResponse = new OperationResponse("", SURGEON_NUMBER, VALID_DATE, ROOM, TYPE, STATUS, PATIENT_NUMBER);

        operationResponseValidator.validate(operationResponse);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenNullSurgeonWhenValidatingShouldThrowException() {
        operationResponse = new OperationResponse(DESCRIPTION, null, VALID_DATE, ROOM, TYPE, STATUS, PATIENT_NUMBER);

        operationResponseValidator.validate(operationResponse);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenInvalidDateWhenValidatingShouldThrowException() {
        operationResponse = new OperationResponse(DESCRIPTION, SURGEON_NUMBER, INVALID, ROOM, TYPE, STATUS,
                PATIENT_NUMBER);

        operationResponseValidator.validate(operationResponse);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenEmptyRoomWhenValidatingShouldThrowException() {
        operationResponse = new OperationResponse(DESCRIPTION, SURGEON_NUMBER, VALID_DATE, "", TYPE, STATUS,
                PATIENT_NUMBER);

        operationResponseValidator.validate(operationResponse);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenEmptyTypeWhenValidatingShouldThrowException() {
        operationResponse = new OperationResponse(DESCRIPTION, SURGEON_NUMBER, VALID_DATE, ROOM, "", STATUS,
                PATIENT_NUMBER);

        operationResponseValidator.validate(operationResponse);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenNullPatientWhenValidatingShouldThrowException() {
        operationResponse = new OperationResponse(DESCRIPTION, SURGEON_NUMBER, VALID_DATE, ROOM, TYPE, STATUS, null);

        operationResponseValidator.validate(operationResponse);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenInvalidStatusWhenValidatingShouldThrowException() {
        operationResponse = new OperationResponse(DESCRIPTION, SURGEON_NUMBER, VALID_DATE, ROOM, TYPE, INVALID,
                PATIENT_NUMBER);

        operationResponseValidator.validate(operationResponse);
    }

    @Test
    public void givenValidResponseWhenValidatingShouldNotThrowException() {
        operationResponse = new OperationResponse(DESCRIPTION, SURGEON_NUMBER, VALID_DATE, ROOM, TYPE, STATUS,
                PATIENT_NUMBER);

        operationResponseValidator.validate(operationResponse);
    }
}
