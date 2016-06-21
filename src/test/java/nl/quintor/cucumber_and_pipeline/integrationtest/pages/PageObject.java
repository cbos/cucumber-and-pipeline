package nl.quintor.cucumber_and_pipeline.integrationtest.pages;

import org.openqa.selenium.support.ui.ExpectedCondition;

import nl.quintor.cucumber_and_pipeline.integrationtest.context.TestContext;

public interface PageObject {

    ExpectedCondition getPageLoadCondition();

    void injectTestContext(TestContext testContext);
}
