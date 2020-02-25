package steps;

import controllers.AuthenticationController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.RequestToken;
import entities.SessionData;
import entities.User;
import helpers.JsonHelper;
import helpers.PropertiesHelper;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class AuthenticationSteps {
    private RequestToken requestToken;
    private SessionData sessionData;
    private Response response;
    private User user;
    private AuthenticationController authenticationController = AuthenticationController.GetAuthenticationControllerInstance();

    @Given("^A new request token needs to be created$")
    public void aNewRequestTokenNeedsToBeCreated() {
        requestToken = new RequestToken();
    }

    @When("^The user send a request to create the request token$")
    public void theUserSendARequestToCreateTheRequestToken() {
        response = authenticationController.createRequestToken();
    }

    @And("^The response contains the request token$")
    public void theResponseContainsTheRequestToken() {
        Assert.assertThat(String.format("Error: The request token is empty: %s", requestToken.getRequestToken()),
                requestToken.isSuccess(),  Matchers.equalTo(true));
    }

    @Given("^A new session with login needs to be created$")
    public void aNewSessionWithLoginNeedsToBeCreated() {
        user = new User(PropertiesHelper.getValueByKey("api.username"),PropertiesHelper.getValueByKey("api.password"));
    }

    @When("^The user send a request to create the session with login$")
    public void theUserSendARequestToCreateTheSessionWithLogin() {
        System.out.println((String)Serenity.sessionVariableCalled("request_token"));
        String body = JsonHelper.createSessionBody(user,Serenity.sessionVariableCalled("request_token"));
        response = authenticationController.setSessionWithLogin(body);
    }

    @Given("^A new request session needs to be created$")
    public void aNewRequestSessionNeedsToBeCreated() {
        sessionData = new SessionData();

    }

    @When("^The user send a request to session$")
    public void theUserSendARequestToSession() {
        String body = JsonHelper.createSessionCreationBody(Serenity.sessionVariableCalled("request_token"));
        response = authenticationController.createNewSession(body);

    }

    @And("^The response contains the session id$")
    public void theResponseContainsTheSessionId() {
        sessionData = JsonHelper.sessionToResponse(response);
        Assert.assertThat(String.format("Error: The request token is empty: %s", requestToken.getRequestToken()),
                sessionData.isSuccess(),  Matchers.equalTo(Boolean.TRUE));

    }

    @And("^The response contains the field success equals to \"([^\"]*)\"$")
    public void theResponseContainsTheFieldSuccessEqualsTo(String success) throws Throwable {
        Boolean key = Serenity.sessionVariableCalled("success");
        Assert.assertThat("Error: The request token is unsuccessful",
                key,  Matchers.equalTo(Boolean.valueOf(success)));
    }


    @Then("^The token is generated$")
    public void theTokenIsGenerated() {
        requestToken = JsonHelper.responseRequestTokenToJson(response);
        Serenity.setSessionVariable("request_token").to(requestToken.getRequestToken());
        Serenity.setSessionVariable("success").to(requestToken.isSuccess());

    }

    @When("^The user send a request to delete the session$")
    public void theUserSendARequestToDeleteTheSession() {
        
    }

    @Given("^That i want to delete an existing session id$")
    public void thatIWantToDeleteAnExistingSessionId() {
    }
}
