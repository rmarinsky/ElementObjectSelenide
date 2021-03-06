package gsmserver.Components.Popups;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import gsmserver.Components.MainComponent;
import gsmserver.Components.User;
import gsmserver.Utils.Random;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static gsmserver.Utils.CustomConditions.classError;
import static gsmserver.Utils.CustomConditions.signaturesOfFieldsHaveRequiredLabel;

public class ForgotPassword{

    private User user;

    ForgotPassword(){
        $("#forgot-password-view").shouldBe(Condition.visible);
        user = new User();
    }

    public void verifyForgotPasswordValidation(){
        SelenideElement fieldEmail = $(user.fieldEmail);
        signaturesOfFieldsHaveRequiredLabel(user.fieldEmail);
        fieldEmail.clear();
        fieldEmail.click();
        MainComponent.formDescription.click();
        fieldEmail.shouldHave(classError());
        fieldEmail.sendKeys(Random.generateRandomEmail());
        MainComponent.formDescription.click();
        fieldEmail.shouldNotHave(classError());
        this.shouldBeVisibleMassageNotRegisteredEmail();
        fieldEmail.clear();
        fieldEmail.sendKeys(Random.generateRandomString());
        MainComponent.submitForm();
        fieldEmail.shouldHave(classError());
        fieldEmail.sendKeys("@");
        MainComponent.submitForm();
        fieldEmail.shouldHave(classError());
        fieldEmail.sendKeys(Random.generateRandomString());
        MainComponent.submitForm();
        fieldEmail.shouldHave(classError());
        fieldEmail.sendKeys(".com");
        MainComponent.submitForm();
        this.shouldBeVisibleMassageNotRegisteredEmail();
    }

    @Step
    private void shouldBeVisibleMassageNotRegisteredEmail(){
        MainComponent.submitForm().submitShouldBeFailed();
        MainComponent.formSummary.findAll("a[href]").shouldHave(CollectionCondition.texts("register", " without registration"));
    }

    @Step("Recovery password for user:: [{0}]")
    public void recoveryPasswordForUser(String value){
        SelenideElement fieldEmail = $(user.fieldEmail);
        fieldEmail.clear();
        fieldEmail.setValue(value);
        MainComponent.submitForm();
        fieldEmail.shouldBe(Condition.hidden);
        $("button[type='submit']").shouldBe(Condition.hidden);
    }

}
