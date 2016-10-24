package gsmserver;

import gsmserver.Components.Product;
import gsmserver.Utils.BaseTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static gsmserver.Utils.DefaultData.defaultProductId;

public class ProductPageTests extends BaseTest{

    @BeforeClass
    public static void beforeClass(){
        Product.openProductPage(defaultProductId);
    }

    @Before
    public void beforeBase(){
        clearCookies();
    }

    @Test
    public void addProductToCartFromProductPage(){
        Product.openProductPage(defaultProductId).
                addToCart(defaultProductId).goToCart(defaultProductId).
                cartShouldHaveProduct(defaultProductId);
    }

    @Test
    public void checkInStockStoresTest(){
        Product.openProductPage(defaultProductId).
                checkInStockStores("HK", "EU");
    }

    @Test
    public void checkDeliveryCalculation(){
        Product.openProductPage(defaultProductId).
                openDeliveryCalculationPopup().
                checkFormAndCalculatePrice().
                closeDeliveryCalculationPopup();
    }

}
