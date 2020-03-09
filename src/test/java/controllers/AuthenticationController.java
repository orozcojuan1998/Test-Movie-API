package controllers;

import helpers.PropertiesHelper;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.net.URL;

public class AuthenticationController extends ApiController {

    private Response response;


    public AuthenticationController() {
        super();

    }

    public Response createRequestToken() {
        URL idUrl = buildUrl.buildAuthToken();
        response = requestSpecification.given().
                queryParam("api_key",System.getenv("API_KEY")).
                when().
                get(idUrl);
        return response;
    }

    public Response setSessionWithLogin(String body) {
        URL idUrl = buildUrl.buildAuthSessionToken();
         response = requestSpecification.given().queryParam("api_key",System.getenv("API_KEY")).
                    when().
                    body(body).
                    post(idUrl);
         return response;

    }

    public Response getResponse() {
        return response;
    }

    public Response createNewSession(JSONObject body) {
        URL idUrl = buildUrl.buildAuthSession();
        response = requestSpecification.given().queryParam("api_key",System.getenv("API_KEY")).
                when().
                body(body.toString()).
                post(idUrl);

        return response;
    }

    public Response deleteSession (JSONObject body){
        URL idUrl = buildUrl.buildAuthSessionDelete();
        response = requestSpecification.given().queryParam("api_key",System.getenv("API_KEY")).
                when().
                body(body.toString()).
                delete(idUrl);
        return response;
    }


}
