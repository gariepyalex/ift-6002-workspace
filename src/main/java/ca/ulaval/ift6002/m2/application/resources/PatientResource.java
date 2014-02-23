package ca.ulaval.ift6002.m2.application.resources;

import java.util.NoSuchElementException;

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

import ca.ulaval.ift6002.m2.application.assemblers.PrescriptionResponseAssembler;
import ca.ulaval.ift6002.m2.application.responses.ExceptionResponse;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;
import ca.ulaval.ift6002.m2.application.validator.response.InvalidResponseException;
import ca.ulaval.ift6002.m2.application.validator.response.PrescriptionResponseValidator;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;
import ca.ulaval.ift6002.m2.services.PatientService;

@Path("/patient/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {

    private static final String ERROR_CODE = "PRES001";

    private final PatientRepository patientRepository = RepositoryLocator.getPatientRepository();
    private final DrugRepository drugRepository = RepositoryLocator.getDrugRepository();

    private final DateFormatter dateFormatter = new DateFormatter();

    private final PrescriptionResponseAssembler prescriptionResponseAssembler = new PrescriptionResponseAssembler(dateFormatter, drugRepository);

    private final PatientService patientService = new PatientService(patientRepository, prescriptionResponseAssembler);

    private final PrescriptionResponseValidator prescriptionValidator = new PrescriptionResponseValidator();

    @POST
    @Path("{patientId}/prescriptions")
    public Response createPrescription(@PathParam("patientId") String patientId, @Context UriInfo uri,
            PrescriptionResponse dto) {
        try {
            prescriptionValidator.validate(dto);

            patientService.savePrescription(patientId, dto);

            return Response.created(uri.getRequestUri()).build();
        } catch (NoSuchElementException | InvalidResponseException e) {
            ExceptionResponse exception = new ExceptionResponse(ERROR_CODE, e.getMessage());

            return Response.status(Status.BAD_REQUEST).entity(exception).build();
        }
    }

}
