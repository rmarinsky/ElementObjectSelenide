package gsmserver.Utils.Report.CustomCollectors;

import com.codeborne.selenide.Screenshots;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static gsmserver.Utils.Report.CustomCollectors.AllureReportsUtils.attachScreenshot;


public class CustomScreenShooter extends TestWatcher {

    private boolean captureSuccessfulTests;

    private CustomScreenShooter() {
    }

    public static CustomScreenShooter failedTests() {
        return new CustomScreenShooter();
    }

    public CustomScreenShooter succeededTests() {
        this.captureSuccessfulTests = true;
        return this;
    }

    @Override
    protected void starting(Description test) {
        Screenshots.startContext(test.getClassName(), test.getMethodName());
    }

    @Override
    protected void succeeded(Description test) {
        if (this.captureSuccessfulTests) {
            attachScreenshot();
        }
    }

    @Override
    protected void failed(Throwable e, Description description) {
        attachScreenshot();
    }

    @Override
    protected void finished(Description description) {
        Screenshots.finishContext();
    }

}
