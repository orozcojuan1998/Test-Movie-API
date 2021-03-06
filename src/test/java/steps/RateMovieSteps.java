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

    private ResponseBody movieResponse;
    private Response response;
    private Double value;
    private URL idUrl;
    private String movieId;
    private DirectorUrl buildUrl = new DirectorUrl();
    private RateMovieController  rateMovieController= new RateMovieController();

    @Given("^The movie with data already exist$")
    public void theMovieWithDataAlreadyExist(DataTable movieData) {
        List<Map<String, String>> data = movieData.asMaps(String.class, String.class);
        movieId = data.get(0).get("id");


    }

    @When("^The user send a request to rate the movie with its data$")
    public void theUserSendARequestToRateTheMovie(DataTable valueData) {
        idUrl = buildUrl.buildRateMovie(movieId);
        List<Map<String, String>> data = valueData.asMaps(String.class, String.class);
        value = Double.valueOf(data.get(0).get("value"));
        String valueBody =  JsonHelper.setValueParam(value);
        response = rateMovieController.rateMovie(valueBody, Serenity.sessionVariableCalled("session_id"),idUrl);
        movieResponse = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status_message").to(movieResponse.getStatus_message());


    }

    @When("^The user send a request to delete the rated movie$")
    public void theUserSendARequestToDeleteTheRatedMovie() {
        idUrl = buildUrl.buildRateMovie(movieId);
        response = rateMovieController.deleteRating(Serenity.sessionVariableCalled("session_id"),idUrl);
        movieResponse = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status_message").to(movieResponse.getStatus_message());

    }

    @When("^The guest send a request to rate the movie with its data$")
    public void theGuestSendARequestToRateTheMovieWithItsData(DataTable valueData) {
        idUrl = buildUrl.buildRateMovie(movieId);
        List<Map<String, String>> data = valueData.asMaps(String.class, String.class);
        value = Double.valueOf(data.get(0).get("value"));
        String valueBody =  JsonHelper.setValueParam(value);
        response = rateMovieController.rateMovieGuest(valueBody, Serenity.sessionVariableCalled("guest_session_id"),idUrl);
        movieResponse = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status_message").to(movieResponse.getStatus_message());
    }

    @When("^The guest send a request to delete the rated movie$")
    public void theGuestSendARequestToDeleteTheRatedMovie() {
        idUrl = buildUrl.buildRateMovie(movieId);
        response = rateMovieController.deleteRatingGuest(Serenity.sessionVariableCalled("guest_session_id"),idUrl);
        movieResponse = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status_message").to(movieResponse.getStatus_message());
    }
}
