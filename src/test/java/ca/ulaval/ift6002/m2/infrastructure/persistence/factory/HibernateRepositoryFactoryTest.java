package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
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
    public void givenFactoryWhenCreateDrugRepositoryShouldReturnHibernateInstance() {
        DrugRepository drugRepository = repositoryFactory.createDrugRepository();

        assertEquals(DrugHibernateRepository.class, drugRepository.getClass());
    }

    @Test
    public void givenFactoryWhenCreatePatientRepositoryShouldReturnHibernateInstance() {
        PatientRepository patientRepository = repositoryFactory.createPatientRepository();

        assertEquals(PatientHibernateRepository.class, patientRepository.getClass());
    }
}
