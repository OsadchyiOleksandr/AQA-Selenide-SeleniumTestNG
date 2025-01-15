import static com.codeborne.selenide.Selenide.open;

public class SiteToWork {

    String siteToWork = "https://procraft.ua/ua";

    SiteToWork(){
        open(siteToWork);
    }

    void goTo(String site){
        open(site);
    }
}
