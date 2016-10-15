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
        $("#login-view").shouldBe(Condition.visible);
        this.user = new User();
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
        this.user.login.shouldBe(Condition.empty);
        this.user.password.shouldBe(Condition.empty);
        CustomConditions.signaturesOfFieldsHaveRequiredLabel(user.login, user.password);
        this.user.login.click();
        this.user.password.click();
        $("td.buy-without-signup-message").click();
        this.shouldHaveClassError(this.user.login);
        this.shouldHaveClassError(this.user.password);
        submitForm().submitShouldBeFailed();
        this.user.fillLogin(tempValue);
        $("td.buy-without-signup-message").click();
        this.shouldNotHaveClassError(this.user.login);
        this.user.fillPassword(tempValue);
        $("td.buy-without-signup-message").click();
        this.shouldNotHaveClassError(this.user.password);
        this.user.fillLogin(tempValue);
        this.user.password.clear();
        submitForm().submitShouldBeFailed();
        shouldNotHaveClassError(this.user.login);
        this.user.password.shouldHave(classError(), cannotBeBlankTitleTip());
        submitForm().submitShouldBeFailed();
        this.user.fillPassword(tempValue);
        submitForm().submitShouldBeFailed();
        this.user.login.shouldHave(classError(), originalTitleTip("Incorrect username or password"));
        this.user.password.shouldHave(classError(), originalTitleTip("Incorrect username or password"));
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
