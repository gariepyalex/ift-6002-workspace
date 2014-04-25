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
import javax.ws.rs.core.UriInfo;

import ca.ulaval.ift6002.m2.application.requests.ConsumptionRequest;
import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;
import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;
import ca.ulaval.ift6002.m2.application.validators.requests.ConsumptionRequestValidator;
import ca.ulaval.ift6002.m2.application.validators.requests.InvalidRequestException;
import ca.ulaval.ift6002.m2.application.validators.requests.PrescriptionRequestValidator;
import ca.ulaval.ift6002.m2.domain.patient.DeadPatientException;
import ca.ulaval.ift6002.m2.domain.patient.InteractionDetectionException;
import ca.ulaval.ift6002.m2.domain.prescription.NotEnoughRenewalsException;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionNotFoundException;
import ca.ulaval.ift6002.m2.services.PatientService;

@Path("/patient/{patientId}/prescriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource extends Resource {

    private static final String INVALID_PRESCRIPTION_CODE = "PRES001";
    private static final String DEAD_PATIENT_CODE = "PATDCD";
    private static final String NO_PATIENT_FOUND_CODE = "PRES010";
    private static final String NO_PRESCRIPTION_FOUND_CODE = "PRES011";
    private static final String NOT_ENOUGH_RENEWALS_CODE = "PRES012";
    private static final String INTERACTION_CODE = "PRES002";

    private final PatientService patientService = new PatientService();
    private final PrescriptionRequestValidator prescriptionValidator = new PrescriptionRequestValidator();
    private final ConsumptionRequestValidator consumptionValidator = new ConsumptionRequestValidator();

    @POST
    public Response createPrescription(@PathParam("patientId") String patientId, @Context UriInfo uri,
            PrescriptionRequest request) {
        try {
            prescriptionValidator.validate(request);

            Integer generatedNumber = patientService.savePrescription(patientId, request);

            return redirectTo(uri, "/" + generatedNumber);
        } catch (InvalidRequestException e) {
            return badRequest(e.getCode(), e.getMessage());
        } catch (NoSuchElementException e) {
            return badRequest(INVALID_PRESCRIPTION_CODE, e.getMessage());
        } catch (DeadPatientException e) {
            return gone(DEAD_PATIENT_CODE, e.getMessage());
        } catch (InteractionDetectionException e) {
            return conflict(INTERACTION_CODE, e.getMessage());
        }
    }

    @GET
    public Response findPrescriptions(@PathParam("patientId") String patientId) {
        try {
            PrescriptionResponse[] responses = patientService.getPrescriptions(patientId);

            return success(responses);
        } catch (NoSuchElementException e) {
            return notFound(NO_PATIENT_FOUND_CODE, e.getMessage());
        }
    }

    @POST
    @Path("/{prescriptionId}/consommations")
    public Response addConsumption(@PathParam("patientId") String patientId,
            @PathParam("prescriptionId") String prescriptionId, @Context UriInfo uri, ConsumptionRequest request) {
        try {
            consumptionValidator.validate(request);

            patientService.addConsumption(patientId, prescriptionId, request);

            return success();
        } catch (InvalidRequestException e) {
            return badRequest(e.getCode(), e.getMessage());
        } catch (NoSuchElementException e) {
            return notFound(NO_PATIENT_FOUND_CODE, e.getMessage());
        } catch (PrescriptionNotFoundException e) {
            return notFound(NO_PRESCRIPTION_FOUND_CODE, e.getMessage());
        } catch (NotEnoughRenewalsException e) {
            return badRequest(NOT_ENOUGH_RENEWALS_CODE, e.getMessage());
        }

    }

}
