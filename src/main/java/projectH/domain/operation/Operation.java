package projectH.domain.operation;

import java.util.ArrayList;
import java.util.List;

import projectH.domain.instrument.Instrument;
import projectH.domain.instrument.InvalidInstrumentException;

public class Operation {

	private final String description;
	private final int surgeon;
	private final String date;
	private final InterventionType type;
	private final InterventionStatus status;
	private List<Instrument> instrumentList;

	public Operation(String description, int surgeon, String date, InterventionType type) {
		this(description, surgeon, date, type, InterventionStatus.PLANNED);
	}

	public Operation(String description, int surgeon, String date, InterventionType type, InterventionStatus status) {
		this.description = description;
		this.surgeon = surgeon;
		this.date = date;
		this.type = type;
		this.status = status;
		instrumentList = new ArrayList<Instrument>();
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

	public void addInstrument(Instrument instrument) throws InvalidInstrumentException {
		if (instrumentList.contains(instrument)) {
			throw new InvalidInstrumentException("Instrument with serial number " + instrument.getSerial() + " has "
					+ "already been assigned to this operation");
		}
		instrumentList.add(instrument);
	}

	public int getNumberOfInstrument() {
		return instrumentList.size();
	}

	public boolean hasInstrument(Instrument instrument) {
		return instrumentList.contains(instrument);
	}

}
