package gsmserver.Components.Account;

import com.codeborne.selenide.Condition;
import gsmserver.Components.User;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static gsmserver.Components.BaseComponent.submitForm;

public class AccountChangePassword {

    private User user;

    private AccountChangePassword(){
        $("#reset-password-view").shouldBe(Condition.visible);
        this.user = new User();
    }

    @Step
    public static AccountChangePassword openChangePasswordPage(){
        open("/account/changepassword/");
        return new AccountChangePassword();
    }

    @Step
    public void changePassword(final String tempPassword){
        this.user.password.setValue(tempPassword);
        this.user.passwordConfirm.setValue(tempPassword);
        submitForm();
        $("#ChangePasswordConfirm").shouldBe(Condition.visible);
    }

}
