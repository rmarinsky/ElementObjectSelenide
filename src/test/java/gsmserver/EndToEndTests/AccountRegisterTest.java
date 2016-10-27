package gsmserver.EndToEndTests;

import com.tempmail.TempMailApiClient;
import gsmserver.Components.Account.AccountChangePassword;
import gsmserver.Components.Account.AccountPersonalData;
import gsmserver.Components.Account.AccountSubscriptions;
import gsmserver.Components.TopLinks;
import gsmserver.Utils.BaseTest;
import gsmserver.Utils.Random;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.tempmail.TempMailApiClient.getTempEmailApi;

public class AccountRegisterTest extends BaseTest {


    @BeforeClass
    public static void beforeClass() throws IOException, SAXException, ParserConfigurationException {

        TempMailApiClient.createTempMailAccountByName(Random.generateRandomTestNameString());
    }

    @Test
    public void registerNewUserAndChangePassword() throws InterruptedException {

        new TopLinks().openLoginPopup().openRegistrationForm().
                registerUser(super.defaultTestValue, getTempEmailApi(), getTempEmailApi());

        new TempMailApiClient().waitFirstLetter().confirmRegistrationByApi(TempMailApiClient.getUrlFromFirstLetter()).
                checkContentOfConfirmationRegistration().subscribeOnSubjectByIndex(0);

        new AccountSubscriptions().shouldBeSelectedSubscriptionByIndex(0);

        AccountPersonalData.openPersonalDataPage().loginEmailFirstNameShouldBe(super.defaultTestValue, getTempEmailApi(), getTempEmailApi());

        AccountChangePassword.openChangePasswordPage().changePassword(getTempEmailApi());
        new TempMailApiClient().waitSecondLetter().confirmPasswordRecoveryByApi(TempMailApiClient.getUrlFromSecondLetter()).checkContentConfirmationChangePassword();

        new TopLinks().logoutUser().
                openLoginPopup().loginUser(getTempEmailApi(), getTempEmailApi()).userHaveName(super.defaultTestValue);

        AccountSubscriptions.openAccountSubscriptionsPage().shouldBeSelectedSubscriptionByIndex(0);
    }

}
