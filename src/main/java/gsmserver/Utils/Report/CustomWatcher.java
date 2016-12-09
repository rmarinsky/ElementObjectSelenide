package gsmserver.Utils.Report;

import com.codeborne.selenide.Screenshots;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;


public class CustomWatcher extends TestWatcher {

    private final HTMLTemplate report = new HTMLTemplate();

    private boolean onSucceededTest = false;
    private boolean saveVideo = false;

    public CustomWatcher saveVideo(){
        this.saveVideo = true;
        return this;
    }

    public CustomWatcher logSucceededTest() {
        this.onSucceededTest = true;
        return this;
    }

    @Override
    protected void starting(Description test) {
        Screenshots.startContext(test.getClassName(), test.getMethodName());
        report.start();
    }

    @Override
    protected void succeeded(Description description) {
        if (onSucceededTest) {
            AllureUtils.attachScreenshot();
            if(saveVideo) {
                AllureUtils.attachVideo();
            }
            report.finish();
        }
    }

    @Override
    protected void failed(Throwable e, Description description) {
        AllureUtils.attachScreenshot();
        if(saveVideo) {
            AllureUtils.attachVideo();
        }
        report.finish();
    }

    @Override
    protected void finished(Description description) {
        report.clean();
    }

}
