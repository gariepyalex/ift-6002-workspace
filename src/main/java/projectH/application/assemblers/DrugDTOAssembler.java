package projectH.application.assemblers;

import java.util.Collection;

import projectH.application.responses.DrugDTO;
import projectH.domain.drug.Drug;

public class DrugDTOAssembler {

	public DrugDTO toDTO(Drug drug) {
		return new DrugDTO(drug.getDin().toString(), drug.getBrandName(), drug.getDescriptor());
	}

	public DrugDTO[] toDTOs(Collection<Drug> drugs) {
		DrugDTO[] drugDTOs = new DrugDTO[drugs.size()];
		int i = 0;

		for (Drug drug : drugs) {
			drugDTOs[i++] = toDTO(drug);
		}

		return drugDTOs;
	}

}
