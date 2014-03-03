package ca.ulaval.ift6002.m2.domain.operation;

public enum OperationType {

    EYE("OEIL"), HEART("COEUR"), MARROW("MOELLE"), ONCOLOGY("ONCOLOGIQUE"), OTHER("AUTRE");

    private final String textValue;

    OperationType(String textValue) {
        this.textValue = textValue;
    }

    @Override
    public String toString() {
        return textValue;
    }

    public boolean isRestricted() {
        return this == EYE || this == HEART || this == MARROW;
    }

    public static OperationType determineFrom(String type) {
        if (type.equalsIgnoreCase(EYE.toString())) {
            return EYE;
        } else if (type.equalsIgnoreCase(HEART.toString())) {
            return HEART;
        } else if (type.equalsIgnoreCase(MARROW.toString())) {
            return MARROW;
        } else if (type.equalsIgnoreCase(ONCOLOGY.toString())) {
            return ONCOLOGY;
        } else if (type.equalsIgnoreCase(OTHER.toString())) {
            return OTHER;
        }

        throw new IllegalArgumentException("Operation type is not defined");
    }
}
