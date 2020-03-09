package controllers;

import builders.UrlBuilder;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class RateMovieController extends ApiController {

    private Response response;

    public RateMovieController() {
        super();

    }

    public Response rateMovie (String value, String session_id,URL idUrl) {
        response = requestSpecification.given().queryParam("api_key",System.getenv("API_KEY")).
                and().queryParam("session_id",session_id).
                when().
                body(value).
                post(idUrl);
        return response;
    }


    public Response deleteRating(String session_id,URL idUrl)  {
        response = requestSpecification.given().queryParam("api_key",System.getenv("API_KEY")).
                and().queryParam("session_id",session_id).
                when().
                delete(idUrl);
        return response;
    }
}
