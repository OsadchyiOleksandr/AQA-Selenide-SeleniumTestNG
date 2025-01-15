package qa.automation.pages;

import org.openqa.selenium.WebDriver;
import qa.automation.utils.Constants;

/**
 * A class that describes common properties and fields for Elektroinstrument Page
 */
public class ElektroinstrumentPage extends BasePage{

    private String pageURL = Constants.MAIN_PAGE_URL + "/elektroinstrument/";
    WebDriver driver;

    /**
     * Constructor that accepts for all pages
     * @param driver, action for actions on pages
     */
    public ElektroinstrumentPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    /**
     * @return URL for this page
     */
    public String getURL(){
        return pageURL;
    }
}