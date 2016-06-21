package nl.quintor.cucumber_and_pipeline.integrationtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import nl.quintor.cucumber_and_pipeline.integrationtest.context.TestContext;

public class WelcomePage implements PageObject {

    @FindBy(className = "mainpage-choice")
    private WebElement mainPageChoice;

    private TestContext testContext;

    @Override
    public ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(mainPageChoice);
    }

    @Override
    public void injectTestContext(TestContext testContext) {
        this.testContext = testContext;
    }

    public NewCreditRequestPage clickNewRequest() {
        mainPageChoice.findElement(By.id("newCreditRequest")).click();
        return PageObjectFactory.instantiatePageAndWaitUntilLoaded(NewCreditRequestPage.class);
    }

    public CreditStatusPage clickCreditStatus() {
        mainPageChoice.findElement(By.id("creditStatus")).click();
        return PageObjectFactory.instantiatePageAndWaitUntilLoaded(CreditStatusPage.class);
    }
}
