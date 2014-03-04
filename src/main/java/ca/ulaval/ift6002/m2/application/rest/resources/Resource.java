package ca.ulaval.ift6002.m2.application.rest.resources;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import ca.ulaval.ift6002.m2.application.responses.ExceptionResponse;

public abstract class Resource {

    protected static final String NO_ELEMENT_FOUND_CODE = "LOGIC001";

    protected Response error(String code, String message) {
        ExceptionResponse exception = new ExceptionResponse(code, message);

        return Response.status(Status.BAD_REQUEST).entity(exception).build();
    }

    protected Response success() {
        return Response.ok().build();
    }

    protected Response redirectTo(UriInfo uri, String url) {
        URI uriLocation = URI.create(uri.getRequestUri().toString() + url);

        return Response.created(uriLocation).build();
    }
}
