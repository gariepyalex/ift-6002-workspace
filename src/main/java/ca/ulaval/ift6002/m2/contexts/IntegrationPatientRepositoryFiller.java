package ca.ulaval.ift6002.m2.contexts;

import java.util.Date;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientFactory;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionFactory;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class IntegrationPatientRepositoryFiller {

    private static final Din EXISTING_DIN = new Din("11111111");
    private static final Practitioner A_PRACTITIONER = new Practitioner("A random name");

    private static final Date RECENT_DATE = new Date();
    private static final Date OLD_DATE = new Date(0);

    private static final int RENEWALS = 2;

    private final PatientRepository patientRepository;
    private final DrugRepository drugRepository;

    private final PatientFactory patientFactory;
    private final PrescriptionFactory prescriptionFactory;

    public IntegrationPatientRepositoryFiller() {
        this.patientRepository = RepositoryLocator.getPatientRepository();
        this.drugRepository = RepositoryLocator.getDrugRepository();
        this.patientFactory = FactoryLocator.getPatientFactory();
        this.prescriptionFactory = FactoryLocator.getPrescriptionFactory();
    }

    public void fill() {
        patientRepository.store(patientFactory.create(1, "ABCD 4512 1213"));
        patientRepository.store(patientFactory.create(2, "ABCD 4512 1213"));

        Drug existingDrug = drugRepository.get(EXISTING_DIN);
        Prescription recentPrescription = prescriptionFactory.create(A_PRACTITIONER, RECENT_DATE, RENEWALS,
                existingDrug);
        Prescription oldPrescription = prescriptionFactory.create(A_PRACTITIONER, OLD_DATE, RENEWALS, existingDrug);

        Patient patientWithRecentPrescription = patientFactory.create(3, "ABCD 4512 1213");
        patientWithRecentPrescription.receivesPrescription(recentPrescription);

        Patient patientWithOldPrescription = patientFactory.create(4, "ABCD 4512 1213");
        patientWithOldPrescription.receivesPrescription(oldPrescription);

        patientRepository.store(patientWithRecentPrescription);
        patientRepository.store(patientWithOldPrescription);
    }
}
