package nl.quintor.cucumber_and_pipeline.integrationtest.steps;

import static nl.quintor.cucumber_and_pipeline.integrationtest.pages.PageObjectFactory.instantiatePageAndWaitUntilLoaded;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.quintor.cucumber_and_pipeline.integrationtest.pages.CreditStatusPage;
import nl.quintor.cucumber_and_pipeline.integrationtest.pages.NewCreditRequestPage;
import nl.quintor.cucumber_and_pipeline.integrationtest.pages.WelcomePage;

public class WelcomePageSteps {

    private WelcomePage welcomePage;
    private NewCreditRequestPage creditRequestPage;
    private CreditStatusPage creditStatusPage;

    @Given("^A user opens the welcome page$")
    public void aUserOpensTheWelcomePage() throws Throwable {
        welcomePage = instantiatePageAndWaitUntilLoaded(WelcomePage.class);
        assertThat(welcomePage, notNullValue());
    }

    @When("^the user clicks on the choice for new request$")
    public void theUserClicksOnTheChoiceForNewRequest() throws Throwable {
        creditRequestPage = welcomePage.clickNewRequest();
    }

    @When("^the user clicks on the choice for credit status$")
    public void theUserClicksOnTheChoiceForCreditStatus() throws Throwable {
        creditStatusPage = welcomePage.clickCreditStatus();
    }

    @Then("^the new request page is opened$")
    public void theNewRequestPageIsOpened() throws Throwable {
        assertThat(creditRequestPage, notNullValue());
    }

    @Then("^the credit status page is opened$")
    public void theCreditStatusPageIsOpened() throws Throwable {
        assertThat(creditStatusPage, notNullValue());
    }
}
