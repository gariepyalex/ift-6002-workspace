package ca.ulaval.ift6002.m2.domain.operation;

public enum OperationType {

    EYE, HEART, MARROW, ONCOLOGY, OTHER;

    public boolean isDangerous() {
        return this == EYE || this == HEART || this == MARROW;
    }

    public static OperationType determineFrom(String type) {
        if (type.equalsIgnoreCase("OEIL")) {
            return OperationType.EYE;
        } else if (type.equalsIgnoreCase("COEUR")) {
            return OperationType.HEART;
        } else if (type.equalsIgnoreCase("MOELLE")) {
            return OperationType.MARROW;
        } else if (type.equalsIgnoreCase("ONCOLOGIQUE")) {
            return OperationType.ONCOLOGY;
        } else if (type.equalsIgnoreCase("AUTRE")) {
            return OperationType.OTHER;
        }

        throw new IllegalArgumentException("Operation type is not defined");
    }
}
