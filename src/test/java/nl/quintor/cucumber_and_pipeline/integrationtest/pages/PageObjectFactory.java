package nl.quintor.cucumber_and_pipeline.integrationtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import nl.quintor.cucumber_and_pipeline.integrationtest.context.TestContext;


public class PageObjectFactory {

    public static <T extends PageObject> T instantiatePageAndWaitUntilLoaded(Class<T> uiPart) {
        T page = instantiatePage(uiPart);
        PageFactory.initElements(new AjaxElementLocatorFactory(TestContext.getInstance().getWebDriver(), 10), page);
        waitFor(page.getPageLoadCondition());
        return page;
    }

    public static <V> void waitFor(Function<? super WebDriver, V> condition) {
        waitFor(TestContext.getInstance().getWebDriver(), 20, condition);
    }

    private static <T extends PageObject> T instantiatePage(Class<T> pageClassToProxy) {
        try {
            T uiPart = pageClassToProxy.newInstance();
            uiPart.injectTestContext(TestContext.getInstance());
            return uiPart;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <V> void waitFor(WebDriver driver, long timeoutInSeconds, Function<? super WebDriver, V> condition) {
        new WebDriverWait(driver, timeoutInSeconds).until(condition);
    }
}
