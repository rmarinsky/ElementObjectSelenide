package gsmserver.Utils;

import com.automation.remarks.junit.VideoRule;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import gsmserver.Pages.HomePage;
import gsmserver.Utils.Report.CustomWatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.sleep;
import static gsmserver.Utils.DefaultData.defaultBaseUrl;

public abstract class BaseTest {

    protected final String defaultTestValue = "TEST";

    /*@Rule
    public SoftAsserts softAsserts = new SoftAsserts();*/

    @Rule
    public VideoRule videoRule = new VideoRule();

    @Rule
    public CustomWatcher watcher = new CustomWatcher().onFailedTest(true).onSucceededTest(false);

    @BeforeClass
    public static void baseBeforeClass(){
        Configuration.baseUrl = defaultBaseUrl;
    }

    @Before
    public void beforeBase(){
        clearCookies();
        if(!(WebDriverRunner.getWebDriver().getCurrentUrl().equalsIgnoreCase(baseUrl + "/")))
            HomePage.openHomePage();
    }
    @After
    public void baseAfter(){
        clearCookies();
    }

    protected static void clearCookies() {
        if(!(WebDriverRunner.getWebDriver().getCurrentUrl().equalsIgnoreCase("https://temp-mail.ru/"))) {
            WebDriverRunner.getWebDriver().manage().deleteAllCookies();
            WebDriverRunner.getWebDriver().manage().deleteAllCookies();
            sleep(200);
        }
    }

}
