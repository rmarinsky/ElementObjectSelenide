package gsmserver.CheckoutTests;

import gsmserver.Components.Checkout.ContactInformation;
import gsmserver.Components.Product;
import gsmserver.Utils.BaseTest;
import org.junit.*;

import static gsmserver.Utils.DefaultData.defaultAvrgCostProduct;

public class CheckoutContactInformationTests extends BaseTest{

    @BeforeClass
    public static void beforeClass(){
        clearCookies();
        new Product().addProductToCartViaJS(defaultAvrgCostProduct, 1);
    }

    @AfterClass
    public static void afterClass(){
        new Product().removeProductFromCartViaJS(defaultAvrgCostProduct);
    }

    @Before
    public void beforeBase(){
        ContactInformation.openCheckoutPage();
    }

    @Test
    public void verifyCountriesWithRequiredTaxIdTest(){
        new ContactInformation().verifyCountriesWithRequiredTaxId("Brazil", "El Salvador", "Ecuador", "Argentina");
    }

    @Test
    public void checkMiddleNameRequiringTest(){
        new ContactInformation().verifyCountriesWithRequiredMiddleName("Azerbaijan", "Armenia", "Belarus", "Georgia", "Kazakhstan", "Kyrgyzstan", "Moldova, Republic Of",
                                                                "Russian Federation", "Tajikistan", "Turkmenistan", "Ukraine", "Uzbekistan");
    }

    @Test
    public void checkRegionRequiring(){
        new ContactInformation().verifyCountriesWithRequiredRegion("Mexico", "Ukraine", "Spain", "Russian Federation", "Australia",
                "Canada", "France", "Portugal", "United Kingdom", "United States", "United Arab Emirates");
    }

}
