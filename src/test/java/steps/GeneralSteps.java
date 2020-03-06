package steps;

import controllers.AuthenticationController;
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
        System.out.println("JAVA_HOME:-" + System.getenv("API_KEY"));
        int codeResponse = Serenity.sessionVariableCalled("status");
        Assert.assertThat(String.format("Error: The status code is not %s", statusCode),
               codeResponse, Matchers.equalTo(Integer.parseInt(statusCode)));
    }
}
