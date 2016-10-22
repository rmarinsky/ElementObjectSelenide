package gsmserver.Components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import gsmserver.Utils.JSExecutor;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class Product {

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

    @Step("Add to cart product: [{0}] in count: [{1}]")
    public void addProductToCartViaJs(final Integer productId, Integer count){
        new JSExecutor().GETRequest(String.format("/ajax/cart/add/%s,%s", productId, count));
    }

    public void addProductSToCartViaJs(final Integer... productId){
        for(Integer id : productId) {
            new JSExecutor().GETRequest(String.format("/ajax/cart/add/%s,1", id));
        }
    }

}
