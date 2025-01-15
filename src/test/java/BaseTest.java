import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import qa.automation.utils.PropertiesFactory;
import qa.automation.utils.WebDriverFactory;

public class BaseTest {

    WebDriver driver;

    @BeforeSuite
    public void setUpBrowser(){
        this.driver = WebDriverFactory.getDriver(PropertiesFactory.getBrowserProperty());
    }

    @AfterMethod
    private void captureScreenshot() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        InputStream inStream = new ByteArrayInputStream(screenshot);
        Allure.addAttachment("Скриншот", "image/png", inStream, "png");
    }

    @AfterSuite (alwaysRun = true)
    private void closeWindow(){
        WebDriverFactory.closeBrowser(driver);
    }
}