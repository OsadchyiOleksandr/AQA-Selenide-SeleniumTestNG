import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;

import org.testng.annotations.*;
import com.codeborne.selenide.Condition;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.logevents.SelenideLogger;

public class ProcraftTest {

    SiteToWork site = new SiteToWork();
    int testCouter = 0;

    @Attachment(type = "image/png")
    public byte[] screenshot() throws IOException{
        File screenshot = Screenshots.getLastScreenshot();
        return screenshot == null ? null : Files.toByteArray(screenshot);
    }

    @BeforeSuite
    static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        // or for fine-tuning:
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(false)
                .savePageSource(true)
        );
    }

    @BeforeTest
    public void beginLogs() throws IOException {
        String text = "\n\n------------------------------New Iteration------------------------------\n\n";
        FileWorks.writeToFile(text,"logs.txt");
    }

    @BeforeMethod
    public void returnToPage(){
        site.goTo(site.siteToWork);
    }

    @AfterMethod
    public void ToPage() throws IOException {
        testCouter++;
        String testNum = String.format("%s. - \n", testCouter);
        FileWorks.writeToFile(String.valueOf(testNum),"logs.txt");
        screenshot();
    }
    @AfterTest
    public void endLogs() throws IOException {
        String text = "\n------------------------------Results------------------------------\n" +
                "=================================================================";
        FileWorks.writeToFile(text,"logs.txt");
    }

    @Test(priority = 1, alwaysRun = true, testName = "siteAccessTest")
    @Description("Site Access Test")
    @Owner("Oleksandr")
    @Issue("PRO-1")
    public void siteAccessTest(){
        webdriver().shouldHave(url(site.siteToWork));
    }

    @Test(priority = 2, testName = "elInstrumentRedirectTest")
    @Description("Instrument Redirect Test")
    @Owner("Oleksandr")
    @Issue("PRO-2")
    public void elInstrumentRedirectTest(){
        $x("//p[text()='Електроінструмент']").click();
        webdriver().shouldHave(url("https://procraft.ua/ua/elektroinstrument/"));
    }

    @Test(priority = 3, testName = "searchTest")
    @Description("Search Test")
    @Owner("Oleksandr")
    @Issue("PRO-3")
    public void searchTest(){
        String textTemp = "qaswedffr";
        String xpathTemp = "//input[@name='search']";
        $x(xpathTemp)
                .shouldHave(Condition.attribute("placeholder", "Пошук"));
        $x("//input[@name='search' and @placeholder='Пошук']").click();
        $x("//input[@name='search' and @placeholder='Пошук']").val(textTemp).pressEnter();
        webdriver().shouldHave(url(String
                .format("https://procraft.ua/ua/index.php?route=product/search&search=%s", textTemp)));
    }

    @DataProvider(name = "For Catalog Test")
    public Object[][] forCatalogTest(){
        return new Object[][]{
                {"//a[@href='ua/benzointrumenty/' and @class='main-nav__link']",
                        "//a[@href='ua/benzointrumenty/generatory-dizelnye/' and @class='main-nav-dropdown__link']",
                        "ua/benzointrumenty/generatory-dizelnye/"},
                {"//a[@href='ua/benzointrumenty/' and @class='main-nav__link']",
                        "//a[@href='ua/benzointrumenty/kultivatory-benzinovye/' and @class='main-nav-dropdown__link']",
                        "ua/benzointrumenty/kultivatory-benzinovye/"},
                {"//a[@href='ua/elektroinstrument/' and @class='main-nav__link']",
                        "//a[@href='ua/elektroinstrument/shurupoverty/' and @class='main-nav-dropdown__link']",
                        "ua/elektroinstrument/shurupoverty/"}
        };
    }

    @Test(priority = 4, testName = "catalogTest", dataProvider = "For Catalog Test")
    @Description("Catalog Test")
    @Owner("Oleksandr")
    @Issue("PRO-4")
    public void catalogTest(String xpathCategory, String xpathItem, String tempItem){
        String xpathButton = "//button[text()='Каталог товарів']";
        $x(xpathButton).shouldBe(Condition.exist);
        $x(xpathButton).hover();
        $x(xpathCategory).shouldBe(Condition.exist);
        $x(xpathCategory).hover();
        $x(xpathItem).shouldBe(Condition.exist);
        $x(xpathItem).click();
        webdriver().shouldHave(url(String
                .format("https://procraft.ua/%s", tempItem)));
    }

    @Test(priority = 5, testName = "addToBucketTest")
    @Description("Add To Bucket Test")
    @Owner("Oleksandr")
    @Issue("PRO-5")
    public void addToBucketTest(){
        String xpathItem = "//h3[contains(text(), 'ХІТ')]/..//div[@class='owl-item'][1]";
        String xpathButton = xpathItem + "/..//button";
        String xpathItemName = "//h3[contains(text(), 'ХІТ')]/..//h4/a[1]";
        String itemName = $x(xpathItemName).getText();
        String xpathNameInBucket = "//div[@class=\"mfp-content\"]/..//div[@class='name-right']/a";
        String xpathCloseButton = "//span[@class='modal-close']";
        String xpathPopup = "//div[@class='mfp-content']";
        $x(xpathItem).shouldBe(Condition.exist);
        $x(xpathItem).hover();
        $x(xpathButton).shouldBe(Condition.exist);
        $x(xpathButton).click();
        $x(xpathNameInBucket).shouldHave(Condition.text(itemName));
        $x(xpathCloseButton).click();
        $x(xpathPopup).shouldNotBe(Condition.visible);
    }


    @Test(priority = 6, testName = "appersLoginTest")
    @Description("Appears Login Test")
    @Owner("Oleksandr")
    @Issue("PRO-6")
    public void appearsLoginTest(){
        String xpathItem = "//a[@href='#user-login']";
        String xpathLogin = "//div[@class='fancybox-stage']";
        String xpathCloseButton = "//*[@data-fancybox-close and @type='button']";
        $x(xpathItem).click();
        $x(xpathLogin).shouldBe(Condition.visible);
        $x(xpathCloseButton).click();
        $x(xpathLogin).shouldNotBe(Condition.visible);
    }
}
