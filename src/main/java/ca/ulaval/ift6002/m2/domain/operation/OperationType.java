package ca.ulaval.ift6002.m2.domain.operation;

public enum OperationType {

    EYE, HEART, MARROW, ONCOLOGY, OTHER;

    private static final String EYE_STRING = new String("OEIL");
    private static final String HEART_STRING = new String("COEUR");
    private static final String MARROW_STRING = new String("MOELLE");
    private static final String ONCOLOGY_STRING = new String("ONCOLOGIQUE");
    private static final String OTHER_STRING = new String("AUTRE");

    public boolean isDangerous() {
        return this == EYE || this == HEART || this == MARROW;
    }

    public static OperationType determineFrom(String type) {
        if (type.equalsIgnoreCase(EYE_STRING)) {
            return OperationType.EYE;
        } else if (type.equalsIgnoreCase(HEART_STRING)) {
            return OperationType.HEART;
        } else if (type.equalsIgnoreCase(MARROW_STRING)) {
            return OperationType.MARROW;
        } else if (type.equalsIgnoreCase(ONCOLOGY_STRING)) {
            return OperationType.ONCOLOGY;
        } else if (type.equalsIgnoreCase(OTHER_STRING)) {
            return OperationType.OTHER;
        }

        throw new IllegalArgumentException("Operation type is not defined");
    }

    public static String convertToString(OperationType type) {
        if (type == OperationType.EYE) {

            return EYE_STRING;
        } else if (type == OperationType.HEART) {
            return HEART_STRING;
        } else if (type == OperationType.MARROW) {
            return MARROW_STRING;
        } else if (type == OperationType.ONCOLOGY) {
            return ONCOLOGY_STRING;
        } else {
            return OTHER_STRING;
        }
    }
}
