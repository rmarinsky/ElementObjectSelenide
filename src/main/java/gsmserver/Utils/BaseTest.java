package gsmserver.Utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.SoftAsserts;
import gsmserver.Utils.Report.CustomCollectors.CustomScreenShooter;
import gsmserver.Utils.Report.CustomTextReport.CustomJUnitReporter;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;

public abstract class BaseTest {

    @Rule
    public SoftAsserts softAsserts = new SoftAsserts();

    @Rule
    public CustomScreenShooter makeScreenshotOnFailedTest = CustomScreenShooter.failedTests();

    @Rule
    public TestRule customReporter = new CustomJUnitReporter();

    @BeforeClass public static void beforeClass(){
        Configuration.baseUrl = "http://gsmserver.com/";
    }

}
