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
        this.user.fillFirstName(firstNameValue).
                fillLogin(loginValue).
                fillEmail(emailValue);
        BaseComponent.submitForm();
        $("#SignUpConfirm").shouldBe(Condition.visible);
    }

    @Step
    private Registration clearForm(){
        this.user.firstName.clear();
        this.user.login.clear();
        this.user.email.clear();
        return this;
    }

    @Step
    public Registration verifyVisibleOfRequiredLabelsInRegistrationForm(){
        signaturesOfFieldsHaveRequiredLabel(this.user.login, this.user.email);
        return this;
    }

    @Title("Verify validation the form \"Registration\" with empty fields in form")
    @Step
    public Registration verifyRegistrationFormValidationWithEmptyForm(){
        this.clearForm();
        BaseComponent.formSummary.shouldNotBe(Condition.visible);
        this.user.firstName.click();
        this.user.login.click();
        this.user.email.click();
        this.user.firstName.click();
        this.user.firstName.shouldNotHave(CustomConditions.classError());
        this.user.login.shouldHave(CustomConditions.classError());
        this.user.email.shouldHave(CustomConditions.classError());
        BaseComponent.submitForm().submitShouldBeFailed();
        this.user.login.shouldHave(CustomConditions.classError(), CustomConditions.cannotBeBlankTitleTip());
        this.user.email.shouldHave(CustomConditions.classError(), CustomConditions.cannotBeBlankTitleTip());
        $("label.styled-checkbox.user-agreement").click();
        $("button[type='submit']").shouldHave(Condition.attribute("disabled"));
        this.user.fillLogin(Random.generateRandomString());
        this.user.email.sendKeys(Random.generateRandomEmail());
        this.user.firstName.click();
        this.user.login.shouldNotHave(CustomConditions.classError());
        this.user.email.shouldNotHave(CustomConditions.classError());
        $("label.styled-checkbox.user-agreement").click();
        $("button[type='submit']").shouldNotHave(Condition.attribute("disabled"));
        return this;
    }

    @Title("Verify validation the form \"Registration\" with random fields in form, but login should be is already registered")
    @Step
    public Registration verifyRegistrationFormValidationWithSomeValues(){
        this.clearForm();
        this.user.fillLogin("t");
        this.user.firstName.click();
        this.user.login.shouldHave(CustomConditions.classError());
        this.user.fillLogin("ttt");
        this.user.firstName.click();
        this.user.login.shouldNotHave(CustomConditions.classError());
        this.clearForm();
        this.user.fillLogin(defaultEmail);
        this.user.fillEmail(Random.generateRandomString());
        this.user.firstName.click();
        this.user.email.shouldHave(CustomConditions.classError());
        BaseComponent.submitForm().submitShouldBeFailed();
        this.user.email.sendKeys("@");
        BaseComponent.submitForm().submitShouldBeFailed();
        this.user.email.shouldHave(CustomConditions.classError());
        this.user.email.sendKeys(Random.generateRandomString());
        BaseComponent.submitForm().submitShouldBeFailed();
        this.user.email.shouldHave(CustomConditions.classError());
        this.user.email.sendKeys(".com");
        BaseComponent.submitForm().submitShouldBeFailed();
        this.user.email.shouldNotHave(CustomConditions.classError());
        this.user.login.shouldHave(CustomConditions.classError().because("Login \"" + defaultEmail + "\" is already registered"));
        return this;
    }

}
