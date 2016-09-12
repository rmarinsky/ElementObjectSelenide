package com.gsmserver;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProductListBlock {

    private String searchedPhrase;

    public ProductListBlock(){}

    public ProductListBlock(String searchedPhrase){
        this.searchedPhrase = searchedPhrase;
    }

    private SelenideElement searchTitle = $(".search-title-highlight");

    public void searchResultShouldHasPhrase(){
        searchTitle.shouldHave(Condition.text(searchedPhrase));
    }

}
