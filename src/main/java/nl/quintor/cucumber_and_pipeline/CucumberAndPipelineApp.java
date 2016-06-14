package nl.quintor.cucumber_and_pipeline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@ComponentScan(basePackageClasses = CucumberAndPipelineApp.class)
@ImportResource("classpath:application-*.xml")
@EnableJpaRepositories
public class CucumberAndPipelineApp {

    private static Class cucumberAndPipelineApp = CucumberAndPipelineApp.class;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(cucumberAndPipelineApp, args);
    }
}
