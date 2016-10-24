package gsmserver;

import com.codeborne.selenide.Condition;
import gsmserver.Components.Cart;
import gsmserver.Components.Product;
import gsmserver.Utils.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;

import static com.codeborne.selenide.Selenide.$;
import static gsmserver.Utils.DefaultData.defaultAvrgCostProduct;
import static gsmserver.Utils.DefaultData.lowCostProduct;

public class CartTests extends BaseTest{

    @BeforeClass
    public static void beforeClass(){
        Cart.openCartPage();
    }

    @Before
    public void beforeBase(){
        new Product().addProductToCartViaJS(defaultAvrgCostProduct, 1);
    }

    @After
    public void afterTest(){
        new Product().removeProductFromCartViaJS(defaultAvrgCostProduct);
        clearCookies();
    }

    @Title(value = "Product exist in cart test")
    @Test public void productExistInCartTest(){
        Cart.openCartPage().cartShouldHaveProduct(defaultAvrgCostProduct);
    }

    @Test
    public void removeProductTest(){
        Cart.openCartPage().
                keepProductInCart(defaultAvrgCostProduct).
                removeProductFromCart(defaultAvrgCostProduct);
    }

    @Title(value = "Cart should have no possible to order with cost lower than 20$")
    @Test
    public void forbiddenCheckoutWithLowCost(){
        new Product().removeProductFromCartViaJS(defaultAvrgCostProduct);
        new Product().addProductToCartViaJS(lowCostProduct, 1);
        Cart.openCartPage().cartShouldHaveProduct(lowCostProduct).
                shouldBeVisibleMassageLowCost();
        $("#goto-checkout").shouldHave(Condition.cssClass("inactive-button"));
    }

}
