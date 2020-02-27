package controllers;

import builders.UrlBuilder;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class RateMovieController extends ApiController {

    private Response response;

    public RateMovieController() {
        super(new UrlBuilder().addDomain().addPathStep(
                PropertiesHelper.getValueByKey("url.rating")).build());

    }

    public Response rateMovie (String value, String movieId, String session_id) {
        URL idUrl = new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.movie")).
                addPathStep(movieId).
                addPathStep( PropertiesHelper.getValueByKey("url.rating")).
                build();
        System.out.println(idUrl);
        response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                and().queryParam("session_id",session_id).
                when().
                body(value).
                post(idUrl);
        return response;
    }


    public Response deleteRating(String movieId, String session_id)  {
        URL idUrl = new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.movie")).
                addPathStep(movieId).
                addPathStep( PropertiesHelper.getValueByKey("url.rating")).
                build();

        response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                and().queryParam("session_id",session_id).
                when().
                delete(idUrl);
        return response;
    }
}
