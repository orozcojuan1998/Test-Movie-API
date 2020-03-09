package controllers;

import builders.UrlBuilder;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class ReviewController extends ApiController{

    private Response response;

    public ReviewController() {
        super();

    }

    public Response getReview(URL idUrl){
        response = requestSpecification.given().
                    queryParam("api_key",System.getenv("API_KEY")).
                    when().
                    get(idUrl);
        return response;
    }
}
