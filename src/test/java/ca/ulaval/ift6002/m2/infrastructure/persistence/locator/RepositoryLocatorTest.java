package ca.ulaval.ift6002.m2.infrastructure.persistence.locator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;

public class RepositoryLocatorTest {

    private static final DrugRepository DRUG_REPOSITORY_IMPLEMENTATION = mock(DrugRepository.class);
    private static final OperationRepository OPERATION_REPOSITORY_IMPLEMENTATION = mock(OperationRepository.class);
    private static final PatientRepository PATIENT_REPOSITORY_IMPLEMENTATION = mock(PatientRepository.class);

    @Before
    public void setUp() {
        RepositoryLocator locator = new RepositoryLocator();

        locator.register(DrugRepository.class, DRUG_REPOSITORY_IMPLEMENTATION);
        locator.register(OperationRepository.class, OPERATION_REPOSITORY_IMPLEMENTATION);
        locator.register(PatientRepository.class, PATIENT_REPOSITORY_IMPLEMENTATION);

        RepositoryLocator.load(locator);
    }

    @Test
    public void givenLocatorWhenGettingDrugRepositoryShouldReturnAnInstanceOfDrugRepository() {
        DrugRepository drugRepositoryFound = RepositoryLocator.getDrugRepository();

        assertEquals(DRUG_REPOSITORY_IMPLEMENTATION, drugRepositoryFound);
    }

    @Test
    public void givenLocatorWhenGettingOperationRepositoryShouldReturnAnInstanceOfOperationRepository() {
        OperationRepository operationRepositoryFound = RepositoryLocator.getOperationRepository();

        assertEquals(OPERATION_REPOSITORY_IMPLEMENTATION, operationRepositoryFound);
    }

    @Test
    public void givenLocatorWhenGettingPatientRepositoryShouldReturnAnInstanceOfPatientRepository() {
        PatientRepository patientRepositoryFound = RepositoryLocator.getPatientRepository();

        assertEquals(PATIENT_REPOSITORY_IMPLEMENTATION, patientRepositoryFound);
    }
}
