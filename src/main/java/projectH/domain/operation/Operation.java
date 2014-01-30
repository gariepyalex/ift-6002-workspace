package projectH.domain.operation;

public class Operation {

	private static final String DEFAULT_STATUS = "PLANNED";
	private String description;
	private int surgeon;
	private String date;
	private String type;
	private String status;

	public Operation(String wantedDescription, int wantedSurgeon, String wantedDate, String wantedType,
			String wantedStatus) {
		description = wantedDescription;
		surgeon = wantedSurgeon;
		date = wantedDate;
		type = wantedType;
		status = wantedStatus;
	}

	public Operation(String wantedDescription, int wantedSurgeon, String wantedDate, String wantedType) {
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

	public String getType() {
		return type;
	}

	public String getStatus() {
		return status;
	}

}
