package ca.ulaval.ift6002.m2.application.rest.resources;

import java.util.NoSuchElementException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import ca.ulaval.ift6002.m2.application.responses.ConsumptionResponse;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;
import ca.ulaval.ift6002.m2.application.validator.response.ConsumptionResponseValidator;
import ca.ulaval.ift6002.m2.application.validator.response.InvalidResponseException;
import ca.ulaval.ift6002.m2.application.validator.response.PrescriptionResponseValidator;
import ca.ulaval.ift6002.m2.domain.patient.DeadPatientException;
import ca.ulaval.ift6002.m2.services.PatientService;

@Path("/patient/{patientId}/prescriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource extends Resource {

    private static final String DEAD_PATIENT_CODE = "PATDCD";
    private static final String NO_PATIENT_FOUND = "PRES010";

    private final PatientService patientService = new PatientService();
    private final PrescriptionResponseValidator prescriptionValidator = new PrescriptionResponseValidator();
    private final ConsumptionResponseValidator consumptionValidator = new ConsumptionResponseValidator();

    @POST
    public Response createPrescription(@PathParam("patientId") String patientId, @Context UriInfo uri,
            PrescriptionResponse response) {
        try {
            prescriptionValidator.validate(response);

            patientService.savePrescription(patientId, response);

            return success();
        } catch (InvalidResponseException e) {
            return errorBadRequest(e.getCode(), e.getMessage());
        } catch (NoSuchElementException e) {
            return errorBadRequest(NO_ELEMENT_FOUND_CODE, e.getMessage());
        } catch (DeadPatientException e) {
            return error(DEAD_PATIENT_CODE, e.getMessage(), Status.GONE);
        }
    }

    @GET
    public Response findPrescriptions(@PathParam("patientId") String patientId) {
        try {
            PrescriptionResponse[] responses = patientService.getPrescriptions(patientId);

            return Response.ok(responses).build();
        } catch (NoSuchElementException e) {
            return errorBadRequest(NO_PATIENT_FOUND, e.getMessage());
        }
    }

    @POST
    @Path("{patientId}/prescriptions/{prescriptionId}/consommations")
    public Response addConsumption(@PathParam("patientId") String patientId,
            @PathParam("prescriptionId") String prescriptionId, @Context UriInfo uri, ConsumptionResponse response) {
        try {
            consumptionValidator.validate(response);

            patientService.addConsumption(patientId, prescriptionId, response);

            return success();
        } catch (InvalidResponseException e) {
            return errorBadRequest(e.getCode(), e.getMessage());
        }

    }

}
