package gsmserver.Utils.Report.CustomCollectors;

import com.codeborne.selenide.Screenshots;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;


public class CustomScreenShooter extends TestWatcher {

    private CustomScreenShooter() {
    }

    public static CustomScreenShooter onFailedTests() {
        return new CustomScreenShooter();
    }

    @Override
    protected void starting(Description test) {
        Screenshots.startContext(test.getClassName(), test.getMethodName());
    }

    @Override
    protected void failed(Throwable e, Description description) {
        AllureReportUtil.attachScreenshot();
    }

    @Override
    protected void finished(Description description) {
        Screenshots.finishContext();
    }

}
