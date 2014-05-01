package ca.ulaval.ift6002.m2.integration;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ulaval.ift6002.m2.configuration.factory.HibernateFactoryConfiguration;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentFactory;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.instrument.Typecode;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientFactory;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.surgery.Surgery;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryFactory;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryRepository;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryStatus;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryType;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.PatientHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.SurgeryHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerFactoryProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProviderThreadSafe;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;

public class SurgeryHibernateRepositoryITest {
    private static final Serial INSTRUMENT_SERIAL_NUMBER = new Serial("11111");

    private static final InstrumentStatus INSTRUMENT_STATUS = InstrumentStatus.UNUSED;

    private static final Typecode INSTRUMENT_TYPECODE = new Typecode("11111");

    private static final int PATIENT_NUMBER = 1;

    private static final SurgeryStatus SURGERY_STATUS = SurgeryStatus.IN_PROGRESS;

    private static final Room ROOM = new Room("a room");

    private static final Date DATE = new Date();

    private static final Surgeon SURGEON = new Surgeon(1);

    private static final String DESCRIPTION = "A surgery";

    private static final SurgeryType SURGERY_TYPE = SurgeryType.EYE;

    private static Patient patient;

    private static EntityManager entityManager;

    private static SurgeryRepository surgeryRepository;
    private static PatientRepository patientRepository;

    private static SurgeryFactory surgeryFactory;
    private static InstrumentFactory instrumentFactory;
    private static PatientFactory patientFactory;

    @BeforeClass
    public static void oneTimeSetUp() {
        setupFactoryLocator();
        setUpEntityManager();

        surgeryFactory = FactoryLocator.getSurgeryFactory();
        instrumentFactory = FactoryLocator.getInstrumentFactory();
        patientFactory = FactoryLocator.getPatientFactory();

        surgeryRepository = new SurgeryHibernateRepository(new EntityManagerProviderThreadSafe());
        patientRepository = new PatientHibernateRepository(new EntityManagerProviderThreadSafe());

        patient = patientFactory.create(PATIENT_NUMBER);

        beginTransaction();
        patientRepository.store(patient);
        commitTransaction();
    }

    @AfterClass
    public static void closeEntityManager() {
        EntityManagerProviderThreadSafe.clearEntityManager();
        entityManager.close();
        EntityManagerFactoryProvider.closeFactory();
    }

    @Test
    public void givenNewSurgeryWhenStoringShouldBeStored() {
        Surgery newSurgery = surgeryFactory.create(SURGERY_TYPE, DESCRIPTION, SURGEON, DATE, ROOM, SURGERY_STATUS,
                patient);

        beginTransaction();
        surgeryRepository.store(newSurgery);
        int surgeryNumber = newSurgery.getNumber();
        commitTransaction();

        surgeryRepository.get(surgeryNumber);
    }

    @Test
    public void givenNewSurgeryWithInstrumentWhenStoringShouldBeStored() {
        Surgery newSurgery = surgeryFactory.create(SURGERY_TYPE, DESCRIPTION, SURGEON, DATE, ROOM, SURGERY_STATUS,
                patient);
        Instrument instrument = instrumentFactory.create(INSTRUMENT_TYPECODE, INSTRUMENT_STATUS,
                INSTRUMENT_SERIAL_NUMBER);
        newSurgery.add(instrument);

        beginTransaction();
        surgeryRepository.store(newSurgery);
        int surgeryNumber = newSurgery.getNumber();
        commitTransaction();

        Surgery surgeryRetrieved = surgeryRepository.get(surgeryNumber);

        assertTrue(surgeryRetrieved.has(instrument));
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
