package ca.ulaval.ift6002.m2.application.rest.validator;

public class DrugResourceValidator {

    private static final int MIN_LENGTH_OF_SEARCH_KEYWORDS = 3;

    public void validateKeyword(String keyword) {
        if (keyword.length() < MIN_LENGTH_OF_SEARCH_KEYWORDS) {
            throw new IllegalArgumentException("The minimum character's length is: " + MIN_LENGTH_OF_SEARCH_KEYWORDS);
        }
    }
}
