package ca.ulaval.ift6002.m2.contexts;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientFactory;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Consumption;
import ca.ulaval.ift6002.m2.domain.prescription.ConsumptionFactory;
import ca.ulaval.ift6002.m2.domain.prescription.Pharmacy;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionFactory;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class IntegrationPatientRepositoryFiller {

    private static final int PATIENT_NUMBER_WITH_RECENT_PRESCRIPTION = 3;
    private static final int PATIENT_NUMBER_WITH_OBSOLETE_PRESCRIPTION = 4;
    private static final int DEAD_PATIENT_NUMBER = 5;
    private static final int PATIENT_NUMBER_WITH_MULTIPLE_PRESCRIPTIONS_CONSUMPTIONS = 6;

    private static final Din EXISTING_DIN = new Din("02229682");
    private static final Practitioner A_PRACTITIONER = new Practitioner("A random name");

    private static final Date RECENT_DATE = new Date();
    private static final Date OLD_DATE = new Date(0);
    private static final DateFormatter DATE_FORMATTER = new DateFormatter();
    private static final Date DATE1 = DATE_FORMATTER.parse("2009-01-04T12:08:56");
    private static final Date DATE2 = DATE_FORMATTER.parse("2010-02-04T12:08:56");
    private static final Date DATE3 = DATE_FORMATTER.parse("2007-03-04T12:08:56");
    private static final Date DATE4 = DATE_FORMATTER.parse("2012-04-04T12:08:56");

    private static final int ONE_RENEWAL = 1;
    private static final int TWO_RENEWALS = 2;

    private final PatientRepository patientRepository;
    private final DrugRepository drugRepository;

    private final PatientFactory patientFactory;
    private final PrescriptionFactory prescriptionFactory;
    private final ConsumptionFactory consumptionFactory;

    public IntegrationPatientRepositoryFiller() {
        this.patientRepository = RepositoryLocator.getPatientRepository();
        this.drugRepository = RepositoryLocator.getDrugRepository();
        this.patientFactory = FactoryLocator.getPatientFactory();
        this.prescriptionFactory = FactoryLocator.getPrescriptionFactory();
        this.consumptionFactory = FactoryLocator.getConsumptionFactory();
    }

    public void fill() {
        patientRepository.store(patientFactory.create(1));
        patientRepository.store(patientFactory.create(2));

        Drug existingDrug = drugRepository.get(EXISTING_DIN);
        Prescription recentPrescription = prescriptionFactory.create(A_PRACTITIONER, RECENT_DATE, TWO_RENEWALS,
                existingDrug);
        Prescription obsolePrescription = getObsoletePrescription(existingDrug);

        Patient patientWithRecentPrescription = patientFactory.create(PATIENT_NUMBER_WITH_RECENT_PRESCRIPTION);
        patientWithRecentPrescription.receivesPrescription(recentPrescription);

        Patient patientWithObsoletePrescription = patientFactory.create(PATIENT_NUMBER_WITH_OBSOLETE_PRESCRIPTION);
        patientWithObsoletePrescription.receivesPrescription(obsolePrescription);

        Patient deadPatient = patientFactory.create(DEAD_PATIENT_NUMBER);
        deadPatient.declareDead();

        Patient patientWithMultiplePrescriptionsAndConsumptions = patientFactory.create(PATIENT_NUMBER_WITH_MULTIPLE_PRESCRIPTIONS_CONSUMPTIONS);
        Prescription prescription1 = prescriptionFactory.create(A_PRACTITIONER, DATE1, TWO_RENEWALS, existingDrug);
        Prescription prescription2 = prescriptionFactory.create(A_PRACTITIONER, DATE2, TWO_RENEWALS, existingDrug);
        Prescription prescription3 = prescriptionFactory.create(A_PRACTITIONER, DATE3, TWO_RENEWALS, existingDrug);
        Prescription prescription4 = prescriptionFactory.create(A_PRACTITIONER, DATE4, TWO_RENEWALS, existingDrug);
        patientWithMultiplePrescriptionsAndConsumptions.receivesPrescription(prescription1);
        patientWithMultiplePrescriptionsAndConsumptions.receivesPrescription(prescription2);
        patientWithMultiplePrescriptionsAndConsumptions.receivesPrescription(prescription3);
        patientWithMultiplePrescriptionsAndConsumptions.receivesPrescription(prescription4);

        patientRepository.store(patientWithRecentPrescription);
        patientRepository.store(patientWithObsoletePrescription);
        patientRepository.store(deadPatient);
        patientRepository.store(patientWithMultiplePrescriptionsAndConsumptions);

        Consumption consumption1 = consumptionFactory.create(DATE1, new Pharmacy("Jean Coutu"), ONE_RENEWAL);
        Consumption consumption2 = consumptionFactory.create(DATE2, new Pharmacy("Jean Coutu II"), ONE_RENEWAL);
        Consumption consumption3 = consumptionFactory.create(DATE3, new Pharmacy("Jean Coutu III"), ONE_RENEWAL);
        Consumption consumption4 = consumptionFactory.create(DATE4, new Pharmacy("Jean Coutu IV"), ONE_RENEWAL);
        Consumption consumption5 = consumptionFactory.create(DATE3, new Pharmacy("Jean Coutu V"), ONE_RENEWAL);
        Consumption consumption6 = consumptionFactory.create(DATE2, new Pharmacy("Jean Coutu VI"), ONE_RENEWAL);

        patientWithMultiplePrescriptionsAndConsumptions.consumesPrescription(prescription1.getNumber(), consumption1);
        patientWithMultiplePrescriptionsAndConsumptions.consumesPrescription(prescription1.getNumber(), consumption2);
        patientWithMultiplePrescriptionsAndConsumptions.consumesPrescription(prescription2.getNumber(), consumption3);
        patientWithMultiplePrescriptionsAndConsumptions.consumesPrescription(prescription3.getNumber(), consumption4);
        patientWithMultiplePrescriptionsAndConsumptions.consumesPrescription(prescription4.getNumber(), consumption5);
        patientWithMultiplePrescriptionsAndConsumptions.consumesPrescription(prescription4.getNumber(), consumption6);

        patientRepository.store(patientWithMultiplePrescriptionsAndConsumptions);
    }

    private Prescription getObsoletePrescription(Drug drug) {
        Prescription obsoletePrescription = prescriptionFactory.create(A_PRACTITIONER, OLD_DATE, TWO_RENEWALS, drug);
        Consumption consumption = FactoryLocator.getConsumptionFactory().create(OLD_DATE, new Pharmacy("Jean Coutu"),
                TWO_RENEWALS);
        obsoletePrescription.addConsumption(consumption);
        return obsoletePrescription;
    }
}
