package projectH.domain.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import projectH.domain.date.DateException;
import projectH.domain.date.DateFormatter;
import projectH.domain.instrument.Instrument;
import projectH.domain.instrument.InvalidInstrumentException;

public class Operation {

	private final String description;
	private final int surgeon;
	private final Date date;
	private String room;
	private final OperationType type;
	private final OperationStatus status;
	private int patientNumber;
	private List<Instrument> instrumentList;
	private DateFormatter dateFormatter = new DateFormatter();

	public Operation(String description, int surgeon, String date, String room, OperationType type, int patient) {
		this(description, surgeon, date, room, type, OperationStatus.PLANNED, patient);
	}

	public Operation(String description, int surgeon, String date, String room, OperationType type,
			OperationStatus status, int patient) {

		DateFormatter dateFactory = new DateFormatter();
		this.description = description;
		this.surgeon = surgeon;

		try {
			this.date = dateFactory.parse(date);
		} catch (DateException e) {
			throw new InvalidOperationException(e.getMessage());
		}

		this.room = room;
		this.type = type;
		this.status = status;
		this.patientNumber = patient;
		instrumentList = new ArrayList<Instrument>();
	}

	public String getDescription() {
		return description;
	}

	public int getSurgeon() {
		return surgeon;
	}

	public String getDate() {
		return dateFormatter.dateToString(date);
	}

	public String getRoom() {
		return room;
	}

	public OperationType getType() {
		return type;
	}

	public OperationStatus getStatus() {
		return status;
	}

	public int getPatientNumber() {
		return patientNumber;
	}

	public void addInstrument(Instrument instrument) throws InvalidInstrumentException {
		if (instrumentList.contains(instrument)) {
			throw new InvalidInstrumentException("Instrument with serial number " + instrument.getSerial() + " has "
					+ "already been assigned to this operation");
		}
		if (instrument.isAnonymous() && type.isCarefulOperationType())
			throw new InvalidInstrumentException("Anonymous instrument with type " + instrument.getTypecode()
					+ " cannot be added to operation with type" + this.getType());
		instrumentList.add(instrument);
	}

	public int getNumberOfInstrument() {
		return instrumentList.size();
	}

	public boolean hasInstrument(Instrument instrument) {
		return instrumentList.contains(instrument);
	}
}
