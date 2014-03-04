package ca.ulaval.ift6002.m2.domain.operation;

public enum OperationStatus {
    PLANNED("PLANIFIEE"), IN_PROGRESS("EN_COURS"), FINISH("TERMINE"), CANCELLED("ANNULEE"), POSTPONED("REPORTEE");

    private String textValue;

    OperationStatus(String textValue) {
        this.textValue = textValue;
    }

    @Override
    public String toString() {
        return textValue;
    }

    public static OperationStatus determineFrom(String status) {
        if (status.isEmpty()) {
            return PLANNED;
        }

        for (OperationStatus currentStatus : values()) {
            if (status.equalsIgnoreCase(currentStatus.toString())) {
                return currentStatus;
            }
        }

        throw new IllegalArgumentException("Operation status is not defined");
    }

    public static boolean isValid(String status) {
        if (status.isEmpty()) {
            return true;
        }

        for (OperationStatus currentStatus : values()) {
            if (status.equalsIgnoreCase(currentStatus.toString())) {
                return true;
            }
        }

        return false;
    }
}
