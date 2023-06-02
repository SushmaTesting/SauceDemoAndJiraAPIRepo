package selenium;

import common.util.TestUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.WebDriverProvider;
import selenium.page.SauceDemoPage;

import java.net.MalformedURLException;

public class SauceDemoTest extends WebDriverProvider {
    SauceDemoPage eCart;
    String sheetName = "SauceDemo";


    @BeforeMethod
    public void setUp() throws MalformedURLException {
        initialization();
        eCart = new SauceDemoPage(driver);
    }

    @DataProvider
    public Object[][] readTestData() {
        Object data[][] = TestUtils.getTestData(sheetName);
        return data;
    }

    @Test(priority = 1, dataProvider = "readTestData")
    public void loginSauceDemoPage(String UserName, String Password, String itemName) throws InterruptedException {
        eCart.LoginPage(UserName, Password);
        eCart.addItemToCart(itemName);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
