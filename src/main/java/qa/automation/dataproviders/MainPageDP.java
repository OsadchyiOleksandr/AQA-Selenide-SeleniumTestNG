package qa.automation.dataproviders;

import org.testng.annotations.DataProvider;

/**
 * Class with DataProviders
 */
public class MainPageDP {

    /**
     * @return Testing Data for Test 4 as Object[][]
     */
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
}