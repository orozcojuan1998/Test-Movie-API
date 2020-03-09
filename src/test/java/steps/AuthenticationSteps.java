package steps;

import com.google.gson.JsonObject;
import controllers.AuthenticationController;
import controllers.GuestAuthController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.*;
import helpers.DirectorUrl;
import helpers.JsonHelper;
import helpers.PropertiesHelper;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;

import java.net.URL;

public class AuthenticationSteps {
    private RequestToken requestToken;
    private SessionData sessionData;
    private Response response;
    private User user;
    private GuestSessionToken guestSessionToken;
    private String requestTokenNew;
    private ResponseBody responseBody;
    private URL idUrl;
    private DirectorUrl buildUrl = new DirectorUrl();
    private AuthenticationController authenticationController = new AuthenticationController();
    private GuestAuthController guestAuthController = new GuestAuthController();

    @Given("^A new request token needs to be created$")
    public void aNewRequestTokenNeedsToBeCreated() {
        requestToken = new RequestToken();
    }

    @When("^The user send a request to create the request token$")
    public void theUserSendARequestToCreateTheRequestToken() {
        idUrl = buildUrl.buildAuthToken();
        response = authenticationController.createRequestToken(idUrl);
    }

    @When("^The user send a request to create the request token with invalid key$")
    public void theUserSendARequestToCreateTheRequestTokenWithInvalidKey() {
        idUrl = buildUrl.buildAuthToken();
        response = authenticationController.createRequestToken(idUrl);
        Serenity.setSessionVariable("success").to(requestToken.isSuccess());
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
        idUrl = buildUrl.buildAuthSessionToken();
        String body = JsonHelper.createSessionBody(user,requestToken.getRequestToken());
        response = authenticationController.setSessionWithLogin(body,idUrl);
    }

    @And("^The user send a request to create the session with login with invalid username$")
    public void theUserSendARequestToCreateTheSessionWithLoginWithInvalidUsername() {
        idUrl = buildUrl.buildAuthSessionToken();
        user.setUsername("invalidUser");
        String body = JsonHelper.createSessionBody(user,requestToken.getRequestToken());
        response = authenticationController.setSessionWithLogin(body,idUrl);
        responseBody = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status_message").to(responseBody.getStatus_message());

    }

    @And("^The user send a request to create the session with login with invalid password$")
    public void theUserSendARequestToCreateTheSessionWithLoginWithInvalidPassword() {
        idUrl = buildUrl.buildAuthSessionToken();
        user.setPassword("uncorrectPassword");
        String body = JsonHelper.createSessionBody(user,requestToken.getRequestToken());
        response = authenticationController.setSessionWithLogin(body,idUrl);
        responseBody = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status_message").to(responseBody.getStatus_message());

    }

    @And("^The user send a request to create the session with login with empty username$")
    public void theUserSendARequestToCreateTheSessionWithLoginWithEmptyUsername() {
        idUrl = buildUrl.buildAuthSessionToken();
        user.setUsername("");
        String body = JsonHelper.createSessionBody(user,requestToken.getRequestToken());
        response = authenticationController.setSessionWithLogin(body,idUrl);
        responseBody = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status_message").to(responseBody.getStatus_message());
    }

    @And("^The user send a request to create the session with login with empty password$")
    public void theUserSendARequestToCreateTheSessionWithLoginWithEmptyPassword() {
        idUrl = buildUrl.buildAuthSessionToken();
        user.setPassword("");
        String body = JsonHelper.createSessionBody(user,requestToken.getRequestToken());
        response = authenticationController.setSessionWithLogin(body,idUrl);
        responseBody = JsonHelper.responsetoListResponse(response);
        Serenity.setSessionVariable("status_message").to(responseBody.getStatus_message());
    }

    @Given("^A new request session needs to be created$")
    public void aNewRequestSessionNeedsToBeCreated() {
        sessionData = new SessionData();

    }

    @When("^The user send a request to session$")
    public void theUserSendARequestToSession() {
        idUrl = buildUrl.buildAuthSession();
        JSONObject body = JsonHelper.setRequestParam(requestToken.getRequestToken());
        System.out.println(body.toString());
        response = authenticationController.createNewSession(body,idUrl);

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


    @When("^The user send a request to delete the session$")
    public void theUserSendARequestToDeleteTheSession() {
        idUrl = buildUrl.buildAuthSessionDelete();
        JSONObject body = JsonHelper.setSessionParam(sessionData.getSession_id());
        response = authenticationController.deleteSession(body,idUrl);
        Serenity.setSessionVariable("status").to(response.statusCode());
    }



    @Given("^A new guest session needs to be created$")
    public void aNewGuestSessionNeedsToBeCreated() {
        guestSessionToken = new GuestSessionToken();
    }

    @When("^The guest sends the request to create the session$")
    public void theGuestSendsTheRequestToCreateTheSession() {
        idUrl = buildUrl.buildAuthSessionGuest();
        response = guestAuthController.createGuestToken(idUrl);
        guestSessionToken = JsonHelper.guestSessionToken(response);
        Serenity.setSessionVariable("success").to(guestSessionToken.isSuccess());
    }

    @When("^The guest sends the request to create the session with invalid data$")
    public void theGuestSendsTheRequestToCreateTheSessionWithInvalidData() {
        idUrl =  buildUrl.buildAuthSessionGuest();
        response = guestAuthController.createGuestTokenInvalid(idUrl);
        guestSessionToken = JsonHelper.guestSessionToken(response);
        Serenity.setSessionVariable("success").to(guestSessionToken.isSuccess());

    }

}
