package projectH.application.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import projectH.application.responses.ExceptionDTO;
import projectH.application.responses.PrescriptionDTO;
import projectH.application.services.PrescriptionService;
import projectH.domain.prescription.InvalidPrescriptionException;

@Path("/patient/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrescriptionResource {

    private static final String ERROR_CODE = "PRES001";

    private final PrescriptionService prescriptionService = new PrescriptionService();

    @POST
    @Path("{patientId}/prescriptions")
    public Response createPrescription(@PathParam("patientId") String patientId, @Context UriInfo uri,
            PrescriptionDTO dto) {
        try {
            prescriptionService.savePrescription(patientId, dto);

            return Response.created(uri.getRequestUri()).build();
        } catch (InvalidPrescriptionException e) {
            ExceptionDTO exception = new ExceptionDTO(ERROR_CODE, e.getMessage());

            return Response.status(Status.BAD_REQUEST).entity(exception).build();
        }
    }

}
