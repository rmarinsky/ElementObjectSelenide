package gsmserver.Utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit.SoftAsserts;
import gsmserver.Utils.Report.CustomCollectors.CustomScreenShooter;
import gsmserver.Utils.Report.CustomTextReport.CustomJUnitReporter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public abstract class BaseTest {

    @Rule
    public SoftAsserts softAsserts = new SoftAsserts();

    @Rule
    public CustomScreenShooter makeScreenshotOnFailedTest = CustomScreenShooter.failedTests();

    @Rule
    public TestRule customReporter = new CustomJUnitReporter();

    @BeforeClass
    public static void beforeClass(){
        Configuration.baseUrl = "http://gsmserver.com";
    }

    @Before
    public void before(){
        open("");
        clearCookies();
    }

    private void clearCookies() {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        sleep(200);
    }

}
