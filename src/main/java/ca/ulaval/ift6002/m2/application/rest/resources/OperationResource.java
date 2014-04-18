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

import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.application.requests.OperationRequest;
import ca.ulaval.ift6002.m2.application.validators.requests.InstrumentRequestValidator;
import ca.ulaval.ift6002.m2.application.validators.requests.InvalidRequestException;
import ca.ulaval.ift6002.m2.application.validators.requests.OperationRequestValidator;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;
import ca.ulaval.ift6002.m2.services.OperationService;

@Path("/interventions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OperationResource extends Resource {

    private static final String NO_PATIENT_FOUND = "INT002";
    private static final String INVALID_INSTRUMENT = "INT030";

    private static final String ALREADY_USED_SERIAL_ERROR = "INT011";
    private static final String ALREADY_USED_SERIAL_MESSAGE = "Serial number already in use";

    private final OperationRequestValidator operationValidator = new OperationRequestValidator();
    private final InstrumentRequestValidator instrumentValidator = new InstrumentRequestValidator();

    private final OperationService operationService = new OperationService();

    @POST
    public Response createOperation(@Context UriInfo uri, OperationRequest operationRequest) {
        try {
            operationValidator.validate(operationRequest);

            Integer generatedNumber = operationService.saveOperation(operationRequest);

            return redirectTo(uri, "/" + generatedNumber);
        } catch (InvalidRequestException e) {
            return badRequest(e.getCode(), e.getMessage());
        } catch (IllegalArgumentException e) {
            return badRequest(NO_ELEMENT_FOUND_CODE, e.getMessage());
        } catch (NoSuchElementException e) {
            return badRequest(NO_PATIENT_FOUND, e.getMessage());
        }
    }

    @POST
    @Path("/{noIntervention}/instruments")
    public Response createInstrument(@PathParam("noIntervention") String noIntervention, @Context UriInfo uri,
            InstrumentRequest instrumentResponse) {
        try {
            instrumentValidator.validate(instrumentResponse);

            operationService.saveInstrument(noIntervention, instrumentResponse);

            return redirectTo(uri, "/" + instrumentResponse.typecode + "/" + instrumentResponse.serial);
        } catch (InvalidRequestException e) {
            return badRequest(e.getCode(), e.getMessage());
        } catch (IllegalStateException e) {
            return badRequest(ALREADY_USED_SERIAL_ERROR, ALREADY_USED_SERIAL_MESSAGE);
        } catch (NoSuchElementException e) {
            return badRequest(NO_ELEMENT_FOUND_CODE, e.getMessage());
        } catch (InvalidInstrumentException e) {
            return badRequest(INVALID_INSTRUMENT, e.getMessage());
        }
    }

    @PUT
    @Path("/{noIntervention}/instruments/{typecode}/{serial}")
    public Response modifyInstrumentStatus(@PathParam("noIntervention") String noIntervention,
            @PathParam("typecode") String typecode, @PathParam("serial") String serial,
            InstrumentRequest instrumentResponse) {
        try {
            instrumentValidator.validateStatus(instrumentResponse);

            operationService.bookmarkInstrumentToStatus(noIntervention, instrumentResponse);

            return success();
        } catch (InvalidRequestException e) {
            return badRequest(e.getCode(), e.getMessage());
        } catch (NoSuchElementException e) {
            return badRequest(NO_ELEMENT_FOUND_CODE, e.getMessage());
        }
    }
}
