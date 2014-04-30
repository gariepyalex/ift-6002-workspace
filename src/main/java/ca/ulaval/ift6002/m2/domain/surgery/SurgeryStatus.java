package ca.ulaval.ift6002.m2.domain.surgery;

public enum SurgeryStatus {
    PLANNED("PLANIFIEE"), IN_PROGRESS("EN_COURS"), FINISH("TERMINE"), CANCELLED("ANNULEE"), POSTPONED("REPORTEE");

    private String textValue;

    SurgeryStatus(String textValue) {
        this.textValue = textValue;
    }

    @Override
    public String toString() {
        return textValue;
    }

    public static SurgeryStatus determineFrom(String status) {
        if (status.isEmpty()) {
            return PLANNED;
        }

        for (SurgeryStatus currentStatus : values()) {
            if (status.equalsIgnoreCase(currentStatus.toString())) {
                return currentStatus;
            }
        }

        throw new IllegalArgumentException("Surgery status is not defined");
    }

    public static boolean isValid(String status) {
        if (status.isEmpty()) {
            return true;
        }

        for (SurgeryStatus currentStatus : values()) {
            if (status.equalsIgnoreCase(currentStatus.toString())) {
                return true;
            }
        }

        return false;
    }
}
