package common.util;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.log4testng.Logger;
import selenium.base.WebDriverProvider;

import java.io.IOException;

public class WebEventListener extends WebDriverProvider implements WebDriverEventListener {
    Logger log = Logger.getLogger(WebEventListener.class);

    @Override
    public void beforeAlertAccept(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterAlertAccept(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        log.info("Before navigating to: '" + url + "'");
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        log.info("Navigated to:'" + url + "'");

    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        log.info("Navigating back to previous page");

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        log.info("Navigated back to previous page");

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        log.info("Navigated forward to next page");
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        log.info("Navigated forward to next page");

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        log.info("Trying to find Element By : " + by.toString());

    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        log.info("Found Element By : " + by.toString());

    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        log.info("Trying to click on: " + element.toString());

    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        log.info("Value of the:" + element.toString() + " before any changes made");

    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        log.info("Element value changed to: " + element.toString());

    }

    @Override
    public void beforeScript(String script, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterScript(String script, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onException(Throwable error, WebDriver driver) {
        System.out.println("Exception occured: " + error);
        try {
            TestUtils.takeScreenshotAtEndOfTest(driver);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {
        // TODO Auto-generated method stub

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        // TODO Auto-generated method stub

    }


}
