package selenium.page;

import common.util.PerformActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class SauceDemoPage {
    PerformActions performActions;
    public SauceDemoPage(WebDriver driver) {
        performActions = new PerformActions();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#user-name")
    private WebElement enterUsername;

    @FindBy(css = "#password")
    private WebElement enterPassword;

    @FindBy(css = "#login-button")
    private WebElement loginButton;

    @FindBy(css = ".app_logo")
    private WebElement logo;

    @FindBy(css = ".inventory_item_label .inventory_item_name")
    private List<WebElement> litOfItem;

    @FindBy(xpath = "//button[text()='Add to cart']")
    private List<WebElement> addToCart;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cart;

    @FindBy(css = ".inventory_list")
    private WebElement itemContainer;

    @FindBy(css = "#cart_contents_container")
    private WebElement cartContainer;

    @FindBy(css = ".inventory_item_name")
    private WebElement itemNameInCart;

    public void LoginPage(String userName, String password) throws InterruptedException {
        enterUsername.sendKeys(userName);
        enterPassword.sendKeys(password);
        loginButton.click();
        performActions.waitForElement(logo);
        Assert.assertTrue(logo.isDisplayed());
    }

    public void addItemToCart(String itemName) {
        for (int i = 0; i <= litOfItem.size(); i++) {
            performActions.waitForElement(itemContainer);
            String name = litOfItem.get(i).getText();
            if (name.contains(itemName)) {
                performActions.jsClick(addToCart.get(i));
                break;
            }
        }
        performActions.jsClick(cart);
        performActions.waitForElement(cartContainer);
        Assert.assertEquals(itemNameInCart.getText(), itemName);
    }

}
