package nl.quintor.cucumber_and_pipeline.domain;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "classpath:nl/quintor/cucumber_and_pipeline/domain" },
        plugin = { "pretty",
                "html:target/cucumber",
                "json:target/cucumber/cucumber.json" },
        glue = "nl.quintor.cucumber_and_pipeline.domain.steps",
        strict = true)
public class RequestHandlerCucumberTest {
}

