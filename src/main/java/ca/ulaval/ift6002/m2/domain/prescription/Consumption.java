package ca.ulaval.ift6002.m2.domain.prescription;

import java.util.Date;

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

}
