package ca.ulaval.ift6002.m2.domain.prescription;

import java.util.Date;

public interface Consumption {

    Date getDate();

    Pharmacy getPharmacy();

    int getCount();

}
