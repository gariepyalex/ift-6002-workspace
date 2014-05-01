package ca.ulaval.ift6002.m2.domain.surgery;

public enum SurgeryType {

    EYE("OEIL"), HEART("COEUR"), MARROW("MOELLE"), ONCOLOGY("ONCOLOGIQUE"), OTHER("AUTRE");

    private final String textValue;

    SurgeryType(String textValue) {
        this.textValue = textValue;
    }

    @Override
    public String toString() {
        return textValue;
    }

    public static SurgeryType determineFrom(String type) {
        for (SurgeryType currentType : values()) {
            if (type.equalsIgnoreCase(currentType.toString())) {
                return currentType;
            }
        }

        throw new IllegalArgumentException("Surgery type is not defined");
    }

    public static boolean isValid(String status) {
        for (SurgeryType currentStatus : values()) {
            if (status.equalsIgnoreCase(currentStatus.toString())) {
                return true;
            }
        }

        return false;
    }
}
