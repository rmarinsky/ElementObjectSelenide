package gsmserver.Components.Popups;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import gsmserver.Components.TopLinks;
import gsmserver.Components.User;
import gsmserver.Utils.CustomConditions;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static gsmserver.Components.BaseComponent.submitForm;
import static gsmserver.Utils.CustomConditions.*;
import static gsmserver.Utils.Random.generateRandomString;

public class AuthorisationPopup {

    private User user;

    public AuthorisationPopup(){
        $("#selctorLogin-view").shouldBe(Condition.visible);
        user = new User();
    }

    @Step
    public TopLinks loginUser(final String login, final String password){
        user.fillLogin(login).
                fillPassword(password);
        submitForm();
        return new TopLinks();
    }

    @Step
    private void shouldNotHaveClassError(SelenideElement element){
        element.shouldNotHave(classError());
    }

    @Step
    private void shouldHaveClassError(SelenideElement element){
        element.shouldHave(classError());
    }

    public AuthorisationPopup verifyLoginFormValidation(){
        String tempValue = generateRandomString();
        user.fieldLogin.shouldBe(Condition.empty);
        user.fieldPassword.shouldBe(Condition.empty);
        CustomConditions.signaturesOfFieldsHaveRequiredLabel(user.fieldLogin, user.fieldPassword);
        user.fieldLogin.click();
        user.fieldPassword.click();
        $("td.buy-without-signup-message").click();
        this.shouldHaveClassError(user.fieldLogin);
        this.shouldHaveClassError(user.fieldPassword);
        submitForm().submitShouldBeFailed();
        user.fillLogin(tempValue);
        $("td.buy-without-signup-message").click();
        this.shouldNotHaveClassError(user.fieldLogin);
        user.fillPassword(tempValue);
        $("td.buy-without-signup-message").click();
        this.shouldNotHaveClassError(user.fieldPassword);
        user.fillLogin(tempValue);
        user.fieldPassword.clear();
        submitForm().submitShouldBeFailed();
        shouldNotHaveClassError(user.fieldLogin);
        user.fieldPassword.shouldHave(classError(), cannotBeBlankTitleTip());
        submitForm().submitShouldBeFailed();
        user.fillPassword(tempValue);
        submitForm().submitShouldBeFailed();
        user.fieldLogin.shouldHave(classError(), originalTitleTip("Incorrect username or password"));
        user.fieldPassword.shouldHave(classError(), originalTitleTip("Incorrect username or password"));
        submitForm().submitShouldBeFailed();
        return this;
    }

    @Step
    public Registration openRegistrationForm(){
        $(byLinkText("Registration")).click();
        return new Registration();
    }

    @Step
    public ForgotPassword openForgotPasswordForm(){
        $("[data-action-click*='forgotPassword']").click();
        return new ForgotPassword();
    }

}
