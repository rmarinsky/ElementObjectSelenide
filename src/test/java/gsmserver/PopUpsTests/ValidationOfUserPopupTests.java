package gsmserver.PopUpsTests;

import gsmserver.Components.TopLinks;
import gsmserver.Pages.HomePage;
import gsmserver.Utils.BaseTest;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Description;

@Description("This is a complex verifications for selctorLogin, forgot password and registration forms in main popup")
public class ValidationOfUserPopupTests extends BaseTest{

    @Before
    public void beforeTest(){
        HomePage.openHomePage();
    }

    @Test
    public void verifyValidationOfLoginFormTest(){
        new TopLinks().openLoginPopup().
                verifyLoginFormValidation();
    }

    @Test
    public void verifyForgotPasswordValidationTest(){
        new TopLinks().openLoginPopup().openForgotPasswordForm().
                verifyForgotPasswordValidation();
    }

    @Test
    public void verifyRegistrationFormValidationTest(){
        new TopLinks().openLoginPopup().openRegistrationForm().
                verifyVisibleOfRequiredLabelsInRegistrationForm().
                verifyRegistrationFormValidationWithEmptyForm().
                verifyRegistrationFormValidationWithSomeValues();
    }

}
