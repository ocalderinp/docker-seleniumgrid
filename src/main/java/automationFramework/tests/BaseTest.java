package automationFramework.tests;

import automationFramework.utils.GetProperties;
import automationFramework.utils.datatypes.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.net.URL;

import static automationFramework.utils.Utils.applyDefaultIfMissing;

public class BaseTest {

    protected static WebDriver driver;
    private static String environment = applyDefaultIfMissing(System.getProperty("environment"), "QA");
    protected static GetProperties properties = new GetProperties(environment);
    private static String browser = properties.getString("BROWSER").toUpperCase();
    private static String hubUrl = applyDefaultIfMissing(System.getProperty("hub"), "http://localhost:4444");

    @BeforeMethod
    public void setUp(Method method) throws Exception {
        BrowserType browserType = BrowserType.valueOf(browser.toUpperCase());
        DesiredCapabilities capabilities;
        switch (browserType) {
            case FIREFOX:
                capabilities = DesiredCapabilities.firefox();
                break;
            case CHROME:
                capabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                break;
            case IE:
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability("requireWindowFocus", false);
                capabilities.setCapability("enablePersistentHover", false);
                capabilities.setCapability("ignoreProtectedModeSettings", true);
                capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                break;
            default:
                capabilities = DesiredCapabilities.firefox();
        }
        try {
            System.out.println("****HUB_URL: " + hubUrl + "/wd/hub");
            driver = new RemoteWebDriver(new URL(hubUrl + "/wd/hub"), capabilities);
            driver.manage().window().maximize();
            navigateToHome();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void navigateToHome() {
        String BASE_URL = properties.getString("BASE_URL");
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        try {
            driver.quit();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
