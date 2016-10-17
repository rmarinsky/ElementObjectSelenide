package gsmserver.Components;

import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class Search {

    @Step
    public ProductList searchFor(String q){
        $(byName("q")).val(q);
        $("a.search-input-submit").submit();
        return new ProductList(q);
    }

}
