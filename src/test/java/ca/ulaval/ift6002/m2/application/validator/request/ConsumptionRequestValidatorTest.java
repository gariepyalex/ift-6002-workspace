package ca.ulaval.ift6002.m2.application.validator.request;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.requests.ConsumptionRequest;
import ca.ulaval.ift6002.m2.application.validator.request.ConsumptionRequestValidator;
import ca.ulaval.ift6002.m2.application.validator.request.InvalidRequestException;

public class ConsumptionRequestValidatorTest {

    private static final String INVALID_DATE = "abc";
    private static final String INVALID_PHARMACY = "";
    private static final Integer INVALID_COUNT = null;
    private static final Integer NEGATIVE_COUNT = -5;

    private static final String A_DATE = "2012-01-01T12:00:00";
    private static final String A_PHARMACY = "pharmacy";
    private static final Integer A_COUNT = 10;

    private ConsumptionRequestValidator consumptionResponseValidator;

    @Before
    public void setUp() {
        consumptionResponseValidator = new ConsumptionRequestValidator();
    }

    @Test(expected = InvalidRequestException.class)
    public void givenInvalidDateWhenValidatingShouldThrowException() {
        ConsumptionRequest response = new ConsumptionRequest(INVALID_DATE, A_PHARMACY, A_COUNT);

        consumptionResponseValidator.validate(response);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenEmptyPharmacyWhenValidatingShouldThrowException() {
        ConsumptionRequest response = new ConsumptionRequest(A_DATE, INVALID_PHARMACY, A_COUNT);

        consumptionResponseValidator.validate(response);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenInvalidCountWhenValidatingShouldThrowException() {
        ConsumptionRequest response = new ConsumptionRequest(A_DATE, A_PHARMACY, INVALID_COUNT);

        consumptionResponseValidator.validate(response);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenNegativeCountWhenValidatingShouldThrowException() {
        ConsumptionRequest response = new ConsumptionRequest(A_DATE, A_PHARMACY, NEGATIVE_COUNT);

        consumptionResponseValidator.validate(response);
    }

    @Test
    public void givenValidResponseWhenValidatingShouldNotThrowException() {
        ConsumptionRequest response = new ConsumptionRequest(A_DATE, A_PHARMACY, A_COUNT);

        consumptionResponseValidator.validate(response);
    }
}
