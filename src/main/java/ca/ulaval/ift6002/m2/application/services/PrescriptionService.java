package ca.ulaval.ift6002.m2.application.services;

import ca.ulaval.ift6002.m2.application.assemblers.PrescriptionDTOAssembler;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionDTO;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.CSVDrugDataAdapter;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository.DrugInMemoryRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository.PrescriptionInMemoryRepository;

public class PrescriptionService {

	// TODO use ServiceLocator
	private final DrugRepository drugRepository = new DrugInMemoryRepository(new CSVDrugDataAdapter());
	private final DateFormatter dateFormatter = new DateFormatter();

	private final PrescriptionDTOAssembler prescriptionAssembler = new PrescriptionDTOAssembler(dateFormatter,
			drugRepository);

	private final PrescriptionRepository prescriptionRepository = new PrescriptionInMemoryRepository();

	public void savePrescription(String patientId, PrescriptionDTO dto) {
		if (!hasEnoughRenewals(dto)) {
			// error
		}

		if (!hasSetDinOrDrugName(dto)) {
			// error
		}

		if (hasSetBothDinAndDrugName(dto)) {
			// error
		}

		Patient patient = new Patient(Integer.valueOf(patientId));
		Prescription prescription = prescriptionAssembler.fromDTO(dto);

		prescriptionRepository.save(patient, prescription);
	}

	private boolean hasEnoughRenewals(PrescriptionDTO dto) {
		return (dto.renewals != null && dto.renewals >= 0);
	}

	private boolean isDinSet(PrescriptionDTO dto) {
		return (!dto.din.trim().isEmpty());
	}

	private boolean isDrugNameSet(PrescriptionDTO dto) {
		return (!dto.name.trim().isEmpty());
	}

	private boolean hasSetDinOrDrugName(PrescriptionDTO dto) {
		return (isDinSet(dto) || isDrugNameSet(dto));
	}

	private boolean hasSetBothDinAndDrugName(PrescriptionDTO dto) {
		return (isDinSet(dto) && isDrugNameSet(dto));
	}

	// if (renewals == null || renewals < 0)
	// throw new
	// InvalidPrescriptionException("The number of renewals must be greater than or equals to zero");

	// if (din.trim().isEmpty() && drugName.trim().isEmpty())
	// throw new
	// InvalidPrescriptionException("A din or drug name must be set");

	// if (!din.isEmpty() && !drugName.isEmpty())
	// throw new
	// InvalidPrescriptionException("You cannot set din and drug name at the same time");

	/**
	 * En faisant un get(DIN), ce if devient inutile (il throw un exception if
	 * not found)
	 **/
	// if (!din.trim().isEmpty() && !drugRepository.isAValidDin(din))
	// throw new InvalidPrescriptionException("The entered dim is invalid");
}
