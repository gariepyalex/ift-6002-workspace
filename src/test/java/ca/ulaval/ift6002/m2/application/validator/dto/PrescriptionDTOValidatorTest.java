package ca.ulaval.ift6002.m2.application.validator.dto;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.PrescriptionDTO;

public class PrescriptionDTOValidatorTest {

    private static final String EMPTY_DIN = "";
    private static final String EMPTY_NAME = "";

    private static final String VALID_NAME = "advil";
    private static final String VALID_DIN = "1010122";

    private static final String PRACTITIONER = "102032";
    private static final String DATE = "12-12-12T12:12:12";

    private static final Integer VALID_RENEWALS = 15;
    private static final Integer INVALID_RENEWALS = -1;
    private static final Integer NULL_RENEWALS = null;

    private PrescriptionDTOValidator prescriptionValidator;

    @Before
    public void setup() {
        prescriptionValidator = new PrescriptionDTOValidator();
    }

    @Test(expected = InvalidDTOException.class)
    public void givenDTOWithNullRenewalsWhenValidatingShouldThrowException() throws InvalidDTOException {
        PrescriptionDTO dto = new PrescriptionDTO(PRACTITIONER, DATE, NULL_RENEWALS, EMPTY_DIN, VALID_NAME);

        prescriptionValidator.validate(dto);
    }

    @Test(expected = InvalidDTOException.class)
    public void givenDTOWithInvalidRenewalsWhenValidatingShouldThrowException() throws InvalidDTOException {
        PrescriptionDTO dto = new PrescriptionDTO(PRACTITIONER, DATE, INVALID_RENEWALS, EMPTY_DIN, VALID_NAME);

        prescriptionValidator.validate(dto);
    }

    @Test(expected = InvalidDTOException.class)
    public void givenDTOWithoutDinOrNameWhenValidatingShouldThrowException() throws InvalidDTOException {
        PrescriptionDTO dto = new PrescriptionDTO(PRACTITIONER, DATE, VALID_RENEWALS, EMPTY_DIN, EMPTY_NAME);

        prescriptionValidator.validate(dto);
    }

    @Test(expected = InvalidDTOException.class)
    public void givenDTOWithBothDinAndNameWhenValidatingShouldThrowException() throws InvalidDTOException {
        PrescriptionDTO dto = new PrescriptionDTO(PRACTITIONER, DATE, VALID_RENEWALS, VALID_DIN, VALID_NAME);

        prescriptionValidator.validate(dto);
    }

    @Test
    public void givenDTOWithOnlyDinWhenValidatingShouldDoNothing() throws InvalidDTOException {
        PrescriptionDTO dto = new PrescriptionDTO(PRACTITIONER, DATE, VALID_RENEWALS, VALID_DIN, EMPTY_NAME);

        prescriptionValidator.validate(dto);
    }

    @Test
    public void givenDTOWithOnlyNameWhenValidatingShouldDoNothing() throws InvalidDTOException {
        PrescriptionDTO dto = new PrescriptionDTO(PRACTITIONER, DATE, VALID_RENEWALS, EMPTY_DIN, VALID_NAME);

        prescriptionValidator.validate(dto);
    }

}
