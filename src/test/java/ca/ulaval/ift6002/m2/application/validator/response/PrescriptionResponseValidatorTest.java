package ca.ulaval.ift6002.m2.application.validator.response;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;

public class PrescriptionResponseValidatorTest {

    private static final String EMPTY_DIN = "";
    private static final String EMPTY_NAME = "";

    private static final String VALID_NAME = "advil";
    private static final String VALID_DIN = "1010122";

    private static final String PRACTITIONER = "102032";
    private static final String DATE = "12-12-12T12:12:12";

    private static final Integer VALID_RENEWALS = 15;
    private static final Integer INVALID_RENEWALS = -1;
    private static final Integer NULL_RENEWALS = null;

    private PrescriptionResponseValidator prescriptionValidator;

    @Before
    public void setUp() {
        prescriptionValidator = new PrescriptionResponseValidator();
    }

    @Test(expected = InvalidResponseException.class)
    public void givenResponseWithNullRenewalsWhenValidatingShouldThrowException() throws InvalidResponseException {
        PrescriptionResponse response = new PrescriptionResponse(PRACTITIONER, DATE, NULL_RENEWALS, EMPTY_DIN,
                VALID_NAME);

        prescriptionValidator.validate(response);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenResponseWithInvalidRenewalsWhenValidatingShouldThrowException() throws InvalidResponseException {
        PrescriptionResponse response = new PrescriptionResponse(PRACTITIONER, DATE, INVALID_RENEWALS, EMPTY_DIN,
                VALID_NAME);

        prescriptionValidator.validate(response);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenResponseWithoutDinOrNameWhenValidatingShouldThrowException() throws InvalidResponseException {
        PrescriptionResponse response = new PrescriptionResponse(PRACTITIONER, DATE, VALID_RENEWALS, EMPTY_DIN,
                EMPTY_NAME);

        prescriptionValidator.validate(response);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenResponseWithBothDinAndNameWhenValidatingShouldThrowException() throws InvalidResponseException {
        PrescriptionResponse response = new PrescriptionResponse(PRACTITIONER, DATE, VALID_RENEWALS, VALID_DIN,
                VALID_NAME);

        prescriptionValidator.validate(response);
    }
}
