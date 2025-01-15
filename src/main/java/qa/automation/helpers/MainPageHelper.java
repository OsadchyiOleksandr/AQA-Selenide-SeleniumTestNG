package qa.automation.helpers;

import org.openqa.selenium.WebDriver;

/**
 * Class with specific activities for MainPage
 */
public class MainPageHelper extends BasePageHelper {

    WebDriver driver;

    /**
     * Constructor that accepts for Main Page
     * @param driver, action for actions on page
     */
    public MainPageHelper(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
}