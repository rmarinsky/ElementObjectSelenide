package com.gsmserver;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProductListBlock {

    private SelenideElement searchTitle = $(".search-title-highlight");

    public void searchResultShouldHasPhrase(String phrase){
        searchTitle.shouldHave(Condition.text(phrase));
    }

}
