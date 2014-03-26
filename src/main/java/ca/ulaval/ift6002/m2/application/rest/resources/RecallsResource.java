package ca.ulaval.ift6002.m2.application.rest.resources;

import java.util.NoSuchElementException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.ift6002.m2.application.responses.RecallsResponse;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

@Path("/rappels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecallsResource extends Resource {

    private static final String PATIENT_NOT_FOUND = "PRES010";

    @GET
    @Path("/contamination/par-patient/{patientId}")
    public Response findDrugs(@PathParam("patientId") Integer patientId) {
        try {
            PatientRepository patientRepository = RepositoryLocator.getPatientRepository();
            patientRepository.get(patientId);
            RecallsResponse[] responses = new RecallsResponse[42];
            // RecallsResponse[] responses =
            // getPatientsThatHaveBeenInContactWithInstrumentsUsedToOperateTheContaminated(patient);

            return success(responses);
        } catch (NoSuchElementException e) {
            return notFound(PATIENT_NOT_FOUND, e.getMessage());
        }
    }

}
