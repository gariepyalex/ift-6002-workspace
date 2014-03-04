package ca.ulaval.ift6002.m2.domain.instrument;

public enum InstrumentStatus {
    USED_PATIENT("UTILISE_PATIENT"), SOILED("SOUILLÉ"), UNUSED("INUTILISE");

    private final String textValue;

    InstrumentStatus(String textValue) {
        this.textValue = textValue;
    }

    @Override
    public String toString() {
        return textValue;
    }

    public static InstrumentStatus determineFrom(String status) {
        for (InstrumentStatus currentStatus : values()) {
            if (status.equalsIgnoreCase(currentStatus.toString())) {
                return currentStatus;
            }
        }

        throw new IllegalArgumentException("There is no status corresponding to: " + status);
    }

    public static boolean isValid(String status) {
        for (InstrumentStatus currentStatus : values()) {
            if (status.equalsIgnoreCase(currentStatus.toString())) {
                return true;
            }
        }

        return false;
    }
}
