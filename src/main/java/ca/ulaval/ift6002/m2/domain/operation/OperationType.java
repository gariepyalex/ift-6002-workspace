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
        for (OperationType currentType : values()) {
            if (type.equalsIgnoreCase(currentType.toString())) {
                return currentType;
            }
        }

        throw new IllegalArgumentException("Operation type is not defined");
    }

    public static boolean isValid(String status) {
        for (OperationType currentStatus : values()) {
            if (status.equalsIgnoreCase(currentStatus.toString())) {
                return true;
            }
        }

        return false;
    }
}
