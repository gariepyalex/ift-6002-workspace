package ca.ulaval.ift6002.m2.application.rest.resources;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ca.ulaval.ift6002.m2.application.responses.ExceptionResponse;

public abstract class Resource {

    protected Response fromException(String code, String message) {
        ExceptionResponse exception = new ExceptionResponse(code, message);

        return Response.status(Status.BAD_REQUEST).entity(exception).build();
    }
}
