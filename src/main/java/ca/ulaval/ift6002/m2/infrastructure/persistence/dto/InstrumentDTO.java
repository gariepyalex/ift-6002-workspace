package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InstrumentDTO {

    @Id
    public int serial;

    public String status;

    public String typecode;

    public InstrumentDTO(String status, String typecode, int serial) {
        this.status = status;
        this.serial = serial;
        this.typecode = typecode;
    }

    protected InstrumentDTO() {
        status = "";
        serial = 0;
        typecode = "";

    }
}
