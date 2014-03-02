package ca.ulaval.ift6002.m2.domain.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

public abstract class Operation {

	protected final Description description;
	protected final Surgeon surgeon;
	protected final Date date;
	protected final Room room;
	protected final OperationStatus status;
	protected final Patient patient;
	protected final List<Instrument> instruments;

	protected Operation(Description description, Surgeon surgeon, Date date, Room room, OperationStatus status,
			Patient patient) {
		this.description = description;
		this.surgeon = surgeon;
		this.date = date;
		this.room = room;
		this.status = status;
		this.patient = patient;
		this.instruments = new ArrayList<>();
	}

	public boolean has(Instrument instrument) {
		return instruments.contains(instrument);
	}

	public int countInstruments() {
		return instruments.size();
	}

	public abstract void add(Instrument instrument);

	public void updateInstrumentStatus(Instrument instrument, String newStatus) {
		instruments.remove(instrument);
		instrument.setStatus(newStatus);
		instruments.add(instrument);
	}
}
