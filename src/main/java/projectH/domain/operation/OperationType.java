package projectH.domain.operation;

public enum OperationType {
    EYE, HEART, MARROW, ONCOLOGY, OTHER;
    public boolean isCarefulOperationType() {
        return (this == EYE || this == HEART || this == MARROW);
    }
}
