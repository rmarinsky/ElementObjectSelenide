package gsmserver.Components.Account;

import com.codeborne.selenide.Condition;
import gsmserver.Components.MainComponent;
import gsmserver.Components.User;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AccountChangePassword {

    private User user;

    private AccountChangePassword(){
        $("#reset-password-view").shouldBe(Condition.visible);
        user = new User();
    }

    @Step
    public static AccountChangePassword openChangePasswordPage(){
        open("/account/changepassword/");
        return new AccountChangePassword();
    }

    @Step("Change password to: [{0}]")
    public void changePassword(final String tempPassword){
        user.fillPassword(tempPassword);
        user.fillConfirmPassword(tempPassword);
        MainComponent.submitForm();
        $("#ChangePasswordConfirm").shouldBe(Condition.visible);
    }

}
