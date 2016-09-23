package gsmserver;

import gsmserver.Components.Cart;
import gsmserver.Helpers.JS.JSProductHelper;
import gsmserver.Utils.BaseTest;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;

import static com.codeborne.selenide.Selenide.open;

public class CartTests extends BaseTest{

    @Before public void before(){
        open("");
        new JSProductHelper().addProductToCart(834632, 1);
    }

    @Title(value = "Product exist in cart test")
    @Test public void productExistInCartTest(){
        Cart.openCartPage().cartShouldHaveProduct(834632);
    }

}
