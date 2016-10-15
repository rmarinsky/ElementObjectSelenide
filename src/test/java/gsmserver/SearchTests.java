package gsmserver;


import gsmserver.Components.Search;
import gsmserver.Utils.BaseTest;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;


@Description(value = "Tests for searching on site")
public class SearchTests extends BaseTest {

    @Title(value = "Test for search")
    @Test public void searchTest(){
        new Search().searchFor("smart-clip").
                searchResultShouldHasSearchedPhrase();
    }

}
