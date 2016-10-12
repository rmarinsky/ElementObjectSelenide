package gsmserver.Components;

import gsmserver.Utils.CustomConditions;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;

public class GeneralForm {

    //public static final SelenideElement formSummary = ;

    @Step
    public static GeneralForm submitForm(){
        $("button[type='submit']").click();
        return new GeneralForm();
    }

    @Step
    public void submitSucceeded(){
        $("div.form-summary").shouldHave(CustomConditions.classSuccess());
    }

    @Step
    public void submitFailed(){
        $("div.form-summary").shouldHave(CustomConditions.classError());
    }

}
