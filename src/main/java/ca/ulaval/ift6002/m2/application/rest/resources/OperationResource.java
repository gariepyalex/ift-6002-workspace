package ca.ulaval.ift6002.m2.application.rest.resources;

import java.net.URI;

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
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;
import ca.ulaval.ift6002.m2.services.OperationService;

@Path("/interventions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OperationResource extends Resource {

    private static final String MISSING_INFORMATION = "INT001";

    private static final String INCOMPLETE_DATA_ERROR = "INT010";
    private static final String INCOMPLETE_DATA_MESSAGE = "Invalid or incomplete data";

    private static final String ALREADY_USED_SERIAL_ERROR = "INT011";
    private static final String ALREADY_USED_SERIAL_MESSAGE = "Serial number already in use";

    private static final String MISSING_SERIAL_ERROR = "INT012";
    private static final String MISSING_SERIAL_MESSAGE = "Requires serial number";

    private final InstrumentResponseValidator instrumentValidator = new InstrumentResponseValidator();

    private final OperationService operationService = new OperationService();

    @POST
    @Path("/interventions")
    public Response createOperation(@Context UriInfo uri, OperationResponse operationResponse) {
        try {
            operationService.saveOperation(operationResponse);

            return Response.created(uri.getRequestUri()).build();
            // TODO: should return '/interventions/$NO_INTERVENTION$'
        } catch (InvalidResponseException e) {
            return fromException(MISSING_INFORMATION, e.getMessage());
        }
    }

    @POST
    @Path("/{noIntervention}/instruments")
    public Response createInstrument(@PathParam("noIntervention") String noIntervention, @Context UriInfo uri,
            InstrumentResponse dto) {
        try {
            instrumentValidator.validate(dto);
            operationService.saveInstrument(noIntervention, dto);

            URI uriLocation = URI.create(uri.getRequestUri().toString() + "/" + dto.typecode + "/" + dto.serial);
            return Response.created(uriLocation).build();
        } catch (InvalidResponseException e) {
            return fromException(INCOMPLETE_DATA_ERROR, INCOMPLETE_DATA_MESSAGE);
        } catch (InvalidInstrumentException e) {
            return fromException(MISSING_SERIAL_ERROR, MISSING_SERIAL_MESSAGE);
        }
    }

    @PUT
    @Path("/{noIntervention}/instruments/{typecode}/{serial}")
    public Response modifyInstrumentStatus(@PathParam("noIntervention") String operationId,
            @PathParam("typecode") String typecode, @PathParam("serial") String serial,
            InstrumentResponse instrumentResponse) {
        try {
            instrumentValidator.validateNewStatus(instrumentResponse);
            operationService.modifyInstrumentStatus(operationId, instrumentResponse);

            // TODO .ok() ? Probably want to return something, or the instrument
            return Response.ok().build();
        } catch (InvalidResponseException e) {
            return fromException(INCOMPLETE_DATA_ERROR, INCOMPLETE_DATA_MESSAGE);
        }
    }
}
