package nl.quintor.cucumber_and_pipeline.integrationtest.steps;

import static nl.quintor.cucumber_and_pipeline.integrationtest.steps.WelcomePageSteps.creditRequestPage;
import static nl.quintor.cucumber_and_pipeline.integrationtest.steps.WelcomePageSteps.creditStatusPage;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Map;

import org.hamcrest.Matchers;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NewCreditRequestSteps {


    @When("^the user enters a new request with:$")
    public void theUserEntersANewRequestWith(DataTable dataTable) throws Throwable {
        Map<String, String> dataMap = dataTable.asMaps(String.class, String.class).get(0);
        
        creditRequestPage.enterNewRequest(dataMap);
    }

    @Then("^the message \"([^\"]*)\" is shown$")
    public void theMessageIsShown(String message) throws Throwable {
        final Collection<String> alerts = creditRequestPage.getAlerts();
        
        assertThat(alerts, hasSize(1));
        assertThat(alerts, Matchers.hasItem(message));
    }

    @And("^the user opens the status page in the menu$")
    public void theUserOpensTheStatusPageInTheMenu() throws Throwable {
        creditStatusPage = creditRequestPage.clickCreditStatus();
    }
}
