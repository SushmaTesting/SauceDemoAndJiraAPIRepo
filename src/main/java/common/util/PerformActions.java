package common.util;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.base.WebDriverProvider;

public class PerformActions extends WebDriverProvider {
    public PerformActions() {
        super();
    }

    private static final long WAIT_IN_SECS = 30;

    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_IN_SECS);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_IN_SECS);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor excecutor = (JavascriptExecutor) driver;
        excecutor.executeScript("arguments[0].click();", element);
    }

    public void actionClick(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }
}
