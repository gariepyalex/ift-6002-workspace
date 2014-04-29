package ca.ulaval.ift6002.m2.acceptance.builder;

import static com.jayway.restassured.RestAssured.given;
import ca.ulaval.ift6002.m2.acceptance.fixtures.JsonFixture;
import ca.ulaval.ift6002.m2.acceptance.runners.JettyTestRunner;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class RequestBuilder {

    private RequestSpecification request;
    private final JsonFixture jsonFixture = new JsonFixture();

    public RequestBuilder() {
        request = given();
        useDefaultSettings();
    }

    public RequestBuilder useDefaultSettings() {
        return withPort(JettyTestRunner.JETTY_TEST_PORT).using(ContentType.JSON);
    }

    public RequestBuilder withPort(int port) {
        request = request.port(port);
        return this;
    }

    public RequestBuilder using(ContentType contentType) {
        request = request.contentType(contentType);
        return this;
    }

    public RequestBuilder withContent(Object requestObject) {
        String body = jsonFixture.convertToJson(requestObject);
        request = request.body(body);
        return this;
    }

    public RequestBuilder withQueryParam(String parameter, String value) {
        request.queryParam(parameter, value);
        return this;
    }

    public Response doGet(String url, Object... pathParams) {
        return request.get(url, pathParams);
    }

    public Response doPost(String url, Object... pathParams) {
        return request.post(url, pathParams);
    }

    public Response doPut(String url, Object... pathParams) {
        return request.put(url, pathParams);
    }
}
