package ca.ulaval.ift6002.m2.application.rest.validator;

import org.junit.Before;
import org.junit.Test;

public class DrugResourceValidatorTest {

    private static final String VALID_KEYWORD = "advil";
    private static final String LESS_THAN_THREE_CHARACTERS_KEYWORD = "ad";

    private DrugResourceValidator drugResourceValidator;

    @Before
    public void setUp() {
        drugResourceValidator = new DrugResourceValidator();
    }

    @Test
    public void givenValidKeywordWhenValidatingShouldNotThrowException() {
        drugResourceValidator.validateKeyword(VALID_KEYWORD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenLessThanThreeCharactersKeywordWhenValidatingShouldThrowException() {
        drugResourceValidator.validateKeyword(LESS_THAN_THREE_CHARACTERS_KEYWORD);
    }

}
