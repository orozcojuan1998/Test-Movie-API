package steps;

import controllers.RateEpisodeController;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import entities.ResponseBody;
import helpers.DirectorUrl;
import helpers.JsonHelper;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class RateEpisodeSteps {

    private ResponseBody listResponse;
    private Response response;
    private Double value;
    private URL idUrl;
    private DirectorUrl buildUrl = new DirectorUrl();
    private RateEpisodeController rateEpisodeController = new RateEpisodeController();


    @Given("^The tv show episode with data already exist$")
    public void theTvShowEpisodeWithDataAlreadyExist(DataTable episodeData) {
        List<Map<String, String>> data = episodeData.asMaps(String.class, String.class);
        Serenity.setSessionVariable("id").to(data.get(0).get("id"));
        Serenity.setSessionVariable("season").to(data.get(0).get("season_number"));
        Serenity.setSessionVariable("episode").to(data.get(0).get("episode_number"));
    }

    @When("^The user send a request to rate the tv show episode with its data$")
    public void theUserSendARequestToRateTheTvShowEpisodeWithItsData(DataTable valueData) {
        List<Map<String, String>> data = valueData.asMaps(String.class, String.class);
        value = Double.valueOf(data.get(0).get("value"));
        String valueBody =  "{\"value\""+":"+"\""+value+"\""+"}";
        response = rateEpisodeController.rateEpisode(valueBody,Serenity.sessionVariableCalled("id"),Serenity.sessionVariableCalled("season"),
                Serenity.sessionVariableCalled("episode"),Serenity.sessionVariableCalled("session_id"));
        listResponse = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status").to(response.statusCode());
        Serenity.setSessionVariable("status_message").to(listResponse.getStatus_message());

    }

    @When("^The user send a request to delete the rated tv episode$")
    public void theUserSendARequestToDeleteTheRatedTvEpisode() {
        response = rateEpisodeController.deleteRating(Serenity.sessionVariableCalled("id"),Serenity.sessionVariableCalled("season"),
                    Serenity.sessionVariableCalled("episode"),Serenity.sessionVariableCalled("session_id"));
        listResponse = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status").to(response.statusCode());
        Serenity.setSessionVariable("status_message").to(listResponse.getStatus_message());
    }
}
