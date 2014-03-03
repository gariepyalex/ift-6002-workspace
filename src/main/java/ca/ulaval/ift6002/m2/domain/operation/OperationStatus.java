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
        if (status.equalsIgnoreCase(PLANNED.toString()) || status.isEmpty()) {
            return PLANNED;
        } else if (status.equalsIgnoreCase(IN_PROGRESS.toString())) {
            return IN_PROGRESS;
        } else if (status.equalsIgnoreCase(FINISH.toString())) {
            return FINISH;
        } else if (status.equalsIgnoreCase(CANCELLED.toString())) {
            return CANCELLED;
        } else if (status.equalsIgnoreCase(POSTPONED.toString())) {
            return POSTPONED;
        }

        throw new IllegalArgumentException("Operation status is not defined");
    }
}
