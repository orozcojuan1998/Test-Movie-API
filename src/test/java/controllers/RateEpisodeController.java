package controllers;

import builders.UrlBuilder;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class RateEpisodeController extends ApiController {

    private Response response;

    public RateEpisodeController() {
        super(new UrlBuilder().addDomain().addPathStep(
                PropertiesHelper.getValueByKey("url.tv")).build());

    }

    public Response rateEpisode (String value, String tvId, String season, String episodeNumber , String session_id) {
        URL idUrl = new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.tv")).
                addPathStep(tvId).
                addPathStep(PropertiesHelper.getValueByKey("url.season")).
                addPathStep(season).
                addPathStep(PropertiesHelper.getValueByKey("url.episode")).
                addPathStep(episodeNumber).
                addPathStep( PropertiesHelper.getValueByKey("url.rating")).
                build();
        response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                and().queryParam("session_id",session_id).
                when().
                body(value).
                post(idUrl);
        return response;
    }


    public Response deleteRating(String tvId, String season, String episodeNumber , String session_id)  {
        URL idUrl = new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.tv")).
                addPathStep(tvId).
                addPathStep(PropertiesHelper.getValueByKey("url.season")).
                addPathStep(season).
                addPathStep(PropertiesHelper.getValueByKey("url.episode")).
                addPathStep(episodeNumber).
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
