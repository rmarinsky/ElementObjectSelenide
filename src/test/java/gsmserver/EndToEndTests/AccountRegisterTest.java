package gsmserver.EndToEndTests;

import com.codeborne.selenide.Selenide;
import com.tempmail.TempMail;
import gsmserver.Components.Account.AccountChangePassword;
import gsmserver.Components.Account.AccountPersonalData;
import gsmserver.Components.Account.AccountSubscriptions;
import gsmserver.Components.TopLinks;
import gsmserver.Utils.BaseTest;
import gsmserver.Utils.Random;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static com.tempmail.TempMail.getTempEMail;
import static com.tempmail.TempMail.getTempPassword;
import static gsmserver.Utils.DefaultData.defaultTestValue;

public class AccountRegisterTest extends BaseTest {

    @BeforeClass
    public static void beforeClass(){
        TempMail.openTempMail().saveEmailAddress();
        TempMail.setTempPassword(Random.generateRandomString());
    }

    @Ignore
    @Test
    public void registerNewUserAndChangePassword(){
        clearCookies();
        Selenide.refresh();
        new TopLinks().openLoginPopup().openRegistrationForm().
                registerUser(defaultTestValue, getTempEMail(), getTempEMail());
        TempMail.openTempMail().
                confirmRegistration().checkContentOfConfirmationRegistration().subscribeOnSubjectByIndex(0);
        new AccountSubscriptions().shouldBeSelectedSubscriptionByIndex(0);

        AccountPersonalData.openPersonalDataPage().loginEmailFirstNameShouldBe(defaultTestValue, getTempEMail(), getTempEMail());

        AccountChangePassword.openChangePasswordPage().changePassword(getTempPassword());
        TempMail.openTempMail().conformPasswordRecovery().checkContentConfirmationChangePassword();
        new TopLinks().logoutUser().
                openLoginPopup().loginUser(getTempEMail(), getTempPassword()).userHaveName(defaultTestValue);

        AccountSubscriptions.openAccountSubscriptionsPage().shouldBeSelectedSubscriptionByIndex(0);
    }

}
