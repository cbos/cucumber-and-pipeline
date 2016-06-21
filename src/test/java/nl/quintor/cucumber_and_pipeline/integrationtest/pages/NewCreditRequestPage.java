package nl.quintor.cucumber_and_pipeline.integrationtest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import nl.quintor.cucumber_and_pipeline.integrationtest.context.TestContext;

public class NewCreditRequestPage implements PageObject {

    @FindBy(name = "creditRequestForm")
    private WebElement creditRequestForm;

    private TestContext testContext;
    
    @Override
    public ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(creditRequestForm);
    }

    @Override
    public void injectTestContext(TestContext testContext) {
        this.testContext = testContext;
    }
}
