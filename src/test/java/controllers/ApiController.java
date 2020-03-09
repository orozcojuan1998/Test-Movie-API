package controllers;

import helpers.DirectorUrl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.net.URL;

public class ApiController {
    protected URL url;
    protected RequestSpecification requestSpecification;
    protected DirectorUrl buildUrl;

    public ApiController() {
        this.requestSpecification = RestAssured.given().contentType(ContentType.JSON);
        buildUrl = new DirectorUrl();
    }
}
