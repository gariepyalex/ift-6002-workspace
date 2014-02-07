package projectH.application.resources;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import projectH.application.responses.DrugDTO;
import projectH.application.responses.ExceptionDTO;
import projectH.domain.drug.Drug;
import projectH.domain.drug.DrugRepository;
import projectH.infrastructure.persistence.factory.RepositoryFactory;

@Path("/drugs/")
@Produces("application/json")
public class DrugResource {

	private final DrugRepository drugRepository = RepositoryFactory.getDrugRepository();

	@GET
	@Path("/findByBrandNameOrDescriptor/{keyword}")
	public Response findDrugs(@PathParam("keyword") String keyword) {
		try {
			Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(keyword);
			DrugDTO[] dto = convertDrugsToDTO(drugsFound);

			return Response.ok(dto).build();
		} catch (Exception e) {
			ExceptionDTO dto = new ExceptionDTO("DIN001", e.getMessage());

			return Response.status(Status.BAD_REQUEST).entity(dto).build();
		}
	}

	private DrugDTO[] convertDrugsToDTO(Collection<Drug> drugs) {
		DrugDTO[] drugsDTO = new DrugDTO[drugs.size()];
		int i = 0;

		for (Drug drug : drugs) {
			drugsDTO[i++] = new DrugDTO(drug);
		}

		return drugsDTO;
	}
}
