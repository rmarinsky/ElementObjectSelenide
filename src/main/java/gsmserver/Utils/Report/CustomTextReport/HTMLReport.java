package gsmserver.Utils.Report.CustomTextReport;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class HTMLReport extends TestWatcher {

    private final HTMLTemplate report = new HTMLTemplate();

    private boolean onFailedTest = true;
    private boolean onSucceededTest = true;

    public HTMLReport onFailedTest(boolean onFailedTest) {
        this.onFailedTest = onFailedTest;
        return this;
    }

    public HTMLReport onSucceededTest(boolean onSucceededTest) {
        this.onSucceededTest = onSucceededTest;
        return this;
    }

    @Override
    protected void starting(Description description) {
        if (onFailedTest || onSucceededTest) {
            report.start();
        }
    }

    @Override
    protected void succeeded(Description description) {
        if (onSucceededTest) {
            report.finish();
        }
    }

    @Override
    protected void failed(Throwable e, Description description) {
        if (onFailedTest) {
            report.finish();
        }
    }

    @Override
    protected void finished(Description description) {
        report.clean();
    }

}
