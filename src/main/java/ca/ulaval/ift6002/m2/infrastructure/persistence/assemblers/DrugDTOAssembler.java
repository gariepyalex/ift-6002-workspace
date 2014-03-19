package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.DrugDTO;

public class DrugDTOAssembler extends DTOAssembler<Drug, DrugDTO> {

    @Override
    public DrugDTO toDTO(Drug drug) {
        return new DrugDTO(drug.getDin().toString(), drug.getBrandName(), drug.getDescriptor());
    }

    @Override
    public Drug fromDTO(DrugDTO dto) {
        Din din = new Din(dto.din);

        // TODO Change this
        return null;// new Drug(din, dto.brandName, dto.descriptor);
    }
}
