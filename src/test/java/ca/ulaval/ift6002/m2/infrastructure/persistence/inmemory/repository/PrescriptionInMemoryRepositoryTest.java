package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;

public class PrescriptionInMemoryRepositoryTest {

    @Test
    public void canCreateEmptyRepository() {
        PrescriptionInMemoryRepository repository = new PrescriptionInMemoryRepository();
        assertTrue(repository.isEmpty());
    }

    @Test
    public void canSavePrescription() {
        PrescriptionInMemoryRepository repository = new PrescriptionInMemoryRepository();
        repository.save(new Patient(101010), getPrescription());
        assertFalse(repository.isEmpty());
    }

    private Prescription getPrescription() {
        return mock(Prescription.class);
    }
}
