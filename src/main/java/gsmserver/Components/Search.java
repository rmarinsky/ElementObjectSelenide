package gsmserver.Components;

import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class Search {

    @Step("Search for: [{0}]")
    public ProductList searchFor(String q){
        $(byName("q")).setValue(q);
        $("a.search-input-submit").submit();
        return new ProductList();
    }

}
