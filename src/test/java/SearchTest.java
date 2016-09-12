import com.gsmserver.SearchBlock;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;


public class SearchTest {

    private String query = "smart-clip";

    @Before public void before(){
        open("http://gsmserver.com/");
    }

    @Test public void first(){
        new SearchBlock().searchFor(query).
                searchResultShouldHasPhrase();
    }

}
