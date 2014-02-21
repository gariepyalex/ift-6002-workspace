package ca.ulaval.ift6002.m2.application.uivalidator;

import org.junit.Before;
import org.junit.Test;

public class DrugResourceValidatorTest {

    private static final String LESS_THAN_THREE_CHARACTERS_KEYWORD = "ab";

    private DrugResourceValidator drugResourceValidator;

    @Before
    public void setup() {
        drugResourceValidator = new DrugResourceValidator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateKeywordWhenLessThanThreeCharactersKeywordShouldThrowException() {
        drugResourceValidator.validateKeyword(LESS_THAN_THREE_CHARACTERS_KEYWORD);
    }

}
