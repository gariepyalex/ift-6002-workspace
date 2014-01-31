package projectH.domain.operation;

public class Operation {

	private static final InterventionStatus DEFAULT_STATUS = InterventionStatus.PLANNED;
	private String description;
	private int surgeon;
	private String date;
	private InterventionType type;
	private InterventionStatus status;

	public Operation(String wantedDescription, int wantedSurgeon, String wantedDate, InterventionType wantedType,
			InterventionStatus wantedStatus) {
		description = wantedDescription;
		surgeon = wantedSurgeon;
		date = wantedDate;
		type = wantedType;
		status = wantedStatus;
	}

	public Operation(String wantedDescription, int wantedSurgeon, String wantedDate, InterventionType wantedType) {
		description = wantedDescription;
		surgeon = wantedSurgeon;
		date = wantedDate;
		type = wantedType;
		status = DEFAULT_STATUS;
	}

	public String getDescription() {
		return description;
	}

	public int getSurgeon() {
		return surgeon;
	}

	public String getDate() {
		return date;
	}

	public InterventionType getType() {
		return type;
	}

	public InterventionStatus getStatus() {
		return status;
	}

}
