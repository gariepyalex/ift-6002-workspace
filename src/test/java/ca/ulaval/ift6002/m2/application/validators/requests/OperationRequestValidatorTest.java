package ca.ulaval.ift6002.m2.application.validators.requests;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.requests.OperationRequest;
import ca.ulaval.ift6002.m2.application.validators.requests.InvalidRequestException;
import ca.ulaval.ift6002.m2.application.validators.requests.OperationRequestValidator;

public class OperationRequestValidatorTest {

    private static final String INVALID = "invalid";

    private static final String DESCRIPTION = "description";
    private static final int SURGEON_NUMBER = 101224;
    private static final String VALID_DATE = "0000-00-00T24:01:00";
    private static final String ROOM = "blocB";
    private static final Integer PATIENT_NUMBER = 1234;
    private static final String TYPE = "oeil";
    private static final String STATUS = "en_cours";

    private OperationRequestValidator operationRequestValidator;
    private OperationRequest operationRequest;

    @Before
    public void setUp() {
        operationRequestValidator = new OperationRequestValidator();
    }

    @Test(expected = InvalidRequestException.class)
    public void givenEmptyDescriptionWhenValidatingShouldThrowException() {
        operationRequest = new OperationRequest("", SURGEON_NUMBER, VALID_DATE, ROOM, TYPE, STATUS, PATIENT_NUMBER);

        operationRequestValidator.validate(operationRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenNullSurgeonWhenValidatingShouldThrowException() {
        operationRequest = new OperationRequest(DESCRIPTION, null, VALID_DATE, ROOM, TYPE, STATUS, PATIENT_NUMBER);

        operationRequestValidator.validate(operationRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenInvalidDateWhenValidatingShouldThrowException() {
        operationRequest = new OperationRequest(DESCRIPTION, SURGEON_NUMBER, INVALID, ROOM, TYPE, STATUS,
                PATIENT_NUMBER);

        operationRequestValidator.validate(operationRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenEmptyRoomWhenValidatingShouldThrowException() {
        operationRequest = new OperationRequest(DESCRIPTION, SURGEON_NUMBER, VALID_DATE, "", TYPE, STATUS,
                PATIENT_NUMBER);

        operationRequestValidator.validate(operationRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenEmptyTypeWhenValidatingShouldThrowException() {
        operationRequest = new OperationRequest(DESCRIPTION, SURGEON_NUMBER, VALID_DATE, ROOM, "", STATUS,
                PATIENT_NUMBER);

        operationRequestValidator.validate(operationRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenNullPatientWhenValidatingShouldThrowException() {
        operationRequest = new OperationRequest(DESCRIPTION, SURGEON_NUMBER, VALID_DATE, ROOM, TYPE, STATUS, null);

        operationRequestValidator.validate(operationRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenInvalidStatusWhenValidatingShouldThrowException() {
        operationRequest = new OperationRequest(DESCRIPTION, SURGEON_NUMBER, VALID_DATE, ROOM, TYPE, INVALID,
                PATIENT_NUMBER);

        operationRequestValidator.validate(operationRequest);
    }

    @Test
    public void givenValidRequestWhenValidatingShouldNotThrowException() {
        operationRequest = new OperationRequest(DESCRIPTION, SURGEON_NUMBER, VALID_DATE, ROOM, TYPE, STATUS,
                PATIENT_NUMBER);

        operationRequestValidator.validate(operationRequest);
    }
}
