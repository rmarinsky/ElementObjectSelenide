package gsmserver.Utils.Report.CustomTextReport;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class CustomJUnitReporter extends TestWatcher{

    private TemplateReport report = new TemplateReport();

    @Override
    protected void starting(Description description) {
        this.report.start();
    }

    @Override
    protected void finished(Description description) {
        this.report.finish(description.getDisplayName());
    }

}
