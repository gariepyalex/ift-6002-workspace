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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryData;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryStatus;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryType;

@Entity
@Table(name = "tbl_surgery")
public class SurgeryHibernateData implements SurgeryData {

    @Id
    @GeneratedValue
    private int id;
    private String description;
    private int surgeonLicense;
    private Date date;
    private String room;
    private SurgeryStatus surgeryStatus;
    private SurgeryType surgeryType;

    @ManyToOne
    private PatientHibernate patient;

    @ManyToMany(cascade = { CascadeType.ALL })
    private List<InstrumentHibernate> instruments;

    public SurgeryHibernateData(String description, Surgeon surgeon, Date date, Room room, SurgeryStatus status,
            Patient patient, SurgeryType surgeryType) {
        this.description = description;
        this.surgeonLicense = surgeon.getLicense();
        this.date = date;
        this.room = room.getValue();
        this.surgeryStatus = status;
        this.surgeryType = surgeryType;
        this.patient = (PatientHibernate) patient;
        this.instruments = new ArrayList<>();
    }

    protected SurgeryHibernateData() {
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
        return patient;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public SurgeryStatus getStatus() {
        return surgeryStatus;
    }

    public SurgeryType getType() {
        return surgeryType;
    }

}
