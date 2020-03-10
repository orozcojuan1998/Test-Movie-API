package steps;

import controllers.AuthenticationController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import net.serenitybdd.core.Serenity;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;


public class GeneralSteps  {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    private AuthenticationController authenticationController = new AuthenticationController();


    private void writeInfoOnLog(String message,String value) {
        logger.trace(message + " :" + value);

    }

    private void writeWarnOnLog(String message) {
        logger.warn(message);
    }

    @Then("^The service responds with a status code \"([^\"]*)\"$")
    public void theServiceRespondsWithAStatusCode(String statusCode) {
        writeWarnOnLog("Validating status code of the response");
        int codeResponse = Serenity.sessionVariableCalled("status");
        Assert.assertThat(String.format("Error: The status code is not %s", statusCode),
               codeResponse, Matchers.equalTo(Integer.parseInt(statusCode)));
    }

    @And("^The response contains the field success equals to \"([^\"]*)\"$")
    public void theResponseContainsTheFieldSuccessEqualsTo(String success) {
        writeWarnOnLog("Validating success of given operation in the body of the response");
        Boolean key = Serenity.sessionVariableCalled("success");
        Assert.assertThat("Error: The request token is unsuccessful",
                key,  Matchers.equalTo(Boolean.valueOf(success)));
    }

    @Then("^The response status message is \"([^\"]*)\"$")
    public void theResponseStatusMessageIs(String message) throws Throwable {
        writeWarnOnLog("Validating status message of the given response");
        Assert.assertThat("Error: The request could not be completed",
                Serenity.sessionVariableCalled("status_message") ,  Matchers.equalTo(message));
    }

}
