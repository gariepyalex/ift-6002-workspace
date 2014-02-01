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
import projectH.infrastructure.persistence.inmemory.repository.DrugInMemoryRepository;

@Path("/drugs/")
@Produces("application/json")
public class DrugResource {

	@GET
	@Path("/findByBrandNameOrDescriptor/{keyword}")
	public Collection<DrugDTO> findDrugs(@PathParam("keyword") String keyword) {
		Collection<DrugDTO> drugsFound = findByBrandNameOrDescriptor(keyword);

		return drugsFound;
	}

	private Collection<DrugDTO> findByBrandNameOrDescriptor(String keyword) {
		// TODO use factory
		DrugRepository drugRepository = new DrugInMemoryRepository();
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
