package steps;

import controllers.RateMovieController;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.eo.Se;
import entities.ResponseBody;
import helpers.DirectorUrl;
import helpers.JsonHelper;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import org.openqa.selenium.json.Json;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class RateMovieSteps {

    private ResponseBody listResponse;
    private Response response;
    private Double value;
    private URL idUrl;
    private DirectorUrl buildUrl = new DirectorUrl();
    private RateMovieController  rateMovieController= new RateMovieController();

    @Given("^The movie with data already exist$")
    public void theMovieWithDataAlreadyExist(DataTable movieData) {
        List<Map<String, String>> data = movieData.asMaps(String.class, String.class);
        Serenity.setSessionVariable("movie_id").to(data.get(0).get("id"));


    }

    @When("^The user send a request to rate the movie with its data$")
    public void theUserSendARequestToRateTheMovie(DataTable valueData) {
        idUrl = buildUrl.buildRateMovie(Serenity.sessionVariableCalled("movie_id"));
        List<Map<String, String>> data = valueData.asMaps(String.class, String.class);
        value = Double.valueOf(data.get(0).get("value"));
        String valueBody =  "{\"value\""+":"+"\""+value+"\""+"}";
        response = rateMovieController.rateMovie(valueBody, Serenity.sessionVariableCalled("session_id"),idUrl);
        listResponse = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status").to(response.statusCode());
        Serenity.setSessionVariable("status_message").to(listResponse.getStatus_message());


    }

    @When("^The user send a request to delete the rated movie$")
    public void theUserSendARequestToDeleteTheRatedMovie() {
        idUrl = buildUrl.buildRateMovie(Serenity.sessionVariableCalled("movie_id"));
        response = rateMovieController.deleteRating(Serenity.sessionVariableCalled("session_id"),idUrl);
        listResponse = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status").to(response.statusCode());
        Serenity.setSessionVariable("status_message").to(listResponse.getStatus_message());

    }
}
