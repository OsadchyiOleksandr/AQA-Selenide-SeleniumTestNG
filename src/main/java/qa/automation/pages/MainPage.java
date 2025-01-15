package qa.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import qa.automation.utils.Constants;

/**
 * A class that describes common properties and fields for MainPage Page
 */
public class MainPage extends BasePage{

    private String pageURL = Constants.MAIN_PAGE_URL;

    WebDriver driver;

    /**
     * Constructor that accepts for MainPage Page
     * @param driver, action for actions on MainPage Page
     */
    public MainPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    /**
     * @return URL for this page
     */
    public String getURL(){
        return pageURL;
    }

    /**
     * Params for search elements
     */
    public final String PAGE_TITLE = "Прокрафт — интернет магазин профессиональных инструментов " +
            "в розницу и оптом Харьков, Киев: Украина";
    public final By CATEGORY_ELECTOTOOL = By.xpath("//p[text()='Електроінструмент']");
    public final By SEARCH_INPUT = By.xpath("//input[@name='search']");
    public final By CATALOG_OF_PRODUCT = By.xpath("//button[text()='Каталог товарів']");
    public final By LOGIN_BUTTON = By.xpath("//a[@href='#user-login']");
    public final By LOGIN_POPUP = By.xpath("//div[@class='fancybox-stage']");
    public final By LOGIN_POPUP_CLOSE_BUTTON = By.xpath("//*[@data-fancybox-close and @type='button']");
}