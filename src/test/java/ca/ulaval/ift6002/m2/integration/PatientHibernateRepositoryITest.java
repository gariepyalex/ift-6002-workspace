package ca.ulaval.ift6002.m2.integration;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ulaval.ift6002.m2.configuration.factory.HibernateFactoryConfiguration;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientFactory;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.PatientHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerFactoryProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProviderThreadSafe;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;

public class PatientHibernateRepositoryITest {

    private static final String A_DRUG_NAME = "a drug name";

    private static final int RENEWALS = 1;

    private static final Date DATE = new Date();

    private static final Practitioner PRACTITIONER = new Practitioner("a practitioner");

    private static final int PATIENT_NUMBER_WITH_PRESCRIPTION = 2;

    private static final int PATIENT_NUMBER = 1;

    private static EntityManager entityManager;

    private static PatientRepository patientRepository;

    private static PatientFactory patientFactory;
    private static PrescriptionFactory prescriptionFactory;
    private static DrugFactory drugFactory;

    @BeforeClass
    public static void oneTimeSetUp() {
        setupFactoryLocator();
        setUpEntityManager();

        patientFactory = FactoryLocator.getPatientFactory();
        prescriptionFactory = FactoryLocator.getPrescriptionFactory();
        drugFactory = FactoryLocator.getDrugFactory();

        patientRepository = new PatientHibernateRepository(new EntityManagerProviderThreadSafe());
    }

    @AfterClass
    public static void closeEntityManager() {
        EntityManagerProviderThreadSafe.clearEntityManager();
        entityManager.close();
        EntityManagerFactoryProvider.closeFactory();
    }

    @Test
    public void givenNewPatientWhenStoringShouldBeStored() {
        Patient newPatient = patientFactory.create(PATIENT_NUMBER);

        beginTransaction();
        patientRepository.store(newPatient);
        commitTransaction();

        patientRepository.get(PATIENT_NUMBER);
    }

    @Test
    public void givenNewPatientWithPrescriptionWhenStoringShouldBeStored() {
        Patient newPatient = patientFactory.create(PATIENT_NUMBER_WITH_PRESCRIPTION);
        Drug drug = drugFactory.create(A_DRUG_NAME);
        Prescription prescription = prescriptionFactory.create(PRACTITIONER, DATE, RENEWALS, drug);
        newPatient.receivesPrescription(prescription);

        beginTransaction();
        patientRepository.store(newPatient);
        commitTransaction();

        Patient patientRetrieved = patientRepository.get(PATIENT_NUMBER_WITH_PRESCRIPTION);

        assertEquals(1, patientRetrieved.getPrescriptions().size());
    }

    private static void setupFactoryLocator() {
        new HibernateFactoryConfiguration().configure();
    }

    private static void setUpEntityManager() {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
        entityManager = entityManagerFactory.createEntityManager();
        EntityManagerProviderThreadSafe.setEntityManager(entityManager);

    }

    private static void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    private static void commitTransaction() {
        entityManager.getTransaction().commit();
    }
}
