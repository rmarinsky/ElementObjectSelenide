package com.gsmserver;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class SearchBlock {

    private SelenideElement fieldSearch = $(byName("q"));
    private SelenideElement submitSearch = $("a.search-input-submit");

    public ProductListBlock searchFor(String q){
        fieldSearch.append(q);
        submitSearch.pressEnter();
        return new ProductListBlock();
    }

}
