package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class OperationDTO {
    @Id
    public Integer id;

    public String date;
    public String status;
    public String description;

    @OneToOne(cascade = { CascadeType.ALL })
    public PatientDTO patient;

    @OneToMany(cascade = { CascadeType.ALL })
    public Collection<InstrumentDTO> instruments;

    @OneToOne(cascade = { CascadeType.ALL })
    public SurgeonDTO surgeon;

    @OneToOne(cascade = { CascadeType.ALL })
    public RoomDTO room;

    public OperationDTO(Integer id, String date, String status, String description, PatientDTO patient,
            Collection<InstrumentDTO> instruments, SurgeonDTO surgeon, RoomDTO room) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.patient = patient;
        this.instruments = instruments;
        this.surgeon = surgeon;
        this.room = room;
    }

    protected OperationDTO() {
        this.id = 0;
        status = "";
        date = "";
        description = "";
        this.patient = new PatientDTO();
        this.instruments = new ArrayList<InstrumentDTO>();
        this.surgeon = new SurgeonDTO();
        this.room = new RoomDTO();
    }

}
