package qa.automation.pages;

import org.openqa.selenium.WebDriver;

/**
 * A class that describes common properties and fields for all pages
 */
public abstract class BasePage {

    WebDriver driver;

    /**
     * Constructor that accepts for all pages
     * @param driver, action for actions on pages
     */
    public BasePage(WebDriver driver){
        this.driver = driver;
    }
}