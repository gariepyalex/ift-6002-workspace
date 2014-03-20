package ca.ulaval.ift6002.m2.domain.prescription;

import java.util.Date;

public interface Consumption {

    public Date getDate();

    public Pharmacy getPharmacy();

    public int getCount();

}
