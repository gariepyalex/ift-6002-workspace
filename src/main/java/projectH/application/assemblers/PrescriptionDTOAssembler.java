package projectH.application.assemblers;

import java.util.Date;

import projectH.application.responses.PrescriptionDTO;
import projectH.domain.date.DateFormatter;
import projectH.domain.drug.Din;
import projectH.domain.drug.Drug;
import projectH.domain.drug.DrugRepository;
import projectH.domain.prescription.Practitioner;
import projectH.domain.prescription.Prescription;

public class PrescriptionDTOAssembler {

	private final static DateFormatter FORMATTER = new DateFormatter();
	private final DrugRepository drugRepository;

	public PrescriptionDTOAssembler(DrugRepository drugRepository) {
		this.drugRepository = drugRepository;
	}

	public PrescriptionDTO toDTO(Prescription prescription) {
		String formattedDate = FORMATTER.dateToString(prescription.getDate());

		return new PrescriptionDTO(prescription.getPractioner().toString(), formattedDate, prescription.getRenewals(),
				prescription.getDrug().getDin().toString(), prescription.getDrug().getBrandName());
	}

	public Prescription fromDTO(PrescriptionDTO dto) {
		Practitioner practitioner = new Practitioner(dto.intervenant);
		Date parsedDate = FORMATTER.parse(dto.date);
		Din din = new Din(dto.din);

		Drug drug = drugRepository.get(din);

		return new Prescription(practitioner, parsedDate, dto.renouvellements, drug);
	}
}
