package gsmserver.Components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

class ProductWrapper {

    public final static ElementsCollection addToCartButtons = $$("a.ga-add-to-cart");
    public final static SelenideElement inputQuantity = $("div.quantity-input");


    static SelenideElement findProduct(final Integer id, final String tagName){
        return $(String.format("%s[data-product-id='%s']", tagName, id));
    }

}
