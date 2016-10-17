package gsmserver.Components.Popups;

import com.codeborne.selenide.Condition;
import gsmserver.Components.BaseComponent;
import gsmserver.Components.User;
import gsmserver.Utils.CustomConditions;
import gsmserver.Utils.Random;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import static com.codeborne.selenide.Selenide.$;
import static gsmserver.Utils.CustomConditions.signaturesOfFieldsHaveRequiredLabel;
import static gsmserver.Utils.DefaultData.defaultEmail;

public class Registration{

    private User user;

    Registration(){
        $("#registration-view").shouldBe(Condition.visible);
        user = new User();
    }

    @Step
    public void registerUser(final String firstNameValue, final String loginValue, final String emailValue){
        user.fillFirstName(firstNameValue).
                fillLogin(loginValue).
                fillEmail(emailValue);
        BaseComponent.submitForm();
        $("#SignUpConfirm").shouldBe(Condition.visible);
    }

    @Step
    private Registration clearForm(){
        $(user.fieldFirstName).clear();
        $(user.fieldLogin).clear();
        $(user.fieldEmail).clear();
        return this;
    }

    @Step
    public Registration verifyVisibleOfRequiredLabelsInRegistrationForm(){
        signaturesOfFieldsHaveRequiredLabel(user.fieldLogin, user.fieldEmail);
        return this;
    }

    @Title("Verify validation the form \"Registration\" with empty fields in form")
    @Step
    public Registration verifyRegistrationFormValidationWithEmptyForm(){
        this.clearForm();
        BaseComponent.formSummary.shouldNotBe(Condition.visible);
        $(user.fieldFirstName).click();
        $(user.fieldLogin).click();
        $(user.fieldEmail).click();
        $(user.fieldFirstName).click();
        $(user.fieldFirstName).shouldNotHave(CustomConditions.classError());
        $(user.fieldLogin).shouldHave(CustomConditions.classError());
        $(user.fieldEmail).shouldHave(CustomConditions.classError());
        BaseComponent.submitForm().submitShouldBeFailed();
        $(user.fieldLogin).shouldHave(CustomConditions.classError(), CustomConditions.cannotBeBlankTitleTip());
        $(user.fieldEmail).shouldHave(CustomConditions.classError(), CustomConditions.cannotBeBlankTitleTip());
        $("label.styled-checkbox.user-agreement").click();
        $("button[type='submit']").shouldHave(Condition.attribute("disabled"));
        user.fillLogin(Random.generateRandomString());
        $(user.fieldEmail).sendKeys(Random.generateRandomEmail());
        $(user.fieldFirstName).click();
        $(user.fieldLogin).shouldNotHave(CustomConditions.classError());
        $(user.fieldEmail).shouldNotHave(CustomConditions.classError());
        $("label.styled-checkbox.user-agreement").click();
        $("button[type='submit']").shouldNotHave(Condition.attribute("disabled"));
        return this;
    }

    @Title("Verify validation the form \"Registration\" with random fields in form, but login should be is already registered")
    @Step
    public Registration verifyRegistrationFormValidationWithSomeValues(){
        this.clearForm();
        user.fillLogin("t");
        $(user.fieldFirstName).click();
        $(user.fieldLogin).shouldHave(CustomConditions.classError());
        user.fillLogin("ttt");
        $(user.fieldFirstName).click();
        $(user.fieldLogin).shouldNotHave(CustomConditions.classError());
        this.clearForm();
        user.fillLogin(defaultEmail);
        user.fillEmail(Random.generateRandomString());
        $(user.fieldFirstName).click();
        $(user.fieldEmail).shouldHave(CustomConditions.classError());
        BaseComponent.submitForm().submitShouldBeFailed();
        $(user.fieldEmail).sendKeys("@");
        BaseComponent.submitForm().submitShouldBeFailed();
        $(user.fieldEmail).shouldHave(CustomConditions.classError());
        $(user.fieldEmail).sendKeys(Random.generateRandomString());
        BaseComponent.submitForm().submitShouldBeFailed();
        $(user.fieldEmail).shouldHave(CustomConditions.classError());
        $(user.fieldEmail).sendKeys(".com");
        BaseComponent.submitForm().submitShouldBeFailed();
        $(user.fieldEmail).shouldNotHave(CustomConditions.classError());
        $(user.fieldLogin).shouldHave(CustomConditions.classError().because("Login \"" + defaultEmail + "\" is already registered"));
        return this;
    }

}
