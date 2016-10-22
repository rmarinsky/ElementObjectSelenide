package gsmserver;

import gsmserver.Components.Cart;
import gsmserver.Components.Product;
import gsmserver.Utils.BaseTest;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;

public class CartTests extends BaseTest{

    @Title(value = "Product exist in cart test")
    @Test public void productExistInCartTest(){
        new Product().addProductToCartViaJs(834632, 1);
        Cart.openCartPage().cartShouldHaveProduct(834632);
    }

}
