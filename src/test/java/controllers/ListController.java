package controllers;

import builders.UrlBuilder;
import entities.ListCreation;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class ListController extends ApiController{

    private Response response;


    public ListController() {
        super();

    }

    public Response getListDetail(URL idUrl) {
        response = requestSpecification.given()
        .queryParam("api_key",System.getenv("API_KEY"))
                .when().get(idUrl);

        return response;
    }

    public Response createList(String body, String session_id,URL idUrl) {
        response = requestSpecification.given().queryParam("api_key",System.getenv("API_KEY")).and().
                queryParam("session_id",session_id).when().body(body).post(idUrl);
        return response;
    }

    public Response deleteList(String session_id,URL idUrl) {
        response = requestSpecification.given().queryParam("api_key",System.getenv("API_KEY")).
                and().
                queryParam("session_id",session_id).when().delete(idUrl);
        return response;
    }

    public Response addMovie(String body, String session_id,URL idUrl) {
        response = requestSpecification.given().queryParam("api_key",System.getenv("API_KEY")).
                and().
                queryParam("session_id",session_id).when().
                body(body).
                post(idUrl);
        return response;
    }

    public Response deleteMovie(String body, String session_id,URL idUrl) {
        response = requestSpecification.given().queryParam("api_key",System.getenv("API_KEY")).
                and().
                queryParam("session_id",session_id).when().
                body(body).
                post(idUrl);
        return response;
    }

    public Response clearList(boolean confirm, String session_id,URL idUrl) {
        response = requestSpecification.given().queryParam("api_key",System.getenv("API_KEY")).
                and().
                queryParam("session_id",session_id).and().queryParam("confirm",confirm).
                when().
                post(idUrl);
        return response;
    }

    public Response checkItemStatus(Integer value, String session_id,URL idUrl) {
        response = requestSpecification.given().queryParam("api_key",System.getenv("API_KEY")).
                and().
                queryParam("session_id",session_id).and().queryParam("movie_id",value).
                when().
                get(idUrl);
        return response;

    }
}
