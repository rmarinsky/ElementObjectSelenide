package gsmserver.Utils.Report;

import com.codeborne.selenide.Screenshots;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;


public class CustomWatcher extends TestWatcher {

    private final HTMLTemplate report = new HTMLTemplate();

    private boolean onFailedTest = true;
    private boolean onSucceededTest = true;
    private boolean saveVideo = true;


    public CustomWatcher onFailedTest(boolean onFailedTest) {
        this.onFailedTest = onFailedTest;
        return this;
    }

    public CustomWatcher saveVideo(boolean saveVideo){
        this.saveVideo = saveVideo;
        return this;
    }

    public CustomWatcher onSucceededTest(boolean onSucceededTest) {
        this.onSucceededTest = onSucceededTest;
        return this;
    }

    @Override
    protected void starting(Description test) {
        if (onFailedTest || onSucceededTest) {
            Screenshots.startContext(test.getClassName(), test.getMethodName());
            report.start();
        }
    }

    @Override
    protected void succeeded(Description description) {
        if (onSucceededTest) {
            report.finish();
            AllureReportUtil.attachScreenshot();
            if(saveVideo) {
                AllureReportUtil.attachVideo();
            }
        }
    }

    @Override
    protected void failed(Throwable e, Description description) {
        if (onFailedTest) {
            report.finish();
            AllureReportUtil.attachScreenshot();
            if(saveVideo) {
                AllureReportUtil.attachVideo();
            }
        }
    }

    @Override
    protected void finished(Description description) {
        report.clean();
    }

}
