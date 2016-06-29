package nl.quintor.cucumber_and_pipeline.integrationtest.steps;

import static nl.quintor.cucumber_and_pipeline.integrationtest.steps.WelcomePageSteps.creditStatusPage;

import org.hamcrest.Matchers;
import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import nl.quintor.cucumber_and_pipeline.integrationtest.pages.CreditStatusPage;

public class CreditStatusSteps {


    @Then("^the newest request has name \"([^\"]*)\" and has status \"([^\"]*)\"$")
    public void theNewestRequestIsTheEnteredRequestAndHasStatus(String name, String status) throws Throwable {
        final CreditStatusPage.CreditRequestRow latestRequest = creditStatusPage.getLatestRequest();
        Assert.assertThat(latestRequest.getNaam().trim(), Matchers.is(name));
        Assert.assertThat(latestRequest.getStatus().trim(), Matchers.is(status));
    }

    @And("^after some waiting the status become \"([^\"]*)\"$")
    public void afterSomeWaitingTheStatusBecome(String status) throws Throwable {
        creditStatusPage.waitStatusToBecome(status);
    }
}
