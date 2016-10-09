package gsmserver.Pages;

import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static gsmserver.Utils.CustomConditions.submitForm;

public final class ConfirmationPage {

    @Step
    public ConfirmationPage checkContentOfConfirmationRegistration(){
        $("h1").shouldHave(text("Registration Completed"));
        $("#content-info").shouldHave(text("You have successfully registered"));
        return this;
    }

    @Step
    public ConfirmationPage checkContentConfirmationChangePassword(){
        $("h1").shouldHave(text("Change Password"));
        $("#content-info").shouldHave(text("Your password has been changed successfully."));
        return this;
    }

    @Step
    public void subscribeOnSubjectByIndex(int i){
        $("label.styled-checkbox", i).click();
        submitForm();
    }

}
