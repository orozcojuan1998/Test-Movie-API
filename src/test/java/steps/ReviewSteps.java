package steps;

import controllers.ReviewController;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import entities.ResponseBody;
import entities.Review;
import helpers.DirectorUrl;
import helpers.JsonHelper;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class ReviewSteps {

    private Review review;
    private ResponseBody responseBody;
    private Response response;
    private URL idUrl;
    private String idReview;
    private DirectorUrl buildUrl = new DirectorUrl();
    private ReviewController reviewController = new ReviewController();

    @Given("^The review exist with its data$")
    public void theReviewExistWithItsData(DataTable reviewData) {
        review = new Review();
        List<Map<String, String>> data = reviewData.asMaps(String.class, String.class);
        idReview = data.get(0).get("id");

    }

    @When("^The user send a request to get the review details$")
    public void theUserSendARequestToGetTheReviewDetails() {
        idUrl = buildUrl.buildReview(idReview);
        response = reviewController.getReview(idUrl);
        review = JsonHelper.responseToReview(response);
        Serenity.setSessionVariable("status_message").to(String.valueOf(response.getStatusCode()));

    }

    @And("^The response content of the review is not empty$")
    public void theResponseContentOfTheReviewIsNotEmpty() {
        Assert.assertThat(("Error: The review does not exits"),
                review.getContent().length(), Matchers.greaterThan(0));
    }
}
