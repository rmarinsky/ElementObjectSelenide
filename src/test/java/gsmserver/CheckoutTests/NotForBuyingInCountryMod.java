package gsmserver.CheckoutTests;

import gsmserver.Components.Checkout.ContactInformation;
import gsmserver.Components.Product;
import gsmserver.Components.User;
import gsmserver.Utils.BaseTest;
import gsmserver.Utils.Random;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static gsmserver.Utils.DefaultData.defaultEmail;
import static gsmserver.Utils.DefaultData.defaultPassword;

public class NotForBuyingInCountryMod extends BaseTest{

    @Before
    public void beforeBase(){
        clearCookies();
        new Product().addProductToCartViaJs(832722, 1);
        ContactInformation.openCheckoutPage();
    }

    @Test
    public void notForUsaModificationWOAuth(){
        String testTempString = Random.generateRandomString();
        new User().chooseCountry("United States").
                chooseRegion("Colorado").
                fillPhoneByCountry("United States", "112223333").
        fillFirstName(testTempString).fillLastName(testTempString).
        fillEmail(Random.generateRandomEmail()).fillAddress(testTempString);
    }

    @Ignore
    @Test
    public void notForUsaModificationWithAuth(){ //// TODO: 15.10.2016 not finished
        new User().loginUserViaJS(defaultEmail, defaultPassword);

    }

}
