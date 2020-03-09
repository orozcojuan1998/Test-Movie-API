package steps;

import controllers.RateTvController;
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

public class RateTvShowSteps {

    private ResponseBody listResponse;
    private Response response;
    private Double value;
    private URL idUrl;
    private DirectorUrl buildUrl = new DirectorUrl();
    private RateTvController rateTvController = new RateTvController();

    @Given("^The tv show with data already exist$")
    public void theTvShowWithDataAlreadyExist(DataTable tvTable) {
        List<Map<String, String>> data = tvTable.asMaps(String.class, String.class);
        Serenity.setSessionVariable("show_id").to(data.get(0).get("id"));
    }

    @When("^The user send a request to rate the tv show with its data$")
    public void theUserSendARequestToRateTheTvShowWithItsData(DataTable valueData) {
        List<Map<String, String>> data = valueData.asMaps(String.class, String.class);
        value = Double.valueOf(data.get(0).get("value"));
        String valueBody =  "{\"value\""+":"+"\""+value+"\""+"}";
        response = rateTvController.rateShow(valueBody, Serenity.sessionVariableCalled("show_id"), Serenity.sessionVariableCalled("session_id"));
        listResponse = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status").to(response.statusCode());
        Serenity.setSessionVariable("status_message").to(listResponse.getStatus_message());
    }

    @When("^The user send a request to delete the rated tv show$")
    public void theUserSendARequestToDeleteTheRatedTvShow() {
        response = rateTvController.deleteRating(Serenity.sessionVariableCalled("show_id"),Serenity.sessionVariableCalled("session_id"));
        listResponse = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status").to(response.statusCode());
        Serenity.setSessionVariable("status_message").to(listResponse.getStatus_message());
    }


}
