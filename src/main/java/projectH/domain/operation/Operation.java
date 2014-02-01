package projectH.domain.operation;

public class Operation {

	private final String description;
	private final int surgeon;
	private final String date;
	private final InterventionType type;
	private final InterventionStatus status;

	public Operation(String description, int surgeon, String date, InterventionType type) {
		this(description, surgeon, date, type, InterventionStatus.PLANNED);
	}

	public Operation(String description, int surgeon, String date, InterventionType type, InterventionStatus status) {
		this.description = description;
		this.surgeon = surgeon;
		this.date = date;
		this.type = type;
		this.status = status;
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
