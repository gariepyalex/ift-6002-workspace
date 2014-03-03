package ca.ulaval.ift6002.m2.domain.operation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

public abstract class Operation {

    private final Integer number;
    private final String description;
    private final Surgeon surgeon;
    private final Date date;
    private final Room room;
    private final OperationStatus status;
    private final Patient patient;
    private final List<Instrument> instruments;

    protected Operation(String description, Surgeon surgeon, Date date, Room room, OperationStatus status,
            Patient patient) {
        this(description, surgeon, date, room, status, patient, null);

    }

    protected Operation(String description, Surgeon surgeon, Date date, Room room, OperationStatus status,
            Patient patient, Integer number) {
        this.description = description;
        this.surgeon = surgeon;
        this.date = date;
        this.room = room;
        this.status = status;
        this.patient = patient;
        this.instruments = new ArrayList<>();
        this.number = number;
    }

    public void updateInstrumentStatus(Instrument instrument, String newStatus) {
        instruments.remove(instrument);
        instrument.setStatus(newStatus);
        instruments.add(instrument);
    }

    public boolean has(Instrument instrument) {
        return instruments.contains(instrument);
    }

    public int countInstruments() {
        return instruments.size();
    }

    public void add(Instrument instrument) {
        if (has(instrument)) {
            throw new InvalidInstrumentException("Instrument with same serial already exists: " + instrument);
        }

        if (!isInstrumentElligible(instrument)) {
            throw new InvalidInstrumentException("Instrument '" + instrument + "' is not elligible");
        }

        instruments.add(instrument);
    }

    public void add(Collection<Instrument> instruments) {
        for (Instrument instrument : instruments) {
            add(instrument);
        }
    }

    protected abstract boolean isInstrumentElligible(Instrument instrument);

    public abstract OperationType getType();

    public Collection<Instrument> getInstruments() {
        return instruments;
    }

    public Integer getNumber() {
        return number;
    }

    public Room getRoom() {
        return room;
    }

    public Surgeon getSurgeon() {
        return surgeon;
    }

    public Date getDate() {
        return date;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDescription() {
        return description;
    }

    public OperationStatus getStatus() {
        return status;
    }
}
