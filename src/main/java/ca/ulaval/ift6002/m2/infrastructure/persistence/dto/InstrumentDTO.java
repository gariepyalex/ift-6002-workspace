package ca.ulaval.ift6002.m2.infrastructure.persistence.dto;

import javax.persistence.Entity;

@Entity
public class InstrumentDTO extends BaseDTO {

    public String status;

    public int serial;

    public String typecode;

    public InstrumentDTO(String status, String typecode, int serial) {
        this.status = status;
        this.serial = serial;
        this.typecode = typecode;
        id = this.serial;
    }

    protected InstrumentDTO() {
        status = "";
        serial = 0;
        typecode = "";

    }
}
