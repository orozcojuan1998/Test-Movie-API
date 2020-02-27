package controllers;

import builders.UrlBuilder;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class RateTvController extends ApiController{

    private Response response;

    public RateTvController() {
        super(new UrlBuilder().addDomain().addPathStep(
                PropertiesHelper.getValueByKey("url.tv")).build());

    }

    public Response rateShow (String value, String movieId, String session_id) {
        URL idUrl = new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.tv")).
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


    public Response deleteRating(String showId, String session_id)  {
        URL idUrl = new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.tv")).
                addPathStep(showId).
                addPathStep( PropertiesHelper.getValueByKey("url.rating")).
                build();
        System.out.println(idUrl);
        response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                and().queryParam("session_id",session_id).
                when().
                delete(idUrl);
        return response;
    }
}
