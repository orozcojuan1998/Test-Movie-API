package controllers;


import io.restassured.response.Response;

import java.net.URL;

public class GuestAuthController extends ApiController {

    private static GuestAuthController guestAuthController;
    private Response response;

    public GuestAuthController() {
        super();
    }

    public Response createGuestToken(URL idUrl) {
        response = requestSpecification.given().queryParam("api_key",System.getenv("API_KEY")).
                when().
                get(idUrl);
        return response;
    }

    public Response createGuestTokenInvalid(URL idUrl) {
        response = requestSpecification.given().queryParam("api_key",System.getenv("API_KEY").concat("s3")).
                when().
                get(idUrl);
        return response;
    }

}
