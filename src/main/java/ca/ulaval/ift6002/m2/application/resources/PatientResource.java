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

import ca.ulaval.ift6002.m2.application.assemblers.PrescriptionDTOAssembler;
import ca.ulaval.ift6002.m2.application.responses.ExceptionDTO;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionDTO;
import ca.ulaval.ift6002.m2.application.validator.dto.InvalidDTOException;
import ca.ulaval.ift6002.m2.application.validator.dto.PrescriptionDTOValidator;
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

    private final PrescriptionDTOAssembler assembler = new PrescriptionDTOAssembler(dateFormatter, drugRepository);

    private final PatientService patientService = new PatientService(patientRepository, assembler);

    private final PrescriptionDTOValidator prescriptionValidator = new PrescriptionDTOValidator();

    @POST
    @Path("{patientId}/prescriptions")
    public Response createPrescription(@PathParam("patientId") String patientId, @Context UriInfo uri,
            PrescriptionDTO dto) {
        try {
            prescriptionValidator.validate(dto);

            patientService.savePrescription(patientId, dto);

            return Response.created(uri.getRequestUri()).build();
        } catch (NoSuchElementException | InvalidDTOException e) {
            ExceptionDTO exception = new ExceptionDTO(ERROR_CODE, e.getMessage());

            return Response.status(Status.BAD_REQUEST).entity(exception).build();
        }
    }

}
