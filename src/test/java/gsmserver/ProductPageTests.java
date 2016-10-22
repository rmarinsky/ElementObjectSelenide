package gsmserver;

import gsmserver.Components.Product;
import gsmserver.Utils.BaseTest;
import org.junit.Before;
import org.junit.Test;

public class ProductPageTests extends BaseTest{

    private Integer testProductId = 858049;

    @Before
    public void beforeBase(){
        clearCookies();
        Product.openProductPage(testProductId);
    }

    @Test
    public void test(){
        new Product().addToCart(testProductId).goToCart(testProductId).
                cartShouldHaveProduct(testProductId);
    }

}
