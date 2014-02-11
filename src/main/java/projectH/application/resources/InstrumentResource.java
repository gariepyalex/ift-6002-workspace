package projectH.application.resources;

import java.net.URI;

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

import projectH.application.assemblers.InstrumentDTOAssembler;
import projectH.application.responses.ExceptionDTO;
import projectH.application.responses.InstrumentDTO;
import projectH.domain.instrument.Instrument;
import projectH.domain.instrument.InstrumentRepository;
import projectH.infrastructure.persistence.inmemory.repository.InstrumentInMemoryRepository;

@Path("/interventions/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InstrumentResource {

	private static final String INCOMPLETE_DATA_ERROR = "INT010";
	private static final String INCOMPLETE_DATA_MESSAGE = "Invalid or incomplete data";

	private static final String ALREADY_USED_SERIAL_ERROR = "INT011";
	private static final String ALREADY_USED_SERIAL_MESSAGE = "Serial number already in use";

	private static final String MISSING_SERIAL_ERROR = "INT012";
	private static final String MISSING_SERIAL_MESSAGE = "Requires serial number";

	// TODO use ServiceLocator
	private final InstrumentRepository instrumentRepository = new InstrumentInMemoryRepository();

	private final InstrumentDTOAssembler instrumentAssembler = new InstrumentDTOAssembler();

	@POST
	@Path("{noIntervention}/instruments")
	public Response createInstrument(@PathParam("noIntervention") String noIntervention, @Context UriInfo uri,
			InstrumentDTO dto) {
		if (instrumentRepository.containsSerial(dto.serial)) {
			ExceptionDTO exception = new ExceptionDTO(ALREADY_USED_SERIAL_ERROR, ALREADY_USED_SERIAL_MESSAGE);

			return Response.status(Status.BAD_REQUEST).entity(exception).build();
		}

		try {
			URI uriLocation = URI.create(uri.getRequestUri().toString() + "/" + dto.typecode + "/" + dto.serial);

			Instrument instrument = instrumentAssembler.fromDTO(dto);
			instrumentRepository.save(noIntervention, instrument);

			return Response.created(uriLocation).build();
		} catch (RuntimeException e) {
			ExceptionDTO exception = new ExceptionDTO(INCOMPLETE_DATA_ERROR, INCOMPLETE_DATA_MESSAGE);

			return Response.status(Status.BAD_REQUEST).entity(exception).build();
		}
	}

	@POST
	@Path("{noIntervention}/instruments/{typecode}/{instrumentId}")
	public Response modifyInstrument(@PathParam("noIntervention") String noIntervention,
			@PathParam("typecode") String typecode, @PathParam("instrumentId") String instrumentId, InstrumentDTO dto,
			@Context UriInfo uri) {
		if (dto.serial.isEmpty()) {
			ExceptionDTO exception = new ExceptionDTO(MISSING_SERIAL_ERROR, MISSING_SERIAL_MESSAGE);

			return Response.status(Status.BAD_REQUEST).entity(exception).build();
		}

		try {
			Instrument instrument = instrumentRepository.findBySerial(dto.serial);
			instrument.setStatus(dto.status);

			return Response.ok(instrument).build();
		} catch (Exception e) {
			ExceptionDTO exception = new ExceptionDTO(INCOMPLETE_DATA_ERROR, INCOMPLETE_DATA_MESSAGE);

			return Response.status(Status.BAD_REQUEST).entity(exception).build();
		}
	}
}
