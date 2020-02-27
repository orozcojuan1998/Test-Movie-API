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
        //.and().
        //                queryParam("session_id",session_id)
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
        System.out.println(idUrl);
        System.out.println(session_id);
        response = requestSpecification.given().queryParam("api_key",PropertiesHelper.getValueByKey("api.key")).
                and().
                queryParam("session_id",session_id).when().delete(idUrl);
        return response;
    }
}
