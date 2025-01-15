package qa.automation.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

/**
 * Abstract class with basic activities for pages
 */
public abstract class BasePageHelper {

    public WebDriver driver;
    public Actions action;

    /**
     * Сonstructor that accepts for all pages
     * @param driver, action for actions on pages
     */
    public BasePageHelper(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(this.driver);
    }

    /**
     * @param driver - control entity
     * @param el - xpath of element for search
     * @return Web element
     */
    public WebElement findElement(WebDriver driver, By el){
        return driver.findElement(el);
    }

    /**
     * @param driver - control entity
     * @param el - xpath of element for search
     * @return Web elements by xpath
     */
    public List<WebElement> findElements(WebDriver driver, By el){
        return driver.findElements(el);
    }

    /**
     *
     * @param el - element to input
     * @param text - text which will be inserted in field
     */

    public void sendToInput(WebElement el, String text){
        action.sendKeys(el, text).perform();
    }

    /**
     * @param el - element which will be clicked
     */
    public void clickOnWebElement(WebElement el){
        action.click(el).perform();
    }

    /**
     * @param el - to this element will be moved cursor
     */
    public void moveToWebElement(WebElement el){
        action.moveToElement(el).perform();
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
     * @param el - this element will be checked for visibility
     * @return ExpectedCondition<Boolean> - as result
     */
    public ExpectedCondition<Boolean> isVisibleElement(final WebElement el) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return el.isDisplayed();
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return false;
                }
            }
            @Override
            public String toString() {
                return "element to be visible: " + el;
            }
        };
    }
}