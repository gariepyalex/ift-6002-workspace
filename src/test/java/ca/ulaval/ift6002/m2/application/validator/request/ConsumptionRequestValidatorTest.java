package ca.ulaval.ift6002.m2.application.validator.request;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.requests.ConsumptionRequest;

public class ConsumptionRequestValidatorTest {

    private static final String INVALID_DATE = "abc";
    private static final String INVALID_PHARMACY = "";
    private static final Integer INVALID_COUNT = null;
    private static final Integer NEGATIVE_COUNT = -5;

    private static final String A_DATE = "2012-01-01T12:00:00";
    private static final String A_PHARMACY = "pharmacy";
    private static final Integer A_COUNT = 10;

    private ConsumptionRequestValidator consumptionRequestValidator;

    @Before
    public void setUp() {
        consumptionRequestValidator = new ConsumptionRequestValidator();
    }

    @Test(expected = InvalidRequestException.class)
    public void givenInvalidDateWhenValidatingShouldThrowException() {
        ConsumptionRequest request = new ConsumptionRequest(INVALID_DATE, A_PHARMACY, A_COUNT);

        consumptionRequestValidator.validate(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenEmptyPharmacyWhenValidatingShouldThrowException() {
        ConsumptionRequest request = new ConsumptionRequest(A_DATE, INVALID_PHARMACY, A_COUNT);

        consumptionRequestValidator.validate(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenInvalidCountWhenValidatingShouldThrowException() {
        ConsumptionRequest request = new ConsumptionRequest(A_DATE, A_PHARMACY, INVALID_COUNT);

        consumptionRequestValidator.validate(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenNegativeCountWhenValidatingShouldThrowException() {
        ConsumptionRequest request = new ConsumptionRequest(A_DATE, A_PHARMACY, NEGATIVE_COUNT);

        consumptionRequestValidator.validate(request);
    }

    @Test
    public void givenValidRequestWhenValidatingShouldNotThrowException() {
        ConsumptionRequest request = new ConsumptionRequest(A_DATE, A_PHARMACY, A_COUNT);

        consumptionRequestValidator.validate(request);
    }
}
