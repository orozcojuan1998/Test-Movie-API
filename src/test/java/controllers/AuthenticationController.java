package controllers;

import builders.UrlBuilder;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class AuthenticationController extends ApiController {

    private static AuthenticationController authenticationControllerInstance;
    private Response response;

    private AuthenticationController() {
        super(new UrlBuilder().addDomain().addPathStep(
                PropertiesHelper.getValueByKey("url.auth")).build());

    }
    public static AuthenticationController GetAuthenticationControllerInstance() {
        if (authenticationControllerInstance == null)
            authenticationControllerInstance = new AuthenticationController();
        return authenticationControllerInstance;
    }


    public Response createRequestToken() {
        URL idUrl = new UrlBuilder().
                addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.auth")).
                addPathStep(PropertiesHelper.getValueByKey("url.auth.token")).
                addPathStep(PropertiesHelper.getValueByKey("url.new")).
                build();
        response = requestSpecification.given().
                queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                when().
                get(idUrl);
        return response;
    }

    public Response setSessionWithLogin(String body) {
        URL idUrl = new UrlBuilder().
                addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.auth")).
                addPathStep(PropertiesHelper.getValueByKey("url.auth.token")).
                addPathStep(PropertiesHelper.getValueByKey("url.auth.login")).
                build();
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
        URL idUrl = new UrlBuilder().
                addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.auth")).
                addPathStep(PropertiesHelper.getValueByKey("url.auth.session")).
                addPathStep(PropertiesHelper.getValueByKey("auth.new")).
                build();
        response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                when().
                body(body).
                post(idUrl);

        return response;
    }
}
