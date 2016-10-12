package gsmserver.Utils;

import com.automation.remarks.junit.VideoRule;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import gsmserver.Pages.HomePage;
import gsmserver.Utils.Report.CustomCollectors.AllureReportUtil;
import gsmserver.Utils.Report.CustomTextReport.HTMLReport;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

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
    public TestWatcher makeScreenshotOnFailedTest = new TestWatcher() {
        @Override
        protected void starting(Description test) {
            Screenshots.startContext(test.getClassName(), test.getMethodName());
        }

        @Override
        protected void failed(Throwable e, Description description) {
            new AllureReportUtil().attachScreenshot();
            //new AllureReportUtil().attachVideo();
        }

        @Override
        protected void finished(Description description) {
            Screenshots.finishContext();
        }

    };

    @Rule
    public TestRule customReporter = new HTMLReport();


    @BeforeClass
    public static void baseBeforeClass(){
        Configuration.baseUrl = defaultBaseUrl;
    }

    @Before
    public void baseBefore(){
        //VideoRecorder.conf().getRecorderType()
        this.clearCookies();
        if(!(WebDriverRunner.getWebDriver().getCurrentUrl().equalsIgnoreCase(baseUrl + "/")))
            HomePage.openHomePage();
    }

    protected void clearCookies() {
        if(!(WebDriverRunner.getWebDriver().getCurrentUrl().equalsIgnoreCase("https://temp-mail.ru/"))) {
            WebDriverRunner.getWebDriver().manage().deleteAllCookies();
            WebDriverRunner.getWebDriver().manage().deleteAllCookies();
            sleep(200);
        }
    }

}
