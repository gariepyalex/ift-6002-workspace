package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Collection;

import ca.ulaval.ift6002.m2.application.responses.DrugDTO;
import ca.ulaval.ift6002.m2.domain.drug.Drug;

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
