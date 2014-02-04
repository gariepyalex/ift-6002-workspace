package projectH.domain.prescription;

public interface PrescriptionRepository {
	public void savePrescription(String patientId, Prescription prescription);
}
