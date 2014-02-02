package projectH.application.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import projectH.application.responses.PrescriptionDTO;

@Path("/patient/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrescriptionResource {

	@POST
	@Path("{patientId}/prescriptions")
	public Response createPrescription(@PathParam("patientId") String patientId, PrescriptionDTO dto) {
		return Response.ok(dto).build();
	}
}
