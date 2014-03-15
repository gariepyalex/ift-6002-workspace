package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Collection;

import ca.ulaval.ift6002.m2.application.responses.DrugResponse;
import ca.ulaval.ift6002.m2.domain.drug.Drug;

public class DrugResponseAssembler {

    public DrugResponse toResponse(Drug drug) {
        return new DrugResponse(drug.getDin().toString(), drug.getBrandName(), drug.getDescriptor());
    }

    public DrugResponse[] toResponses(Collection<Drug> drugs) {
        DrugResponse[] drugResponses = new DrugResponse[drugs.size()];
        int i = 0;

        for (Drug drug : drugs) {
            drugResponses[i++] = toResponse(drug);
        }

        return drugResponses;
    }

}
