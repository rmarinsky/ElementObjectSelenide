package gsmserver.Components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

class ProductWrapper {

    static SelenideElement findProduct(final Integer id, final String tagName){
        return $(String.format("%s[data-product-id='%s']", tagName, id));
    }

}
