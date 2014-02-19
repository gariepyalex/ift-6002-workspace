package ca.ulaval.ift6002.m2.domain.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;

//TODO polymorphism with OperationType (EYE, MARROW)
//TODO should have Room object, and Patient (Patient has a number)
public class Operation {

    private final String description;
    private final int surgeon;
    private final Date date;
    private final String room;
    private final OperationType type;
    private final OperationStatus status;
    private final int patientNumber;
    private final List<Instrument> instrumentList;
    private final DateFormatter dateFormatter = new DateFormatter();

    public Operation(String description, int surgeon, Date date, String room, OperationType type, int patient) {
        this(description, surgeon, date, room, type, OperationStatus.PLANNED, patient);
    }

    public Operation(String description, int surgeon, Date date2, String room, OperationType type,
            OperationStatus status, int patient) {

        this.description = description;
        this.surgeon = surgeon;
        this.date = date2;

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
            throw new InvalidInstrumentException("This instrument is invalid: " + instrument);
        }
        if (instrument.isAnonymous() && type.isCarefulOperationType()) {
            throw new InvalidInstrumentException("Anonymous instrument with type " + instrument.getTypecode()
                    + " cannot be added to operation with type" + getType());
        }

        instrumentList.add(instrument);
    }

    public int countInstruments() {
        return instrumentList.size();
    }

    public boolean hasInstrument(Instrument instrument) {
        return instrumentList.contains(instrument);
    }
}
