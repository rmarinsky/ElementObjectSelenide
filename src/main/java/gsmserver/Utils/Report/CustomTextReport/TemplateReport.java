package gsmserver.Utils.Report.CustomTextReport;

import com.codeborne.selenide.logevents.EventsCollector;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.common.base.Joiner;

import java.util.Collections;
import java.util.logging.Logger;

import static gsmserver.Utils.Report.CustomCollectors.AllureReportsUtils.attachText;


class TemplateReport{
    private static final Logger log = Logger.getLogger(TemplateReport.class.getName());


    void start() {
        SelenideLogger.addListener("reporter", new EventsCollector());
    }

    void finish(String title) {
        EventsCollector logEventListener = SelenideLogger.removeListener("reporter");

        StringBuilder sb = new StringBuilder();
        sb.append("Report for ").append(title).append('\n');

        String delimiter = '+' + Joiner.on('+').join(line(60), line(60), line(8), line(6)) + "+\n";

        sb.append(delimiter);
        sb.append(String.format("|%-60s|%-60s|%-8s|%-6s|%n", "Element", "Subject", "Status", "ms."));
        sb.append(delimiter);

        logEventListener.events().stream().filter(e ->
                !(e.getDuration() == 0)).forEach(e ->
                sb.append(String.format("|%-60s|%-60s|%-8s|%-6s|%n", e.getElement(), e.getSubject(), e.getStatus(), e.getDuration())));
        sb.append(delimiter);
        log.info(sb.toString());
        attachText(sb.toString());
    }

    private String line(int count) {
        return Joiner.on("").join(Collections.nCopies(count, "-"));
    }


}
