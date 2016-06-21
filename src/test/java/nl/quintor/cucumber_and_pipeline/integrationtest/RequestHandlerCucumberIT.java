package nl.quintor.cucumber_and_pipeline.integrationtest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "classpath:nl/quintor/cucumber_and_pipeline/integrationtest" },
        plugin = { "pretty",
                "html:target/cucumberIT",
                "json:target/cucumberIT/cucumber.json" },
        glue = "nl.quintor.cucumber_and_pipeline.integrationtest.steps",
        strict = true)
public class RequestHandlerCucumberIT {
}

