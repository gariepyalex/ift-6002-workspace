package ca.ulaval.ift6002.m2.application.rest.resources;

import java.util.NoSuchElementException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;
import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.application.validator.response.InstrumentResponseValidator;
import ca.ulaval.ift6002.m2.application.validator.response.InvalidResponseException;
import ca.ulaval.ift6002.m2.application.validator.response.OperationResponseValidator;
import ca.ulaval.ift6002.m2.services.OperationService;

@Path("/interventions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OperationResource extends Resource {

    private static final String NO_PATIENT_FOUND = "INT002";

    private static final String ALREADY_USED_SERIAL_ERROR = "INT011";
    private static final String ALREADY_USED_SERIAL_MESSAGE = "Serial number already in use";

    private final OperationResponseValidator operationValidator = new OperationResponseValidator();
    private final InstrumentResponseValidator instrumentValidator = new InstrumentResponseValidator();

    private final OperationService operationService = new OperationService();

    @POST
    public Response createOperation(@Context UriInfo uri, OperationResponse operationResponse) {
        try {
            operationValidator.validate(operationResponse);

            Integer generatedNumber = operationService.saveOperation(operationResponse);

            return redirectTo(uri, "/" + generatedNumber);
        } catch (InvalidResponseException e) {
            return error(e.getCode(), e.getMessage());
        } catch (IllegalArgumentException e) {
            return error(NO_ELEMENT_FOUND_CODE, e.getMessage());
        } catch (NoSuchElementException e) {
            return error(NO_PATIENT_FOUND, e.getMessage());
        }
    }

    @POST
    @Path("/{noIntervention}/instruments")
    public Response createInstrument(@PathParam("noIntervention") String noIntervention, @Context UriInfo uri,
            InstrumentResponse instrumentResponse) {
        try {
            instrumentValidator.validate(instrumentResponse);

            operationService.saveInstrument(noIntervention, instrumentResponse);

            return redirectTo(uri, "/" + instrumentResponse.typecode + "/" + instrumentResponse.serial);
        } catch (InvalidResponseException e) {
            return error(e.getCode(), e.getMessage());
        } catch (IllegalStateException e) {
            return error(ALREADY_USED_SERIAL_ERROR, ALREADY_USED_SERIAL_MESSAGE);
        } catch (NoSuchElementException e) {
            return error(NO_ELEMENT_FOUND_CODE, e.getMessage());
        }
    }

    @PUT
    @Path("/{noIntervention}/instruments/{typecode}/{serial}")
    public Response modifyInstrumentStatus(@PathParam("noIntervention") String noIntervention,
            @PathParam("typecode") String typecode, @PathParam("serial") String serial,
            InstrumentResponse instrumentResponse) {
        try {
            instrumentValidator.validateNewStatus(instrumentResponse);

            operationService.bookmarkInstrumentToStatus(noIntervention, instrumentResponse);

            return success();
        } catch (InvalidResponseException e) {
            return error(e.getCode(), e.getMessage());
        } catch (NoSuchElementException e) {
            return error(NO_ELEMENT_FOUND_CODE, e.getMessage());
        }
    }
}
