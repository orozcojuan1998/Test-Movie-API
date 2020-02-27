package controllers;

import builders.UrlBuilder;
import entities.ListCreation;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class ListController extends ApiController{

    private Response response;


    public ListController() {
        super(new UrlBuilder().addDomain().addPathStep(
                PropertiesHelper.getValueByKey("url.list")).build());

    }

    public Response getListDetail() {
        URL idUrl = new UrlBuilder().addDomain().
                    addPathStep(PropertiesHelper.getValueByKey("url.list")).
                    addPathStep(PropertiesHelper.getValueByKey("url.list.id")).
                    build();
        response = requestSpecification.given()
        .queryParam("api_key",PropertiesHelper.getValueByKey("api.key"))
                .when().get(idUrl);

        return response;
    }

    public Response createList(String body, String session_id) {
        response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).and().
                queryParam("session_id",session_id).when().body(body).post(url);
        return response;
    }

    public Response deleteList(String session_id, ListCreation listCreation) {
        URL idUrl = new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.list")).
                addPathStep(String.valueOf(listCreation.getList_id())).
                build();
        response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                and().
                queryParam("session_id",session_id).when().delete(idUrl);
        return response;
    }

    public Response addMovie(String listId, String body, String session_id) {
        URL idUrl = new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.list")).
                addPathStep(String.valueOf(listId)).
                addPathStep(PropertiesHelper.getValueByKey("url.list.add")).
                build();
        response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                and().
                queryParam("session_id",session_id).when().
                body(body).
                post(idUrl);
        return response;
    }

    public Response deleteMovie(String listId, String body, String session_id) {
        URL idUrl = new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.list")).
                addPathStep(String.valueOf(listId)).
                addPathStep(PropertiesHelper.getValueByKey("url.list.remove")).
                build();
        response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                and().
                queryParam("session_id",session_id).when().
                body(body).
                post(idUrl);
        return response;
    }

    public Response clearList(String listId, boolean confirm, String session_id) {
        URL idUrl = new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.list")).
                addPathStep(String.valueOf(listId)).
                addPathStep(PropertiesHelper.getValueByKey("url.list.clear")).
                build();
        response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                and().
                queryParam("session_id",session_id).and().queryParam("confirm",confirm).
                when().
                post(idUrl);
        return response;
    }

    public Response checkItemStatus(String listId, Integer value, String session_id) {
        URL idUrl = new UrlBuilder().addDomain().
                addPathStep(PropertiesHelper.getValueByKey("url.list")).
                addPathStep(String.valueOf(listId)).
                addPathStep(PropertiesHelper.getValueByKey("url.list.item")).
                build();
        response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                and().
                queryParam("session_id",session_id).and().queryParam("movie_id",value).
                when().
                get(idUrl);
        return response;

    }
}
