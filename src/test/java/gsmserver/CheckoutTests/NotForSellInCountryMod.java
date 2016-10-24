package gsmserver.CheckoutTests;

import gsmserver.Components.MainComponent;
import gsmserver.Components.Checkout.ContactInformation;
import gsmserver.Components.Product;
import gsmserver.Components.Search;
import gsmserver.Components.User;
import gsmserver.Utils.BaseTest;
import gsmserver.Utils.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static gsmserver.Utils.DefaultData.defaultEmail;
import static gsmserver.Utils.DefaultData.defaultPassword;
import static gsmserver.Utils.DefaultData.octopusProduct;

public class NotForSellInCountryMod extends BaseTest{

    @Before
    public void beforeBase(){
        clearCookies();
        new Product().addProductToCartViaJS(octopusProduct, 1);
        ContactInformation.openCheckoutPage();
    }

    @After
    public void afterClass(){
        new Product().removeProductFromCartViaJS(octopusProduct);
    }

    @Test
    public void notForUsaModificationWOAuth(){
        String testTempString = Random.generateRandomString();
        new ContactInformation().openEditAddressForm().
                fillFirstName(testTempString).fillLastName(testTempString).
                chooseCountry("United States").chooseRegion("Colorado").
                fillPhoneByCountry("United States", "112223333").fillCity("testTempString").
        fillEmail(Random.generateRandomEmail()).fillAddress(testTempString);
        MainComponent.submitStepCheckout();
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
        MainComponent.submitStepCheckout();
        new ContactInformation().shouldBeVisibleNotificationForbiddenToSellProduct(octopusProduct).
                removeAndCheckRemovingInCart(octopusProduct);
    }

    @Test
    public void testForSearchForbiddenProducts(){
        new Search().searchFor(String.valueOf(octopusProduct)).searchResultShouldHasSearchedPhrase(String.valueOf(octopusProduct)).shouldHaveProduct(octopusProduct);
        new User().loginUserViaJS(defaultEmail, defaultPassword).setCountryUSAForUserViaJS();
        new Search().searchFor(String.valueOf(octopusProduct)).searchResultShouldHasSearchedPhrase(String.valueOf(octopusProduct)).shouldHaveNoProduct(octopusProduct);
        new User().setCountrySpainForUserViaJS();
    }

}
