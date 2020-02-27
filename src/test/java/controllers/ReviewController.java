package controllers;

import builders.UrlBuilder;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class ReviewController extends ApiController{

    private Response response;

    public ReviewController() {
        super(new UrlBuilder().addDomain().addPathStep(
                PropertiesHelper.getValueByKey("url.tv")).build());

    }

    public Response getReview(String reviewId){
        URL idUrl = new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.review")).
                addPathStep(reviewId).
                build();
        response = requestSpecification.given().
                    queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                    when().get(idUrl);

        return response;
    }
}
