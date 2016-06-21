package nl.quintor.cucumber_and_pipeline.integrationtest.context;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;

/**
 * DriverWrapper is a class which instantiates the WebDriver and caches the instance so it can be reused
 * getDriver() can be used to get an instance, returnDriver has to be used to return it to reuse it.
 */
class DriverWrapper {
    private static WebDriver driverInstance = null;

    private static Logger LOGGER = Logger.getLogger(DriverWrapper.class);

    static WebDriver getDriver() {
        if (null == driverInstance) {
            driverInstance = getFirefoxWebDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
        }
        return driverInstance;
    }

    private static WebDriver getFirefoxWebDriver() {
        WebDriver driverInstance = new FirefoxDriver();
        driverInstance.manage().window().maximize();
        return driverInstance;
    }

    private static WebDriver getPhantomJSWebDriver() {
        String PHANTOM_JS_PATH = "your custom path\\phantomjs.exe";
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.phantomjs();

        desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PHANTOM_JS_PATH);
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[]{"--web-security=no", "--ignore-ssl-errors=yes"});
        desiredCapabilities.setCapability("takesScreenshot", true);
        return new PhantomJSDriver(desiredCapabilities);
    }

    static void returnDriver(WebDriver driver) {
        driverInstance = driver;
    }

    private static void releaseDriver() {
        if (driverInstance != null) {
            releaseDriver(driverInstance);
            driverInstance = null;
        }
    }

    private static void releaseDriver(WebDriver driver) {
        try {
            LOGGER.info("Quit webdriver");
            driver.quit();
            LOGGER.info("Quit webdriver done");
        } catch (UnreachableBrowserException e) {
            LOGGER.info("Cannot close browser: unreachable browser");
        }
    }

    private static class BrowserCleanup implements Runnable {
        @Override
        public void run() {
            LOGGER.info("Closing the browser");
            releaseDriver();
        }
    }
}
