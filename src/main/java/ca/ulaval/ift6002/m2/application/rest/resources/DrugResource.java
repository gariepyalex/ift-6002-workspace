package ca.ulaval.ift6002.m2.application.rest.resources;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.ift6002.m2.application.assemblers.DrugResponseAssembler;
import ca.ulaval.ift6002.m2.application.responses.DrugResponse;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.services.DrugService;

@Path("/medicaments")
@Produces(MediaType.APPLICATION_JSON)
public class DrugResource extends Resource {

    private static final String INVALID_SEARCH_ERROR_CODE = "DIN001";

    private final DrugResponseAssembler drugAssembler = new DrugResponseAssembler();

    private final DrugService drugService = new DrugService();

    @GET
    @Path("/dins/{keyword}")
    public Response findDrugs(@PathParam("keyword") String keyword) {
        try {
            Collection<Drug> drugsFound = drugService.findBy(keyword);
            DrugResponse[] responses = drugAssembler.toResponses(drugsFound);

            return success(responses);
        } catch (IllegalArgumentException e) {
            return badRequest(INVALID_SEARCH_ERROR_CODE, e.getMessage());
        }
    }

}
