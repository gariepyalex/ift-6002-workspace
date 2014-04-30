package ca.ulaval.ift6002.m2.application.validators.requests;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.requests.SurgeryRequest;

public class SurgeryRequestValidatorTest {

    private static final String INVALID = "invalid";

    private static final String DESCRIPTION = "description";
    private static final int SURGEON_NUMBER = 101224;
    private static final String VALID_DATE = "0000-00-00T24:01:00";
    private static final String ROOM = "blocB";
    private static final Integer PATIENT_NUMBER = 1234;
    private static final String TYPE = "oeil";
    private static final String STATUS = "en_cours";

    private SurgeryRequestValidator surgeryRequestValidator;
    private SurgeryRequest surgeryRequest;

    @Before
    public void setUp() {
        surgeryRequestValidator = new SurgeryRequestValidator();
    }

    @Test(expected = InvalidRequestException.class)
    public void givenEmptyDescriptionWhenValidatingShouldThrowException() {
        surgeryRequest = new SurgeryRequest("", SURGEON_NUMBER, VALID_DATE, ROOM, TYPE, STATUS, PATIENT_NUMBER);

        surgeryRequestValidator.validate(surgeryRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenNullSurgeonWhenValidatingShouldThrowException() {
        surgeryRequest = new SurgeryRequest(DESCRIPTION, null, VALID_DATE, ROOM, TYPE, STATUS, PATIENT_NUMBER);

        surgeryRequestValidator.validate(surgeryRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenInvalidDateWhenValidatingShouldThrowException() {
        surgeryRequest = new SurgeryRequest(DESCRIPTION, SURGEON_NUMBER, INVALID, ROOM, TYPE, STATUS,
                PATIENT_NUMBER);

        surgeryRequestValidator.validate(surgeryRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenEmptyRoomWhenValidatingShouldThrowException() {
        surgeryRequest = new SurgeryRequest(DESCRIPTION, SURGEON_NUMBER, VALID_DATE, "", TYPE, STATUS,
                PATIENT_NUMBER);

        surgeryRequestValidator.validate(surgeryRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenEmptyTypeWhenValidatingShouldThrowException() {
        surgeryRequest = new SurgeryRequest(DESCRIPTION, SURGEON_NUMBER, VALID_DATE, ROOM, "", STATUS,
                PATIENT_NUMBER);

        surgeryRequestValidator.validate(surgeryRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenNullPatientWhenValidatingShouldThrowException() {
        surgeryRequest = new SurgeryRequest(DESCRIPTION, SURGEON_NUMBER, VALID_DATE, ROOM, TYPE, STATUS, null);

        surgeryRequestValidator.validate(surgeryRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenInvalidStatusWhenValidatingShouldThrowException() {
        surgeryRequest = new SurgeryRequest(DESCRIPTION, SURGEON_NUMBER, VALID_DATE, ROOM, TYPE, INVALID,
                PATIENT_NUMBER);

        surgeryRequestValidator.validate(surgeryRequest);
    }

    @Test
    public void givenValidRequestWhenValidatingShouldNotThrowException() {
        surgeryRequest = new SurgeryRequest(DESCRIPTION, SURGEON_NUMBER, VALID_DATE, ROOM, TYPE, STATUS,
                PATIENT_NUMBER);

        surgeryRequestValidator.validate(surgeryRequest);
    }
}
