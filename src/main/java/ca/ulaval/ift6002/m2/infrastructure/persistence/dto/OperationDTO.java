package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class OperationDTO {
    @Id
    @GeneratedValue
    public Integer number;

    public String date;
    public String status;
    public String description;
    public String type;

    @ManyToOne
    public PatientDTO patient;

    @ManyToMany(cascade = { CascadeType.ALL })
    public Collection<InstrumentDTO> instruments;

    @ManyToOne(cascade = { CascadeType.ALL })
    public SurgeonDTO surgeon;

    @ManyToOne(cascade = { CascadeType.ALL })
    public RoomDTO room;

    public OperationDTO(String date, String status, String description, PatientDTO patient,
            Collection<InstrumentDTO> instruments, SurgeonDTO surgeon, RoomDTO room, String type, Integer number) {
        this.date = date;
        this.description = description;
        this.patient = patient;
        this.instruments = instruments;
        this.surgeon = surgeon;
        this.room = room;
        this.type = type;
        this.status = status;
        this.number = number;
    }

    protected OperationDTO() {
        this.number = null;
        this.status = "";
        this.date = "";
        this.description = "";
        this.type = "";
        this.patient = new PatientDTO();
        this.instruments = new ArrayList<InstrumentDTO>();
        this.surgeon = new SurgeonDTO();
        this.room = new RoomDTO();
    }

}
