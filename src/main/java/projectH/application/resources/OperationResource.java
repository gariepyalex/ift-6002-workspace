package projectH.application.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import projectH.application.responses.ExceptionDTO;
import projectH.application.responses.OperationDTO;
import projectH.domain.operation.InvalidOperationException;
import projectH.domain.operation.Operation;

@Produces("application/json")
@Consumes("application/json")
@Path("/operation/")
public class OperationResource {

	private static final int BAD_REQUEST = 400;
	private static final String MISSING_INFORMATION = "INT001";

	@POST
	public Response createOperation(@Context UriInfo uri, OperationDTO dto) {

		try {
			Operation operation = dto.toOperation();
			return Response.created(uri.getRequestUri()).build();
		} catch (InvalidOperationException e) {
			return Response.status(BAD_REQUEST).entity(new ExceptionDTO(MISSING_INFORMATION, e.getMessage())).build();
		}
	}
}