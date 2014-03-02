package ca.ulaval.ift6002.m2.domain.operation;


public enum OperationStatus {
    PLANNED, IN_PROGRESS, FINISH, CANCELLED, POSTPONED;

    public static OperationStatus determineFrom(String status) {
        if (status.equalsIgnoreCase("PLANIFIEE") || status.isEmpty()) {
            return OperationStatus.PLANNED;
        } else if (status.equalsIgnoreCase("EN_COURS")) {
            return OperationStatus.IN_PROGRESS;
        } else if (status.equalsIgnoreCase("TERMINE")) {
            return OperationStatus.FINISH;
        } else if (status.equalsIgnoreCase("ANNULEE")) {
            return OperationStatus.CANCELLED;
        } else if (status.equalsIgnoreCase("REPORTEE")) {
            return OperationStatus.POSTPONED;
        }

        throw new IllegalArgumentException("Operation status is not defined");
    }
}
