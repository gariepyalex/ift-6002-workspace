package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import java.util.ArrayList;
import java.util.Collection;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.DrugDTO;

public class DrugDTOAssembler {

    public DrugDTO toDTO(Drug drug) {
        return new DrugDTO(drug.getDin().toString(), drug.getBrandName(), drug.getDescriptor());
    }

    public Drug fromDTO(DrugDTO dto) {
        Din din = new Din(dto.din);

        return new Drug(din, dto.brandName, dto.descriptor);
    }

    public Collection<Drug> fromDTOs(Collection<DrugDTO> dtos) {
        Collection<Drug> drugs = new ArrayList<>(dtos.size());

        for (DrugDTO dto : dtos) {
            Drug drug = fromDTO(dto);

            drugs.add(drug);
        }

        return drugs;
    }
}
