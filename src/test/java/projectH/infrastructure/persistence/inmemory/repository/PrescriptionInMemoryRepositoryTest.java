package projectH.infrastructure.persistence.inmemory.repository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import projectH.domain.patient.Patient;
import projectH.domain.prescription.Prescription;

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
