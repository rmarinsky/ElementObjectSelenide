package gsmserver.Components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import gsmserver.Utils.JSExecutor;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Product {

    public final static ElementsCollection addToCartButtons = $$("a.ga-add-to-cart");
    public final static SelenideElement inputQuantity = $("div.quantity-input");

    static SelenideElement findProduct(final Integer id, final String tagName){
        return $(String.format("%s[data-product-id='%s']", tagName, id));
    }

    @Step
    public void addProductToCartViaJs(final Integer productId, Integer count){
        new JSExecutor().GETRequest(String.format("/ajax/cart/add/%s,%s", productId, count));
    }

    public void addProductsToCart(Integer... productIds){
        for(Integer id : productIds) {
            this.addProductToCartViaJs(id, 1);
        }
    }

}
