package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import helpers.BuildUrl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.net.URL;

public class ApiController {
    protected URL url;
    protected RequestSpecification requestSpecification;
    protected BuildUrl buildUrl;

    public ApiController() {
        this.requestSpecification = RestAssured.given().contentType(ContentType.JSON);
        buildUrl = new BuildUrl();
    }
}
