package ca.ulaval.ift6002.m2.domain.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;
import ca.ulaval.ift6002.m2.domain.patient.Patient;

//TODO polymorphism with OperationType (EYE, MARROW)
public class Operation {

    private final String description;
    private final Surgeon surgeon;
    private final Date date;
    private final Room room;
    private final OperationType type;
    private final OperationStatus status;
    private final Patient patient;
    private final List<Instrument> instruments;
    private final DateFormatter dateFormatter = new DateFormatter();

    public Operation(String description, Surgeon surgeon, Date date, Room room, OperationType type, Patient patient) {
        this(description, surgeon, date, room, type, OperationStatus.PLANNED, patient);
    }

    public Operation(String description, Surgeon surgeon, Date date, Room room, OperationType type,
            OperationStatus status, Patient patient) {

        this.description = description;
        this.surgeon = surgeon;
        this.date = date;

        this.room = room;
        this.type = type;
        this.status = status;
        this.patient = patient;
        instruments = new ArrayList<Instrument>();
    }

    public OperationStatus getStatus() {
        return status;
    }

    public void addInstrument(Instrument instrument) throws InvalidInstrumentException {
        if (hasInstrument(instrument)) {
            throw new InvalidInstrumentException("This instrument is invalid: " + instrument);
        }

        if (instrument.isAnonymous() && type.isCarefulOperationType()) {
            throw new InvalidInstrumentException("Anonymous instrument " + instrument
                    + " cannot be added with operation type " + type);
        }

        instruments.add(instrument);
    }

    public int countInstruments() {
        return instruments.size();
    }

    public boolean hasInstrument(Instrument instrument) {
        return instruments.contains(instrument);
    }
}
