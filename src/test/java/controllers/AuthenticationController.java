package controllers;

import builders.UrlBuilder;
import helpers.BuildUrl;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class AuthenticationController extends ApiController {

    private Response response;


    public AuthenticationController() {
        super();

    }

    public Response createRequestToken() {
        URL idUrl = buildUrl.buildAuthToken();
        response = requestSpecification.given().
                queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                when().
                get(idUrl);
        return response;
    }

    public Response setSessionWithLogin(String body) {
        URL idUrl = buildUrl.buildAuthSessionToken();
         response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                    when().
                    body(body).
                    post(idUrl);
         return response;

    }

    public Response getResponse() {
        return response;
    }

    public Response createNewSession(String body) {
        URL idUrl = buildUrl.buildAuthSession();
        response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                when().
                body(body).
                post(idUrl);

        return response;
    }

    public Response deleteSession (String body){
        URL idUrl = buildUrl.buildAuthSessionDelete();
        response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                when().
                body(body).
                delete(idUrl);
        return response;
    }


}
