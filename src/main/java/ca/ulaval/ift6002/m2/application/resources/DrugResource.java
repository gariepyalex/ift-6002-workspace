package ca.ulaval.ift6002.m2.application.resources;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ca.ulaval.ift6002.m2.application.assemblers.DrugDTOAssembler;
import ca.ulaval.ift6002.m2.application.responses.DrugDTO;
import ca.ulaval.ift6002.m2.application.responses.ExceptionDTO;
import ca.ulaval.ift6002.m2.application.uivalidator.DrugResourceValidator;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;

@Path("/medicaments/")
@Produces(MediaType.APPLICATION_JSON)
public class DrugResource {

    private static final String INVALID_SEARCH_ERROR_CODE = "DIN001";

    private final DrugRepository drugRepository = RepositoryLocator.getDrugRepository();

    private final DrugDTOAssembler drugAssembler = new DrugDTOAssembler();

    private final DrugResourceValidator drugValidator = new DrugResourceValidator();

    @GET
    @Path("/dins/{keyword}")
    public Response findDrugs(@PathParam("keyword") String keyword) {
        try {
            drugValidator.validateKeyword(keyword);

            Collection<Drug> drugsFound = drugRepository.findByBrandNameOrDescriptor(keyword);
            DrugDTO[] dtos = drugAssembler.toDTOs(drugsFound);

            return Response.ok(dtos).build();
        } catch (Exception e) {
            ExceptionDTO dto = new ExceptionDTO(INVALID_SEARCH_ERROR_CODE, e.getMessage());

            return Response.status(Status.BAD_REQUEST).entity(dto).build();
        }
    }

}
