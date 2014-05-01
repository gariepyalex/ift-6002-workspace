package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Pharmacy;

@Entity
@Table(name = "tbl_consumption")
public class ConsumptionHibernate implements Consumption {

    @Id
    @GeneratedValue
    private Integer id;
    private Date date;
    private String pharmacy;
    private int count;

    public ConsumptionHibernate(Date date, Pharmacy pharmacy, int count) {
        this.date = date;
        this.pharmacy = pharmacy.getDescription();
        this.count = count;
    }

    protected ConsumptionHibernate() {
        // for hibernate
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public Pharmacy getPharmacy() {
        return new Pharmacy(pharmacy);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
