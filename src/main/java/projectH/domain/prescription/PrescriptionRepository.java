package projectH.domain.prescription;

import projectH.domain.patient.Patient;

public interface PrescriptionRepository {

	void save(Patient patient, Prescription prescription);
}
