package projectH.application.resources;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import projectH.application.responses.DrugDTO;
import projectH.domain.drug.Drug;
import projectH.domain.drug.DrugRepository;
import projectH.infrastructure.persistence.factory.RepositoryFactory;

@Path("/drugs/")
@Produces("application/json")
public class DrugResource {

	private final DrugRepository drugRepository = RepositoryFactory.getDrugRepository();

	@GET
	@Path("/findByBrandNameOrDescriptor/{keyword}")
	public Collection<DrugDTO> findDrugs(@PathParam("keyword") String keyword) {
		Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(keyword);

		return convertDrugsToDTO(drugsFound);
	}

	private Collection<DrugDTO> convertDrugsToDTO(Collection<Drug> drugs) {
		// TODO use a converter instead
		Collection<DrugDTO> drugsDTO = new ArrayList<>();

		for (Drug drug : drugs) {
			drugsDTO.add(new DrugDTO(drug));
		}

		return drugsDTO;
	}
}
