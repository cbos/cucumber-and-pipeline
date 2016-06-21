package nl.quintor.cucumber_and_pipeline.integrationtest.context;

import org.openqa.selenium.WebDriver;

public class TestContext {
    
    private static ThreadLocal<TestContext> testContextThreadLocal = new ThreadLocal<TestContext>() {
        @Override
        protected TestContext initialValue() {
            return new TestContext();
        }
    };

    private WebDriver webDriver;
    
    private TestContext(){
    }
    
    public static TestContext getInstance(){
        return testContextThreadLocal.get();
    }

    public void start() {
        webDriver = DriverWrapper.getDriver();
    }
    
    public void finish() {
        DriverWrapper.returnDriver(webDriver);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
