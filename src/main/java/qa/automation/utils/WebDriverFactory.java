package qa.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;

/**
 * The class implements the browser factory pattern. The class implements the browser factory pattern to control the browser
 */

public class WebDriverFactory {

    /**
     * Initialize browsers for projects
     */
    private static HashMap<String, WebDriver> drivers = new HashMap<String, WebDriver>(){{
        put("chrome", new ChromeDriver());
    }};

    /**
     *
     * @param browser - chosen browser for tests
     * @return nedded WebDriver with basic params
     */
    public static WebDriver getDriver(String browser){
        WebDriver driver = drivers.get(browser);
        setDriverTimeouts(driver);
        setFullScreen(driver);
        return drivers.get(browser);
    }

    /**
     * Custom Waiter
     * @param driver - for control
     * @param condition - wait nedded condition
     * @param timeoutInSeconds - how long need wait if condition is not true
     */
    public static void waitForCondition(WebDriver driver, ExpectedCondition<Boolean> condition, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(condition);
    }

    /**
     * Set Basic Waiters
     * @param driver - for control
     */
    private static void setDriverTimeouts(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));
    }

    /**
     * Set full screen for browser
     * @param driver - for control
     */
    public static void setFullScreen(WebDriver driver){
        driver.manage().window().fullscreen();
    }

    /**
     * Set full screen for browser
     * @param driver - for control
     */
    public static void closeBrowser(WebDriver driver){
        driver.close();
    }
}