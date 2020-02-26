package controllers;

import builders.UrlBuilder;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class GuestAuthController extends ApiController {

    private static GuestAuthController guestAuthController;
    private Response response;

    private GuestAuthController() {
        super(new UrlBuilder().addDomain().addPathStep(
                PropertiesHelper.getValueByKey("url.auth")).build());

    }
    public static GuestAuthController GetGuestAuthController() {
        if (guestAuthController == null)
            guestAuthController = new GuestAuthController();
        return guestAuthController;
    }

    public Response createGuestToken() {
        URL idUrl = new UrlBuilder().
                addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.auth")).
                addPathStep(PropertiesHelper.getValueByKey("url.auth.guest")).
                addPathStep(PropertiesHelper.getValueByKey("url.new")).
                build();
        response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                when().
                get(idUrl);
        return response;
    }

}
