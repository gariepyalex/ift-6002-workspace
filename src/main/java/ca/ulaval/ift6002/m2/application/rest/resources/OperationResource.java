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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import ca.ulaval.ift6002.m2.application.assemblers.InstrumentResponseAssembler;
import ca.ulaval.ift6002.m2.application.responses.ExceptionResponse;
import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;
import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.application.validator.response.InstrumentResponseValidator;
import ca.ulaval.ift6002.m2.application.validator.response.InvalidResponseException;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;
import ca.ulaval.ift6002.m2.services.OperationService;

@Path("/interventions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OperationResource {

	private static final String MISSING_INFORMATION = "INT001";

	private static final String INCOMPLETE_DATA_ERROR = "INT010";
	private static final String INCOMPLETE_DATA_MESSAGE = "Invalid or incomplete data";

	private static final String ALREADY_USED_SERIAL_ERROR = "INT011";
	private static final String ALREADY_USED_SERIAL_MESSAGE = "Serial number already in use";

	private static final String MISSING_SERIAL_ERROR = "INT012";
	private static final String MISSING_SERIAL_MESSAGE = "Requires serial number";

	private final InstrumentResponseValidator instrumentValidator = new InstrumentResponseValidator();
	private final InstrumentResponseAssembler instrumentResponseAssembler = new InstrumentResponseAssembler();

	private final OperationRepository operationRepository = RepositoryLocator.getOperationRepository();

	private final OperationService operationService = new OperationService(operationRepository,
			instrumentResponseAssembler);

	@POST
	public Response createOperation(@Context UriInfo uri, OperationResponse dto) {
		// Operation operation = dto.toOperation();
		// TODO I assume there are some missing calls loll

		return Response.created(uri.getRequestUri()).build();
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
			ExceptionResponse exception = new ExceptionResponse(INCOMPLETE_DATA_ERROR, INCOMPLETE_DATA_MESSAGE);

			return Response.status(Status.BAD_REQUEST).entity(exception).build();
		}
	}

	@PUT
	@Path("/{noIntervention}/instruments/{typecode}/{serial}")
	public Response modifyInstrumentStatus(@PathParam("noIntervention") String noIntervention,
			@PathParam("typecode") String typecode, @PathParam("serial") String serial, InstrumentResponse dto) {
		try {
			instrumentValidator.validateNewStatus(dto);
			operationService.modifyInstrumentStatus(noIntervention, dto);
			return Response.ok().build();
		} catch (InvalidResponseException e) {
			ExceptionResponse exception = new ExceptionResponse(INCOMPLETE_DATA_ERROR, INCOMPLETE_DATA_MESSAGE);

			return Response.status(Status.BAD_REQUEST).entity(exception).build();
		}
	}
}
