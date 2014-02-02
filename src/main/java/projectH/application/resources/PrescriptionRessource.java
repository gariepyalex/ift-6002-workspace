package projectH.application.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import projectH.application.responses.PrescriptionDTO;
import projectH.domain.drug.DrugRepository;
import projectH.domain.prescription.InvalidPrescriptionException;
import projectH.domain.prescription.Prescription;
import projectH.domain.prescription.PrescriptionRepository;
import projectH.infrastructure.persistence.inmemory.repository.DrugInMemoryRepository;
import projectH.infrastructure.persistence.inmemory.repository.PrescriptionInMemoryRepository;

@Path("/patient/")
public class PrescriptionRessource {
	// TODO use factory
	private static PrescriptionRepository prescriptionRepository = new PrescriptionInMemoryRepository();
	private static DrugRepository drugRepository = new DrugInMemoryRepository();
	@Context
	UriInfo uriInfo;

	@PUT
	@Path("{patient_id}/prescriptions")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPrescription(PrescriptionDTO dto) {
		System.out.println("TEST");
		try {
			Prescription prescription = dto.toPrescription(new DrugInMemoryRepository());
			return Response.created(uriInfo.getAbsolutePath()).build();
		} catch (InvalidPrescriptionException e) {
			return Response.status(401).build();
		}
	}
}
