package gsmserver.Utils.Report.CustomCollectors;

import com.codeborne.selenide.Screenshots;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.IOException;

import static gsmserver.Utils.Report.CustomCollectors.AllureReportsUtils.attachScreenshot;


public class CustomScreenShooter extends TestWatcher {

    private CustomScreenShooter() {
    }

    public static CustomScreenShooter failedTests() {
        return new CustomScreenShooter();
    }

    @Override
    protected void starting(Description test) {
        Screenshots.startContext(test.getClassName(), test.getMethodName());
    }

    @Override
    protected void failed(Throwable e, Description description) {
        try {
            attachScreenshot();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    protected void finished(Description description) {
        Screenshots.finishContext();
    }

}
