package steps;

import com.google.gson.JsonObject;
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
import org.json.JSONObject;
import org.junit.Assert;

public class AuthenticationSteps {
    private RequestToken requestToken;
    private SessionData sessionData;
    private Response response;
    private User user;
    private GuestSessionToken guestSessionToken;
    private String requestTokenNew;
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

    @Then("^The token is generated$")
    public void theTokenIsGenerated() {
        requestToken = JsonHelper.responseRequestTokenToJson(response);
        requestTokenNew = requestToken.getRequestToken();
        Serenity.setSessionVariable("request_token").to(requestToken.getRequestToken());
        Serenity.setSessionVariable("success").to(requestToken.isSuccess());

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
        String body = JsonHelper.createSessionBody(user,requestToken.getRequestToken());
        response = authenticationController.setSessionWithLogin(body);
    }

    @Given("^A new request session needs to be created$")
    public void aNewRequestSessionNeedsToBeCreated() {
        sessionData = new SessionData();

    }

    @When("^The user send a request to session$")
    public void theUserSendARequestToSession() {
        String requestTokenCurrent = (String) Serenity.sessionVariableCalled("request_token");
        JSONObject body = JsonHelper.setRequestParam(requestTokenCurrent);
        System.out.println(body.toString());
        response = authenticationController.createNewSession(body);

    }

    @Then("^The session is generated$")
    public void theSessionIsGenerated() {
        sessionData = JsonHelper.sessionToResponse(response);
        Serenity.setSessionVariable("session_id").to(sessionData.getSession_id());
        Serenity.setSessionVariable("status").to(response.statusCode());
        Boolean key = sessionData.isSuccess();
        Assert.assertThat("Error: The authentication can not be created",
                key,  Matchers.equalTo(Boolean.TRUE));
    }


    @And("^The response contains the field success equals to \"([^\"]*)\"$")
    public void theResponseContainsTheFieldSuccessEqualsTo(String success) {
        Boolean key = Serenity.sessionVariableCalled("success");
        Assert.assertThat("Error: The request token is unsuccessful",
                key,  Matchers.equalTo(Boolean.valueOf(success)));
    }


    @When("^The user send a request to delete the session$")
    public void theUserSendARequestToDeleteTheSession() {
        JSONObject body = JsonHelper.setSessionParam(sessionData.getSession_id());
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
    }
}
