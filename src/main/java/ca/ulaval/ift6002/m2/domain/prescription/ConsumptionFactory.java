package ca.ulaval.ift6002.m2.domain.prescription;

import java.util.Date;

public interface ConsumptionFactory {

    Consumption create(Date date, Pharmacy pharmacy, int count);

}
