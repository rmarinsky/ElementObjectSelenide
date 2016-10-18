package gsmserver.Components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class Search {

    private SelenideElement dropDownCategories = $("div.search-facet-selector"),
                            fieldSearch = $(byName("q")),
                            submitSearch = $("a.search-input-submit");

    @Step("Search for: [{0}]")
    public ProductList searchFor(String q){
        fieldSearch.setValue(q);
        submitSearch.click();
        return new ProductList();
    }

    @Step("Search query: [{0}], for category: [{1}]")
    private void searchByCategory(String searchQuery, SelenideElement element){
        this.fieldSearch.clear();
        this.dropDownCategories.$("#search_facet_selector").click();
        element.click();
        element.shouldBe(Condition.visible);
        this.searchFor(searchQuery);
        $("span.search-title-highlight").shouldHave(Condition.text(searchQuery));
        $("span[style='background-color: #ffee33']").shouldBe(Condition.visible);
    }

    public Search checkSearchByCategories(){
        List<RequestByCategory> requestsAndCategories =
                Arrays.asList(
                        new RequestByCategory("smart-clip", dropDownCategories.$("[data-name='gsm']")),
                        new RequestByCategory("lcd for lenovo", dropDownCategories.$("[data-name='cellphones']")),
                        new RequestByCategory("digital multimeter", dropDownCategories.$("[data-name='equipment']")),
                        new RequestByCategory("volkswagen", dropDownCategories.$("[data-name='cars']")),
                        new RequestByCategory("touchscreen nexus 7", dropDownCategories.$("[data-name='spares']")),
                        new RequestByCategory("puzzle", dropDownCategories.$("[data-name='toys']")),
                        new RequestByCategory("led lamp", dropDownCategories.$("[data-name='other']"))
                );
        for(RequestByCategory requestByCategory : requestsAndCategories){
            searchByCategory(requestByCategory.getRequest(), requestByCategory.getCategoryLocator());
        }
        return this;
    }

    private class RequestByCategory {
        String request;
        SelenideElement categoryLocator;

        RequestByCategory(String request, SelenideElement categoryLocator){
            this.request = request;
            this.categoryLocator = categoryLocator;
        }

        String getRequest() {
            return request;
        }
        SelenideElement getCategoryLocator() {
            return categoryLocator;
        }
    }

}
