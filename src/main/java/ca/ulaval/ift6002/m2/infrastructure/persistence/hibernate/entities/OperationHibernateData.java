package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.OperationData;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.operation.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.patient.Patient;

@Entity
@Table(name = "tbl_operation")
public class OperationHibernateData implements OperationData {

    @Id
    @GeneratedValue
    private int id;
    private String description;
    private int surgeonLicense;
    private Date date;
    private String room;
    private OperationStatus operationStatus;
    private OperationType operationType;
    // TODO Make hibernate patient
    // @ManyToOne
    // private Patient patient;
    @ManyToMany(cascade = { CascadeType.ALL })
    private List<InstrumentHibernate> instruments;

    public OperationHibernateData(String description, Surgeon surgeon, Date date, Room room, OperationStatus status,
            Patient patient, OperationType operationType) {
        this.description = description;
        this.surgeonLicense = surgeon.license;
        this.date = date;
        this.room = room.value;
        this.operationStatus = status;
        this.operationType = operationType;
        // TODO Change this
        // this.patient = patient;
        this.instruments = new ArrayList<>();
    }

    protected OperationHibernateData() {
        // For hibernate
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return new ArrayList<Instrument>(instruments);
    }

    @Override
    public void addInstrument(Instrument instrument) {
        InstrumentHibernate instrumentHibernate = (InstrumentHibernate) instrument;
        instruments.add(instrumentHibernate);
    }

    @Override
    public boolean hasInstruments() {
        return instruments.isEmpty();
    }

    @Override
    public int getNumber() {
        return id;
    }

    public String getRoomName() {
        return room;
    }

    public int getSurgeonLicense() {
        return surgeonLicense;
    }

    public Date getDate() {
        return date;
    }

    public Patient getPatient() {
        // TODO Change this
        return null;// patient;
    }

    public String getDescription() {
        return description;
    }

    public OperationStatus getStatus() {
        return operationStatus;
    }

    public OperationType getType() {
        return operationType;
    }

}
