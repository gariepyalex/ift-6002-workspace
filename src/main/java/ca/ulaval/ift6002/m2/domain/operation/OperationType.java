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

    public static OperationType getRandomRestrictedOperation() {
        OperationType[] restrictedOperationArray = { EYE, HEART, MARROW };
        return restrictedOperationArray[(int) (Math.random() * restrictedOperationArray.length)];
    }
}
