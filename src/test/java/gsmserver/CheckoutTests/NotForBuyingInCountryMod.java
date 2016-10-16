package gsmserver.CheckoutTests;

import gsmserver.Components.BaseComponent;
import gsmserver.Components.Checkout.ContactInformation;
import gsmserver.Components.Product;
import gsmserver.Components.Search;
import gsmserver.Components.User;
import gsmserver.Utils.BaseTest;
import gsmserver.Utils.Random;
import org.junit.Before;
import org.junit.Test;

import static gsmserver.Utils.DefaultData.defaultEmail;
import static gsmserver.Utils.DefaultData.defaultPassword;

public class NotForBuyingInCountryMod extends BaseTest{

    private Integer octopusProduct = 832722;

    @Before
    public void beforeBase(){
        clearCookies();
        new Product().addProductToCartViaJs(octopusProduct, 1);
        ContactInformation.openCheckoutPage();
    }

    @Test
    public void notForUsaModificationWOAuth(){
        String testTempString = Random.generateRandomString();
        new ContactInformation().openEditAddressForm().
                fillFirstName(testTempString).fillLastName(testTempString).
                chooseCountry("United States").chooseRegion("Colorado").
                fillPhoneByCountry("United States", "112223333").fillCity("testTempString").
        fillEmail(Random.generateRandomEmail()).fillAddress(testTempString);
        BaseComponent.submitStepCheckout();
        new ContactInformation().shouldBeVisibleNotificationForbiddenToSellProduct(octopusProduct).
                removeAndCheckRemovingInCart(octopusProduct);
    }

    @Test
    public void notForUsaModificationWithAuth(){
        String testTempString = Random.generateRandomString();
        new User().loginUserViaJS(defaultEmail, defaultPassword);
        new ContactInformation().openEditAddressForm().fillFirstName(testTempString).fillLastName(testTempString).
                chooseCountry("United States").chooseRegion("Colorado").
                fillPhoneByCountry("United States", "112223333").fillCity("testTempString").
                fillEmail(Random.generateRandomEmail()).fillAddress(testTempString);
        BaseComponent.submitStepCheckout();
        new ContactInformation().shouldBeVisibleNotificationForbiddenToSellProduct(octopusProduct).
                removeAndCheckRemovingInCart(octopusProduct);
    }

    @Test
    public void test(){
        new Search().searchFor(String.valueOf(octopusProduct)).searchResultShouldHasSearchedPhrase().shouldHaveProduct(octopusProduct);
        new User().loginUserViaJS(defaultEmail, defaultPassword).setCountryUSAForUserViaJS();
        new Search().searchFor(String.valueOf(octopusProduct)).searchResultShouldHasSearchedPhrase().shouldNotHaveProduct(octopusProduct);
        new User().setCountrySpainForUserViaJS();
    }

}
