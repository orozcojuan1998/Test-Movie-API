package steps;

import controllers.AuthenticationController;
import controllers.GuestAuthController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.GuestSessionToken;
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
    private GuestSessionToken guestSessionToken;
    private AuthenticationController authenticationController = new AuthenticationController();
    private GuestAuthController guestAuthController = GuestAuthController.GetGuestAuthController();

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
        String body = JsonHelper.createSessionBody(user,(String)Serenity.sessionVariableCalled("request_token"));
        response = authenticationController.setSessionWithLogin(body);
    }

    @Given("^A new request session needs to be created$")
    public void aNewRequestSessionNeedsToBeCreated() {
        sessionData = new SessionData();

    }

    @When("^The user send a request to session$")
    public void theUserSendARequestToSession() {
        String requestTokenCurrent = (String) Serenity.sessionVariableCalled("request_token");
        String body = "{\"request_token\""+":"+"\""+requestTokenCurrent+"\""+"}";
        response = authenticationController.createNewSession(body);

    }

    @Then("^The session is generated$")
    public void theSessionIsGenerated() {
        sessionData = JsonHelper.sessionToResponse(response);
        Serenity.setSessionVariable("session_id").to(sessionData.getSession_id());
        Serenity.setSessionVariable("success_session").to(sessionData.isSuccess());
        Serenity.setSessionVariable("status").to(response.statusCode());
    }

    @And("^The response contains the session id$")
    public void theResponseContainsTheSessionId() {
        Boolean key = Serenity.sessionVariableCalled("success_session");
        Assert.assertThat("Error: The request token is empty",
                key,  Matchers.equalTo(Boolean.TRUE));

    }

    @And("^The response contains the field success equals to \"([^\"]*)\"$")
    public void theResponseContainsTheFieldSuccessEqualsTo(String success) {
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
        String jsonBody = (String) Serenity.sessionVariableCalled("session_id");
        String body =  "{\"session_id\""+":"+"\""+jsonBody+"\""+"}";
        response = authenticationController.deleteSession(body);
        Serenity.setSessionVariable("status").to(response.statusCode());

    }



    @Given("^A new guest session needs to be created$")
    public void aNewGuestSessionNeedsToBeCreated() {
        guestSessionToken = new GuestSessionToken();
    }

    @When("^The guest sends the request to create the session$")
    public void theGuestSendsTheRequestToCreateTheSession() {
        response = guestAuthController.createGuestToken();
        guestSessionToken = JsonHelper.guestSessionToken(response);
        Serenity.setSessionVariable("success").to(guestSessionToken.isSuccess());
        Serenity.setSessionVariable("status").to(response.statusCode());

    }
}
