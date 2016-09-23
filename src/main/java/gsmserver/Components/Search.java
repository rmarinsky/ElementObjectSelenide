package gsmserver.Components;

import com.codeborne.selenide.SelenideElement;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class Search {

    private SelenideElement fieldSearch = $(byName("q"));
    private SelenideElement submitSearch = $("a.search-input-submit");

    @Step
    public ProductList searchFor(String q){
        this.fieldSearch.val(q);
        this.submitSearch.submit();
        return new ProductList(q);
    }

}
