package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;

@Entity
@Table(name = "tbl_instrument")
public class InstrumentHibernate extends Instrument {

    @Id
    @GeneratedValue
    private Integer id;
    private InstrumentStatus status;
    private String serial;
    private String typecode;

    public InstrumentHibernate(Typecode typecode, InstrumentStatus status) {
        this(typecode, status, new Serial(""));
    }

    public InstrumentHibernate(Typecode typecode, InstrumentStatus status, Serial serialNumber) {
        this.status = status;
        this.typecode = typecode.toString();
        this.serial = serialNumber.toString();
    }

    protected InstrumentHibernate() {
        // For hibernate
    }

    public Integer getId() {
        return id;
    }

    @Override
    public InstrumentStatus getStatus() {
        return status;
    }

    @Override
    protected void setStatus(InstrumentStatus status) {
        this.status = status;
    }

    @Override
    public Typecode getTypecode() {
        return new Typecode(typecode);
    }

    @Override
    public Serial getSerial() {
        return new Serial(serial);
    }
}
