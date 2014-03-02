package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RoomDTO {

    @Id
    @GeneratedValue
    public Integer id;
    public String room;

    public RoomDTO(String room) {
        this.room = room;
    }

    protected RoomDTO() {
        this.room = "";
    }

}
