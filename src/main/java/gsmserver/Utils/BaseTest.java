package gsmserver.Utils;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit.SoftAsserts;
import gsmserver.Pages.HomePage;
import gsmserver.Utils.Report.CustomCollectors.CustomScreenShooter;
import gsmserver.Utils.Report.CustomTextReport.HTMLReport;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.sleep;

public abstract class BaseTest {

    protected final String testValue = "TEST";

    @Rule
    public SoftAsserts softAsserts = new SoftAsserts();

    @Rule
    public CustomScreenShooter makeScreenshotOnFailedTest = CustomScreenShooter.onFailedTests();

    @Rule
    public TestRule customReporter = new HTMLReport();

    @Before
    public void before(){
        if(!(WebDriverRunner.getWebDriver().getCurrentUrl().equalsIgnoreCase("https://temp-mail.ru/")))
        clearCookies();
        if(!(WebDriverRunner.getWebDriver().getCurrentUrl().equalsIgnoreCase(baseUrl)))
            HomePage.openHomePage();
    }

    private void clearCookies() {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        sleep(200);
    }

}
