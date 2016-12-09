package gsmserver.CheckoutTests;

import gsmserver.Components.Checkout.Checkout;
import gsmserver.Components.Product;
import gsmserver.Pages.HomePage;
import gsmserver.Utils.BaseTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static gsmserver.Utils.DefaultData.avrgCostProduct;

public class CheckoutContactInformationTests extends BaseTest{

    @BeforeClass
    public static void beforeClass(){
        HomePage.openHomePage();
        clearCookies();
        Product.addProductToCartViaJS(avrgCostProduct, 1);
        Checkout.openCheckoutPage();
    }

    @AfterClass
    public static void afterClass(){
        Product.removeProductFromCartViaJS(avrgCostProduct);
    }

    @Test
    public void verifyCountriesWithRequiredTaxIdTest(){
        new Checkout().verifyCountriesWithRequiredTaxId("Brazil", "El Salvador", "Ecuador", "Argentina");
    }

    @Test
    public void checkMiddleNameRequiringTest(){
        new Checkout().verifyCountriesWithRequiredMiddleName("Azerbaijan", "Armenia", "Belarus", "Georgia", "Kazakhstan", "Kyrgyzstan", "Moldova, Republic Of",
                                                                "Russian Federation", "Tajikistan", "Turkmenistan", "Ukraine", "Uzbekistan");
    }

    @Test
    public void checkRegionRequiring(){
        new Checkout().verifyCountriesWithRequiredRegion("Mexico", "Ukraine", "Spain", "Russian Federation", "Australia",
                "Canada", "France", "Portugal", "United Kingdom", "United States", "United Arab Emirates");
    }

}
