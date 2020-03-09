package steps;

import controllers.AuthenticationController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import net.serenitybdd.core.Serenity;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralSteps  {
    private Logger logger = Logger.getLogger("GetTrelloSteps.class");

    private AuthenticationController authenticationController = new AuthenticationController();


    private void writeOnLog(String message) {
        logger.log(Level.INFO, "\n" + message);
    }

    @Then("^The service responds with a status code \"([^\"]*)\"$")
    public void theServiceRespondsWithAStatusCode(String statusCode) {
        int codeResponse = Serenity.sessionVariableCalled("status");
        Assert.assertThat(String.format("Error: The status code is not %s", statusCode),
               codeResponse, Matchers.equalTo(Integer.parseInt(statusCode)));
    }

    @And("^The response contains the field success equals to \"([^\"]*)\"$")
    public void theResponseContainsTheFieldSuccessEqualsTo(String success) {
        Boolean key = Serenity.sessionVariableCalled("success");
        Assert.assertThat("Error: The request token is unsuccessful",
                key,  Matchers.equalTo(Boolean.valueOf(success)));
    }

    @Then("^The response status message is \"([^\"]*)\"$")
    public void theResponseStatusMessageIs(String message) throws Throwable {
        Assert.assertThat("Error: The request could not be completed",
                Serenity.sessionVariableCalled("status_message") ,  Matchers.equalTo(message));
    }
}
