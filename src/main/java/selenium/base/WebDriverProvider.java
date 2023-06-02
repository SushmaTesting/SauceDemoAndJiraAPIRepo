package selenium.base;


import com.relevantcodes.extentreports.LogStatus;
import common.extentReportListener.ExtentReporterNG;
import common.util.PerformActions;
import common.util.TestUtils;
import common.util.WebEventListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.log4testng.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebDriverProvider {
    public static WebDriver driver;
    public static Properties properties;
    public static EventFiringWebDriver e_driver;
    public static WebEventListener eventListener;

    public WebDriverProvider() {
        try {
            properties = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "/src/main/resources" + "/config.properties");
            properties.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialization() throws MalformedURLException {
        String browserName = null != System.getProperty("browser") ? System.getProperty("browser") : "chrome";
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(new FirefoxOptions(capabilities.merge(getOptions())));
        } else if (browserName.equalsIgnoreCase("browserStack")) {
            DesiredCapabilities caps = new DesiredCapabilities();
            String USERNAME = (System.getenv("BROWSERSTACK_USERNAME") != null) ? System.getenv("BROWSERSTACK_USERNAME") : "sushmamb_gHKhkV";
            String AUTOMATE_KEY = (System.getenv("BROWSERSTACK_ACCESS_KEY") != null) ? System.getenv("BROWSERSTACK_ACCESS_KEY") : "Dy5xAEZz6bmVugYUySZB";
            String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
            caps.setCapability("os", "Windows");
            caps.setCapability("os_version", "10");
            caps.setCapability("browser", "Chrome");
            caps.setCapability("browser_version", "latest");
            driver = new RemoteWebDriver(new URL(URL), caps);
            driver.get(properties.getProperty("url"));
        }

        e_driver = new EventFiringWebDriver(driver);
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LODE_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    private Capabilities getOptions() {
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--accept_untrusted_certs");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        firefoxOptions.setAcceptInsecureCerts(true);
        return firefoxOptions;
    }

}
