package nl.quintor.cucumber_and_pipeline.integrationtest.pages;

import static nl.quintor.cucumber_and_pipeline.integrationtest.pages.PageObjectFactory.waitFor;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeMoreThan;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import nl.quintor.cucumber_and_pipeline.integrationtest.context.TestContext;

public class CreditStatusPage implements PageObject {

    @FindBy(id = "creditStatusTable")
    private WebElement creditStatusTable;

    private TestContext testContext;
    
    @Override
    public ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(creditStatusTable);
    }

    @Override
    public void injectTestContext(TestContext testContext) {
        this.testContext = testContext;
    }

    public CreditRequestRow getLatestRequest() {
        final By rowsLocator = By.className("creditRequestRow");
        waitFor(numberOfElementsToBeMoreThan(rowsLocator, 0));
        final List<WebElement> creditRequestRows = creditStatusTable.findElements(rowsLocator);
        return new CreditRequestRow(creditRequestRows.get(0));
    }

    public void waitStatusToBecome(String status) {
        waitFor(ExpectedConditions.textToBe(By.xpath("//table[@id=\"creditStatusTable\"]/tbody/tr[1]/td[contains(@class, 'status')]"), " " + status));
    }

    public static class CreditRequestRow {

        private final WebElement row;

        private CreditRequestRow(WebElement row) {
            this.row = row;
        }
        
        public String getNaam(){
            return row.findElement(By.className("naam")).getText();
        }

        public String getStatus(){
            return row.findElement(By.className("status")).getText();
        }
    }
}
