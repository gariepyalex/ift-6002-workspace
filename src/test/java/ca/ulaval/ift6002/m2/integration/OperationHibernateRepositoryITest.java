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
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientFactory;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.OperationHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.PatientHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerFactoryProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProviderThreadSafe;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;

public class OperationHibernateRepositoryITest {
    private static final Serial INSTRUMENT_SERIAL_NUMBER = new Serial("11111");

    private static final InstrumentStatus INSTRUMENT_STATUS = InstrumentStatus.UNUSED;

    private static final Typecode INSTRUMENT_TYPECODE = new Typecode("11111");

    private static final int PATIENT_NUMBER = 1;

    private static final OperationStatus OPERATION_STATUS = OperationStatus.IN_PROGRESS;

    private static final Room ROOM = new Room("a room");

    private static final Date DATE = new Date();

    private static final Surgeon SURGEON = new Surgeon(1);

    private static final String DESCRIPTION = "An operation";

    private static final OperationType OPERATION_TYPE = OperationType.EYE;

    private static Patient patient;

    private static EntityManager entityManager;

    private static OperationRepository operationRepository;
    private static PatientRepository patientRepository;

    private static OperationFactory operationFactory;
    private static InstrumentFactory instrumentFactory;
    private static PatientFactory patientFactory;

    @BeforeClass
    public static void oneTimeSetUp() {
        setupFactoryLocator();
        setUpEntityManager();

        operationFactory = FactoryLocator.getOperationFactory();
        instrumentFactory = FactoryLocator.getInstrumentFactory();
        patientFactory = FactoryLocator.getPatientFactory();

        operationRepository = new OperationHibernateRepository(new EntityManagerProviderThreadSafe());
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
    public void givenNewOperationWhenStoringShouldBeStored() {
        Operation newOperation = operationFactory.create(OPERATION_TYPE, DESCRIPTION, SURGEON, DATE, ROOM,
                OPERATION_STATUS, patient);

        beginTransaction();
        operationRepository.store(newOperation);
        int operationNumber = newOperation.getNumber();
        commitTransaction();

        operationRepository.get(operationNumber);
    }

    @Test
    public void givenNewOperationWithInstrumentWhenStoringShouldBeStored() {
        Operation newOperation = operationFactory.create(OPERATION_TYPE, DESCRIPTION, SURGEON, DATE, ROOM,
                OPERATION_STATUS, patient);
        Instrument instrument = instrumentFactory.create(INSTRUMENT_TYPECODE, INSTRUMENT_STATUS,
                INSTRUMENT_SERIAL_NUMBER);
        newOperation.add(instrument);

        beginTransaction();
        operationRepository.store(newOperation);
        int operationNumber = newOperation.getNumber();
        commitTransaction();

        Operation operationRetrieved = operationRepository.get(operationNumber);

        assertTrue(operationRetrieved.has(instrument));
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
