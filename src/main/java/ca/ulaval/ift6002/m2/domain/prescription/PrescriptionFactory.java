package ca.ulaval.ift6002.m2.domain.prescription;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.drug.Drug;

public interface PrescriptionFactory {

    Prescription create(Practitioner practitioner, Date date, int renewals, Drug drug);
}
