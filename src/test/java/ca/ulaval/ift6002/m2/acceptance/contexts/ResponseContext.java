package ca.ulaval.ift6002.m2.acceptance.contexts;

import com.jayway.restassured.response.Response;

public class ResponseContext {

    private static Response response;

    public static void setResponse(Response responseInit) {
        response = responseInit;
    }

    public static Response getResponse() {
        return response;
    }

    public static void reset() {
        response = null;
    }
}
