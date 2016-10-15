package gsmserver.Components;

import com.codeborne.selenide.SelenideElement;
import gsmserver.Utils.CustomConditions;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BaseComponent{

    public static final SelenideElement formDescription = $("div.page-description > p");
    public static final SelenideElement formSummary = $("div.form-summary");

    @Step
    public static BaseComponent submitForm(){
        $("button[type='submit']").click();
        return new BaseComponent();
    }

    @Step
    public void submitShouldBeSucceeded(){
        formSummary.shouldHave(CustomConditions.classSuccess(), visible);
    }

    @Step
    public void submitShouldBeFailed(){
        formSummary.shouldHave(CustomConditions.classError(), visible);
    }

}
