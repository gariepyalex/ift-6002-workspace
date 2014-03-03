package ca.ulaval.ift6002.m2.domain.operation;

public enum OperationStatus {
    PLANNED, IN_PROGRESS, FINISH, CANCELLED, POSTPONED;

    private static final String POSTPONED_STRING = "REPORTEE";
    private static final String CANCELLED_STRING = "ANNULEE";
    private static final String FINISH_STRING = "TERMINE";
    private static final String IN_PROGRESS_STRING = "EN_COURS";
    private static final String PLANNED_STRING = "PLANIFIEE";

    public static OperationStatus determineFrom(String status) {
        if (status.equalsIgnoreCase(PLANNED_STRING) || status.isEmpty()) {
            return OperationStatus.PLANNED;
        } else if (status.equalsIgnoreCase(IN_PROGRESS_STRING)) {
            return OperationStatus.IN_PROGRESS;
        } else if (status.equalsIgnoreCase(FINISH_STRING)) {
            return OperationStatus.FINISH;
        } else if (status.equalsIgnoreCase(CANCELLED_STRING)) {
            return OperationStatus.CANCELLED;
        } else if (status.equalsIgnoreCase(POSTPONED_STRING)) {
            return OperationStatus.POSTPONED;
        }

        throw new IllegalArgumentException("Operation status is not defined");
    }

    public static String convertToString(OperationStatus status) {
        if (status == OperationStatus.PLANNED) {
            return PLANNED_STRING;
        } else if (status == OperationStatus.IN_PROGRESS) {
            return IN_PROGRESS_STRING;
        } else if (status == OperationStatus.FINISH) {
            return FINISH_STRING;
        } else if (status == OperationStatus.CANCELLED) {
            return CANCELLED_STRING;
        } else {
            return POSTPONED_STRING;
        }
    }
}
