package ca.ulaval.ift6002.m2.application.validator.response;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.ConsumptionResponse;

public class ConsumptionResponseValidatorTest {

    private static final String INVALID_DATE = "abc";
    private static final String INVALID_PHARMACY = "";
    private static final Integer INVALID_COUNT = null;
    private static final Integer NEGATIVE_COUNT = -5;

    private static final String A_DATE = "2012-01-01T12:00:00";
    private static final String A_PHARMACY = "pharmacy";
    private static final Integer A_COUNT = 10;

    private ConsumptionResponseValidator consumptionResponseValidator;

    @Before
    public void setUp() {
        consumptionResponseValidator = new ConsumptionResponseValidator();
    }

    @Test(expected = InvalidResponseException.class)
    public void givenInvalidDateWhenValidatingShouldThrowException() {
        ConsumptionResponse response = new ConsumptionResponse(INVALID_DATE, A_PHARMACY, A_COUNT);

        consumptionResponseValidator.validate(response);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenEmptyPharmacyWhenValidatingShouldThrowException() {
        ConsumptionResponse response = new ConsumptionResponse(A_DATE, INVALID_PHARMACY, A_COUNT);

        consumptionResponseValidator.validate(response);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenInvalidCountWhenValidatingShouldThrowException() {
        ConsumptionResponse response = new ConsumptionResponse(A_DATE, A_PHARMACY, INVALID_COUNT);

        consumptionResponseValidator.validate(response);
    }

    @Test(expected = InvalidResponseException.class)
    public void givenNegativeCountWhenValidatingShouldThrowException() {
        ConsumptionResponse response = new ConsumptionResponse(A_DATE, A_PHARMACY, NEGATIVE_COUNT);

        consumptionResponseValidator.validate(response);
    }

    @Test
    public void givenValidResponseWhenValidatingShouldNotThrowException() {
        ConsumptionResponse response = new ConsumptionResponse(A_DATE, A_PHARMACY, A_COUNT);

        consumptionResponseValidator.validate(response);
    }
}
