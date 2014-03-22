package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.factory.FactoryLocator;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.DrugHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.OperationHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.PatientHibernateRepository;

@RunWith(MockitoJUnitRunner.class)
public class HibernateRepositoryFactoryTest {

    // TODO DO something better than juste mock factoryLocator...
    private FactoryLocator factoryLocator;

    @Mock
    private DrugFactory drugFactory;

    @Mock
    private OperationFactory operationFactory;

    private RepositoryFactory repositoryFactory;

    @Before
    public void setUpFactoryLocator() {
        factoryLocator = new FactoryLocator();

        factoryLocator.register(DrugFactory.class, drugFactory);
        factoryLocator.register(OperationFactory.class, operationFactory);

        FactoryLocator.load(factoryLocator);
    }

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
    public void whenCreateOperationRepositoryShouldReturnHibernateInstance() {
        OperationRepository operationRepository = repositoryFactory.createOperationRepository();

        assertEquals(OperationHibernateRepository.class, operationRepository.getClass());
    }
}
