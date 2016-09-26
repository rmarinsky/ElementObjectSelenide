package gsmserver;


import gsmserver.Components.Search;
import gsmserver.Utils.BaseTest;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;
import static com.codeborne.selenide.Selenide.open;


@Description(value = "Tests for searching on site")
public class SearchTests extends BaseTest {

    private String query = "smart-clip";

    @Before public void before(){
        open("");
    }

    @Title(value = "Test for search")
    @Test public void searchTest(){
        new Search().searchFor(query).
                searchResultShouldHasSearchedPhrase();
    }

}
