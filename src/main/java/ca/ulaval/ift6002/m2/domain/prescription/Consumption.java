package ca.ulaval.ift6002.m2.domain.prescription;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Consumption {

    private final Date date;
    private final Pharmacy pharmacy;
    private final int count;

    public Consumption(Date date, Pharmacy pharmacy, int count) {
        this.date = date;
        this.pharmacy = pharmacy;
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

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
