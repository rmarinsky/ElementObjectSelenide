package gsmserver.EndToEndTests;

import com.tempmail.TempMail;
import gsmserver.Components.Account.AccountChangePassword;
import gsmserver.Components.Account.AccountPersonalData;
import gsmserver.Components.Account.AccountSubscriptions;
import gsmserver.Components.TopLinks;
import gsmserver.Pages.HomePage;
import gsmserver.Utils.BaseTest;
import gsmserver.Utils.Random;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.tempmail.TempMail.getTempEMail;
import static com.tempmail.TempMail.getTempPassword;

public class AccountRegisterTest extends BaseTest {

    @BeforeClass
    public static void beforeClass(){
        TempMail.openTempMail().saveEmailAddress();
        TempMail.setTempPassword(Random.generateRandomString());
    }

    @Test
    public void registerNewUserAndChangePassword(){
        HomePage.openHomePage();
        new TopLinks().openLoginPopup().openRegistrationView().
                registerUser(super.defaultTestValue, getTempEMail(), getTempEMail());
        TempMail.openTempMail().
                confirmRegistration().checkContentOfConfirmationRegistration().subscribeOnSubjectByIndex(0);
        new AccountSubscriptions().shouldBeSelectedSubscriptionByIndex(0);

        AccountPersonalData.openPersonalDataPage().loginEmailFirstNameShouldBe(super.defaultTestValue, getTempEMail(), getTempEMail());

        AccountChangePassword.openChangePasswordPage().changePassword(getTempPassword());
        TempMail.openTempMail().conformPasswordRecovery().checkContentConfirmationChangePassword();
        new TopLinks().logoutUser().
                openLoginPopup().loginUser(getTempEMail(), getTempPassword()).userHaveName(super.defaultTestValue);

        AccountSubscriptions.openAccountSubscriptionsPage().shouldBeSelectedSubscriptionByIndex(0);
    }


}
