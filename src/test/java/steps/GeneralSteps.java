package steps;

import controllers.AuthenticationController;
import cucumber.api.java.en.Then;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralSteps  {
    private Logger logger = Logger.getLogger("GetTrelloSteps.class");

    private AuthenticationController authenticationController = AuthenticationController.GetAuthenticationControllerInstance();


    private void writeOnLog(String message) {
        logger.log(Level.INFO, "\n" + message);
    }

    @Then("^The service responds with a status code \"([^\"]*)\"$")
    public void theServiceRespondsWithAStatusCode(String statusCode) {
        Assert.assertThat(String.format("Error: The status code is not %s", statusCode),
                authenticationController.getResponse().statusCode(), Matchers.equalTo(Integer.parseInt(statusCode)));
    }
}
