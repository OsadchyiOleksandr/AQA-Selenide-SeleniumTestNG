import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import qa.automation.dataproviders.MainPageDP;
import qa.automation.pages.ElektroinstrumentPage;
import qa.automation.utils.WebDriverFactory;
import qa.automation.pages.MainPage;
import qa.automation.helpers.MainPageHelper;

public class ProcraftMainPageTest extends BaseTest{

    MainPageHelper helper;
    MainPage page;

    @BeforeSuite
    public void setUpObjects(){
        this.page = new MainPage(driver);
        this.helper = new MainPageHelper(driver);
    }

    @BeforeMethod
    public void returnToPage(){
        driver.get(page.getURL());
        WebDriverFactory.setFullScreen(driver);
    }

    @Test(priority = 1, alwaysRun = true, testName = "siteAccessTest")
    @Description("Site Access Test")
    @Owner("Oleksandr")
    @Issue("PRO-1")
    @Step("Step 1")
    public void siteMainTitleTest(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        assertThat((String) js.executeScript("return document.title;"), equalTo(page.PAGE_TITLE));
    }

    @Test(priority = 2, testName = "elInstrumentRedirectTest")
    @Description("Instrument Redirect Test")
    @Owner("Oleksandr")
    @Issue("PRO-2")
    @Step("Step 2")
    public void elInstrumentRedirectTest(){
        ElektroinstrumentPage expectedPage = new ElektroinstrumentPage(driver);
        WebElement element = driver.findElement(page.CATEGORY_ELECTOTOOL);
        helper.waitForCondition(driver, helper.isVisibleElement(element) , 10);
        helper.clickOnWebElement(element);
        assertThat(driver.getCurrentUrl(), is(equalTo(expectedPage.getURL())));
    }

    @Test(priority = 3, testName = "searchTest")
    @Description("Search Test")
    @Owner("Oleksandr")
    @Issue("PRO-3")
    public void searchTest(){
        String text = "qaswedffr";
        WebElement searchInput = helper.findElement(driver, page.SEARCH_INPUT);
        assertThat(searchInput.getAttribute("placeholder"), equalTo("Пошук"));
        helper.clickOnWebElement(searchInput);
        helper.sendToInput(searchInput, text);
        helper.sendToInput(searchInput, "\n");
        assertThat(driver.getCurrentUrl(), equalTo(String.format("https://procraft.ua/ua/index.php?route=product/search&search=%s", text)));
    }

    @Test(priority = 4, testName = "catalogTest", dataProvider = "For Catalog Test", dataProviderClass = MainPageDP.class)
    @Description("Catalog Test")
    @Owner("Oleksandr")
    @Issue("PRO-4")
    public void catalogTest(String xpathCategory, String xpathItem, String tempItem){
        WebElement catalogButton = driver.findElement(page.CATALOG_OF_PRODUCT);
        helper.waitForCondition(driver, helper.isVisibleElement(catalogButton) , 10);
        helper.moveToWebElement(catalogButton);
        helper.clickOnWebElement(catalogButton);
        WebElement categoryElement = driver.findElement(By.xpath(xpathCategory));
        helper.waitForCondition(driver, helper.isVisibleElement(categoryElement) , 10);
        helper.moveToWebElement(categoryElement);
        WebElement itemElement = driver.findElement(By.xpath(xpathItem));
        helper.waitForCondition(driver, helper.isVisibleElement(itemElement) , 10);
        helper.moveToWebElement(itemElement);
        helper.clickOnWebElement(itemElement);
        assertThat(driver.getCurrentUrl(), equalTo(String.format("https://procraft.ua/%s", tempItem)));
    }

    @Test(priority = 5, testName = "appearsLoginTest")
    @Description("Appears Login Test")
    @Owner("Oleksandr")
    @Issue("PRO-5")
    public void appearsLoginTest() {
        WebElement loginButton = helper.findElement(driver, page.LOGIN_BUTTON);
        helper.moveToWebElement(loginButton);
        helper.clickOnWebElement(loginButton);
        WebElement loginPOPUP = helper.findElement(driver, page.LOGIN_POPUP);
        assertThat(loginPOPUP.isDisplayed(), is(true));
        WebElement closeButton = helper.findElement(driver, page.LOGIN_POPUP_CLOSE_BUTTON);
        helper.moveToWebElement(closeButton);
        helper.clickOnWebElement(closeButton);
        List<WebElement> elements = helper.findElements(driver, page.LOGIN_POPUP);
        assertThat(elements.isEmpty(), is(false));
        assertThat(elements.get(0).isSelected(), is(false));
    }
}