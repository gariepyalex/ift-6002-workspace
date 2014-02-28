package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.DrugHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.PatientHibernateRepository;

public class HibernateRepositoryFactoryTest {

    private RepositoryFactory repositoryFactory;

    @Before
    public void setUp() {
        repositoryFactory = new HibernateRepositoryFactory();
    }

    @Test
    public void whenCreateDrugRepositoryShouldReturnHibernateInstance() {
        DrugRepository drugRepository = repositoryFactory.createDrugRepository();

        assertEquals(DrugHibernateRepository.class, drugRepository.getClass());
    }

    @Test
    public void whenCreatePatientRepositoryShouldReturnHibernateInstance() {
        PatientRepository patientRepository = repositoryFactory.createPatientRepository();

        assertEquals(PatientHibernateRepository.class, patientRepository.getClass());
    }

    @Test
    public void whenCreateInstrumentRepositoryShouldReturnHibernateInstance() {
        InstrumentRepository instrumentRepository = repositoryFactory.createInstrumentRepository();

        assertNull(instrumentRepository);
    }

    @Test
    public void whenCreateOperationRepositoryShouldReturnHibernateInstance() {
        OperationRepository operationRepository = repositoryFactory.createOperationRepository();

        assertNull(operationRepository);
    }
}
