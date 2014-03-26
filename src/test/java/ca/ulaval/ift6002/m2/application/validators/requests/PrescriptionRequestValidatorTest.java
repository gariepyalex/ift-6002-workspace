package ca.ulaval.ift6002.m2.application.validators.requests;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;
import ca.ulaval.ift6002.m2.application.validators.requests.InvalidRequestException;
import ca.ulaval.ift6002.m2.application.validators.requests.PrescriptionRequestValidator;

public class PrescriptionRequestValidatorTest {

    private static final String EMPTY_DIN = "";
    private static final String EMPTY_NAME = "";

    private static final String VALID_NAME = "advil";
    private static final String VALID_DIN = "1010122";

    private static final String PRACTITIONER = "102032";
    private static final String DATE = "12-12-12T12:12:12";
    private static final String INVALID_DATE = "invalide";

    private static final Integer VALID_RENEWALS = 15;
    private static final Integer INVALID_RENEWALS = -1;
    private static final Integer NULL_RENEWALS = null;

    private PrescriptionRequestValidator prescriptionValidator;

    @Before
    public void setUp() {
        prescriptionValidator = new PrescriptionRequestValidator();
    }

    @Test(expected = InvalidRequestException.class)
    public void givenInvalidDateWhenValidatingShouldThrowException() {
        PrescriptionRequest request = new PrescriptionRequest(PRACTITIONER, INVALID_DATE, VALID_RENEWALS, VALID_DIN,
                EMPTY_NAME);

        prescriptionValidator.validate(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenWithNullRenewalsWhenValidatingShouldThrowException() {
        PrescriptionRequest request = new PrescriptionRequest(PRACTITIONER, DATE, NULL_RENEWALS, EMPTY_DIN, VALID_NAME);

        prescriptionValidator.validate(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenInvalidRenewalsWhenValidatingShouldThrowException() {
        PrescriptionRequest request = new PrescriptionRequest(PRACTITIONER, DATE, INVALID_RENEWALS, EMPTY_DIN,
                VALID_NAME);

        prescriptionValidator.validate(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenNoDinOrNameWhenValidatingShouldThrowException() {
        PrescriptionRequest request = new PrescriptionRequest(PRACTITIONER, DATE, VALID_RENEWALS, EMPTY_DIN, EMPTY_NAME);

        prescriptionValidator.validate(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenDinAndNameWhenValidatingShouldThrowException() {
        PrescriptionRequest request = new PrescriptionRequest(PRACTITIONER, DATE, VALID_RENEWALS, VALID_DIN, VALID_NAME);

        prescriptionValidator.validate(request);
    }

    @Test
    public void givenValidRequestWhenValidatingShouldNotThrowException() {
        PrescriptionRequest request = new PrescriptionRequest(PRACTITIONER, DATE, VALID_RENEWALS, VALID_DIN, EMPTY_NAME);

        prescriptionValidator.validate(request);
    }
}
