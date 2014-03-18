package ca.ulaval.ift6002.m2.application.rest.resources;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import ca.ulaval.ift6002.m2.application.responses.ExceptionResponse;

public abstract class Resource {

    protected static final String NO_ELEMENT_FOUND_CODE = "LOGIC001";

    protected Response badRequest(String code, String message) {
        return error(code, message, Status.BAD_REQUEST);
    }

    protected Response notFound(String code, String message) {
        return error(code, message, Status.NOT_FOUND);
    }

    protected Response error(String code, String message, Status status) {
        ExceptionResponse exception = new ExceptionResponse(code, message);

        return Response.status(status).entity(exception).build();
    }

    protected Response success() {
        return Response.ok().build();
    }

    protected Response success(Object response) {
        return Response.ok(response).build();
    }

    protected Response redirectTo(UriInfo uri, String url) {
        URI uriLocation = URI.create(uri.getRequestUri().toString() + url);

        return Response.created(uriLocation).build();
    }
}
