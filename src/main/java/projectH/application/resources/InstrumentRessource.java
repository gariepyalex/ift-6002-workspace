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
import javax.ws.rs.core.UriInfo;

import projectH.application.responses.ExceptionDTO;
import projectH.application.responses.InstrumentDTO;
import projectH.domain.instrument.Instrument;
import projectH.domain.instrument.InstrumentRepository;
import projectH.infrastructure.persistence.inmemory.repository.InstrumentInMemoryRepository;

@Path("/interventions/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InstrumentRessource {

	private static final String INCOMPLETE_DATA_ERROR = "INT010";
	private static final String INCOMPLETE_DATA_MESSAGE = "Invalid or incomplete data";
	private static final String ALREADY_USED_SERIAL_ERROR = "INT011";
	private static final String ALREADY_USED_SERIAL_MESSAGE = "Serial number already in use";
	private static final String MISSING_SERIAL_ERROR = "INT012";
	private static final String MISSING_SERIAL_MESSAGE = "Requires serial number";
	private static final int BAD_REQUEST = 400;
	private InstrumentRepository instrumentRepository;

	@POST
	@Path("{noIntervention}/instruments")
	public Response createIntervention(@PathParam("noIntervention") String noIntervention, @Context UriInfo uri,
			InstrumentDTO dto) {

		try {
			instrumentRepository = new InstrumentInMemoryRepository();
			Instrument instrument = dto.toInstrument();
			instrumentRepository.saveInstrument(noIntervention, instrument);
			URI uriLocation = URI.create(uri.getRequestUri().toString() + "/" + dto.typecode + "/" + dto.serial);
			return Response.created(uriLocation).build();
		} catch (RuntimeException e) {
			return Response.status(BAD_REQUEST)
					.entity(new ExceptionDTO(INCOMPLETE_DATA_ERROR, INCOMPLETE_DATA_MESSAGE)).build();
		}

	}
}
