package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.Pharmacy;

public class ConsumptionHibernate implements Consumption {

    private final Date date;
    private final String pharmacy;
    private final int count;

    public ConsumptionHibernate(Date date, Pharmacy pharmacy, int count) {
        this.date = date;
        this.pharmacy = pharmacy.getDescription();
        this.count = count;
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
