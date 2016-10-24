package gsmserver.Components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import gsmserver.Components.Popups.DeliveryCalculation;
import gsmserver.Utils.JSExecutor;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class Product {

    private SelenideElement inStockStores = $(".availability-in-stores");

    public final static By buttonAddToCart = byCssSelector("a.ga-add-to-cart");
    public final By buttonMinus = byName("btn-minus");
    public final static By inputQuantity = byName("quantity");
    public final By buttonPlus = byName("btn-plus");
    private final By buttonToCart = byCssSelector("span.in-cart > a");

    public Product(){
    }

    private Product(Integer productId){
        findProduct(productId).shouldBe(Condition.visible);
    }

    @Step("Add to cart product with id[{0}]")
    public Product addToCart(Integer idProduct){
        findProduct(idProduct).$(buttonAddToCart).click();
        return this;
    }

    @Step
    public Cart goToCart(Integer idProduct){
        findProduct(idProduct).$(buttonToCart).click();
        return new Cart();
    }

    @Step("Open product page with id: [{0}]")
    public static Product openProductPage(Integer productId){
        Selenide.open(String.format("/item/%s/", productId));
        return new Product(productId);
    }

    @Step("Find product by id: [{0}]")
    static SelenideElement findProduct(final Integer id){
        return $(String.format("[data-product-id='%s']", id));
    }

    @Step
    public void checkInStockStores(String... stores){
        for (String store : stores) {
            inStockStores.$(withText(store)).shouldHave(Condition.cssClass("in-stock"));
        }
    }

    public DeliveryCalculation openDeliveryCalculationPopup(){
        $(".product-delivery .right").click();
        return new DeliveryCalculation();
    }

    /*Java Script opedations*/

    @Step("Add to cart product: [{0}] in count: [{1}]")
    public static void addProductToCartViaJS(final Integer productId, Integer count){
        new JSExecutor().GETRequest(String.format("/ajax/cart/add/%s,%s", productId, count));
    }

    public static void addProductSToCartViaJs(final Integer... productId){
        for(Integer id : productId) {
            new JSExecutor().GETRequest(String.format("/ajax/cart/add/%s,1", id));
        }
    }

    @Step("Remove product form user's wish list via JS")
    public static void removeProductFromWishListViaJS(Integer productId){
        new JSExecutor().GETRequest("/account/wishlist/remove/?productId="+String.valueOf(productId));
    }

    @Step("Remove product form Cart via JS")
    public static void removeProductFromCartViaJS(Integer productId){
        new JSExecutor().GETRequest("/ajax/cart/remove/"+String.valueOf(productId)+"?ajax=on&pageType=cart");
    }

}
