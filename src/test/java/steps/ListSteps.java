package steps;

import controllers.ListController;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.ListCreation;
import entities.ResponseBody;
import entities.Lists;
import entities.ResponseStatus;
import helpers.DirectorUrl;
import helpers.JsonHelper;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class ListSteps {

    private Lists lists;
    private Response response;
    private Integer value;
    private ListCreation listCreation;
    private ResponseBody listResponse;
    private ResponseStatus responseStatus;
    private URL idUrl;
    private DirectorUrl buildUrl = new DirectorUrl();
    private ListController listController = new ListController();



    @Given("^The user wants to see the details of a list$")
    public void theUserWantsToSeeTheDetailsOfAList() {
        lists = new Lists();
    }

    @When("^The user send the request to get the list detail$")
    public void theUserSendTheRequestToGetTheListDetail() {
        idUrl = buildUrl.buildListDetail();
        response = listController.getListDetail(idUrl);

    }

    @Then("^The response contains the name \"([^\"]*)\" of the list$")
    public void theResponseContainsTheNameOfTheList(String listName) throws Throwable {
        lists = JsonHelper.responseToList(response);
        Assert.assertThat("Error: The request could not be completed",
                listName,  Matchers.equalTo(lists.getName()));
    }

    @Given("^The user wants to create a list$")
    public void theUserWantsToCreateAList() {
        lists = new Lists();
    }

    @When("^The user send the request to create the list with its data$")
    public void theUserSendTheRequestToCreateTheListWithItsData(DataTable listData) {
        idUrl = buildUrl.buildListCreate();
        List<Map<String, String>> data = listData.asMaps(String.class, String.class);
        lists.setName(data.get(0).get("name"));
        lists.setDescription(data.get(0).get("description"));
        String body = JsonHelper.objectToJson(lists);
        response = listController.createList(body, Serenity.sessionVariableCalled("session_id"),idUrl);
        listCreation = JsonHelper.responseToCreationList(response);
        Serenity.setSessionVariable("list_creation").to(listCreation);
        Serenity.setSessionVariable("status").to(response.statusCode());

    }

    @Given("^The user wants to delete a list$")
    public void theUserWantsToDeleteAList() {
        lists = new Lists();
    }

    @And("^The user send the request to delete the list$")
    public void theUserSendTheRequestToDeleteTheList() {
        idUrl = buildUrl.buildListDelete(listCreation);
        ListCreation temporaryList =  Serenity.sessionVariableCalled("list_creation");
        response = listController.deleteList(Serenity.sessionVariableCalled("session_id"), idUrl);
        listResponse = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status_message").to(listResponse.getStatus_message());
    }

    @Then("^The response status message is \"([^\"]*)\"$")
    public void theResponseStatusMessageIs(String message) throws Throwable {
        Assert.assertThat("Error: The request could not be completed",
                Serenity.sessionVariableCalled("status_message") ,  Matchers.equalTo(message));
    }

    @Given("^The list already exist with its data$")
    public void theListAlreadyExistWithItsData(DataTable listData) {
        List<Map<String, String>> data = listData.asMaps(String.class, String.class);
        Serenity.setSessionVariable("listId").to(data.get(0).get("id"));
    }

    @When("^The user send the request to add a movie to a list with its data$")
    public void theUserSendTheRequestToAddAMovieToAListWithItsData(DataTable movieData) {
        idUrl = buildUrl.buildAddMovie(Serenity.sessionVariableCalled("listId"));
        List<Map<String, String>> data = movieData.asMaps(String.class, String.class);
        value = Integer.valueOf(data.get(0).get("id_movie"));
        String valueBody =  "{\"media_id\""+":"+"\""+value+"\""+"}";
        response = listController.addMovie(valueBody, Serenity.sessionVariableCalled("session_id"),idUrl);
        listResponse = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status_message").to(listResponse.getStatus_message());
        Serenity.setSessionVariable("status").to(response.statusCode());

    }

    @When("^The user send the request to delete a movie from a list with its data$")
    public void theUserSendTheRequestToDeleteAMovieFromAListWithItsData(DataTable movieData) {
        idUrl = buildUrl.buildDeleteMovie(Serenity.sessionVariableCalled("listId"));
        List<Map<String, String>> data = movieData.asMaps(String.class, String.class);
        value = Integer.valueOf(data.get(0).get("id_movie"));
        String valueBody =  "{\"media_id\""+":"+"\""+value+"\""+"}";
        response = listController.deleteMovie(valueBody, Serenity.sessionVariableCalled("session_id"),idUrl);
        listResponse = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status_message").to(listResponse.getStatus_message());
        Serenity.setSessionVariable("status").to(response.statusCode());

    }

    @When("^The user send the request to clear the list$")
    public void theUserSendTheRequestToClearTheList() {
        idUrl = buildUrl.buildClearList(Serenity.sessionVariableCalled("listId"));
        response = listController.clearList(true, Serenity.sessionVariableCalled("session_id"),idUrl);
        listResponse = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status_message").to(listResponse.getStatus_message());
        Serenity.setSessionVariable("status").to(response.statusCode());
    }

    @When("^The user send the request to check if a movie is present in the list with its data$")
    public void theUserSendTheRequestToCheckIfAMovieIsPresentInTheListWithItsData(DataTable movieData) {
        idUrl = buildUrl.buildCheckItemStatus(Serenity.sessionVariableCalled("listId"));
        List<Map<String, String>> data = movieData.asMaps(String.class, String.class);
        value = Integer.valueOf(data.get(0).get("id_movie"));
        response = listController.checkItemStatus(value, Serenity.sessionVariableCalled("session_id"),idUrl);
        responseStatus = JsonHelper.responseStatusToListResponse(response);
        Serenity.setSessionVariable("success").to(responseStatus.isItem_present());
        Serenity.setSessionVariable("status").to(response.statusCode());

    }
}
