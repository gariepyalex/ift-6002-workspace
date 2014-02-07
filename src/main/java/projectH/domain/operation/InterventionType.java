package projectH.domain.operation;

public enum InterventionType {
	EYE, HEART, MARROW, ONCOLOGY, OTHER;
	public boolean isCarefulInterventionType() {
		return (this == EYE || this == HEART || this == MARROW);
	}
}
