package gsmserver.Utils;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit.SoftAsserts;
import gsmserver.Utils.Report.CustomCollectors.CustomScreenShooter;
import gsmserver.Utils.Report.CustomTextReport.HTMLReport;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public abstract class BaseTest {

    @Rule
    public SoftAsserts softAsserts = new SoftAsserts();

    @Rule
    public CustomScreenShooter makeScreenshotOnFailedTest = CustomScreenShooter.onFailedTests();

    @Rule
    public TestRule customReporter = new HTMLReport();

    @Before
    public void before(){
        clearCookies();
        if(!(WebDriverRunner.getWebDriver().getCurrentUrl().equalsIgnoreCase(baseUrl)))
        open("");
    }

    private void clearCookies() {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        sleep(200);
    }

}
