package tests;

import config.ConfigManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"platformName"})
    public void setUp(@Optional("windows_chrome") String platformName) throws Exception {
        Map<String, MutableCapabilities> platforms = Map.of(
         "windows_chrome", new MutableCapabilities(Map.of("os", "windows","osVersion","10","browserName","Chrome","browserVersion","latest")),
                "mac_safari", new MutableCapabilities(Map.of(
                        "os", "OS X",
                        "osVersion", "Monterey",
                        "browserName", "Safari",
                        "browserVersion", "latest"
                )),
                "iphone13", new MutableCapabilities(Map.of(
                        "deviceName", "iPhone 13",
                        "osVersion", "15",
                        "browserName", "Safari",
                        "deviceOrientation", "portrait"
                ))

        );
        MutableCapabilities caps = platforms.get(platformName);
        // Common capabilities
        caps.setCapability("projectName", "SauceDemo-Automation");
        caps.setCapability("buildName", "SauceDemo-Build-1");
        caps.setCapability("sessionName", "SauceDemo Tests");
        caps.setCapability("browserstack.local", false); // true for internal apps
        caps.setCapability("browserstack.debug", true);
        caps.setCapability("browserstack.console", "info");
        caps.setCapability("browserstack.networkLogs", true);
        caps.setCapability("acceptInsecureCerts", true);
        // Read BrowserStack credentials from environment variables
        String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
        String ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
        driver = new RemoteWebDriver(
                new URL("https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub"),
                caps
        );

        //driver = new LocalTestScript().getDriver();
        // Navigate to app URL from config

        driver.get(ConfigManager.get("app.url"));
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            Reporter.log("Driver quit successfully", true);
        }
    }

}
