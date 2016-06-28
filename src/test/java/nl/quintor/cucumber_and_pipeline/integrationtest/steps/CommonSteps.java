package nl.quintor.cucumber_and_pipeline.integrationtest.steps;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import nl.quintor.cucumber_and_pipeline.integrationtest.context.TestContext;

public class CommonSteps {

    @Before
    public void setup() {
        final TestContext testContext = TestContext.getInstance();
        testContext.start();
        testContext.getWebDriver().get(getApplicationUrl());
    }
    
    private String getApplicationUrl() {
        return System.getProperty("target.url", "http://localhost:8082");
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        final TestContext testContext = TestContext.getInstance();
        if (scenario.isFailed()) {
            try {
                final WebDriver webDriver = testContext.getWebDriver();
                scenario.write("Page URL is " + webDriver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (Throwable somePlatformsDontSupportScreenshotsOrBrowserHasDied) {
                somePlatformsDontSupportScreenshotsOrBrowserHasDied.printStackTrace(System.err);
            }
        }
        testContext.finish();
    }
}