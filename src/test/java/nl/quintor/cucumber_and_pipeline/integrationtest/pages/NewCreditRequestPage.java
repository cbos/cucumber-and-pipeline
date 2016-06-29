package nl.quintor.cucumber_and_pipeline.integrationtest.pages;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

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

    public void enterNewRequest(Map<String, String> dataMap) {

        for (String fieldName : dataMap.keySet()) {
            creditRequestForm.findElement(By.id(fieldName.toLowerCase())).sendKeys(dataMap.get(fieldName));
        }

        creditRequestForm.findElement(By.id("submitButton")).click();
    }

    public Collection<String> getAlerts() {
        final List<WebElement> alerts = testContext.getWebDriver().findElements(By.className("alert"));
        return Collections2.transform(alerts, new Function<WebElement, String>() {
            @Override
            public String apply(WebElement webElement) {
                return webElement.findElement(By.tagName("div")).getText();
            }
        });
    }

    public CreditStatusPage clickCreditStatus() {
        testContext.getWebDriver().findElement(By.id("creditStatusMenu")).click();
        return PageObjectFactory.instantiatePageAndWaitUntilLoaded(CreditStatusPage.class);
    }
}
