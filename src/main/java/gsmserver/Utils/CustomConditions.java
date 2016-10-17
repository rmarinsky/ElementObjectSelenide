package gsmserver.Utils;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;

public final class CustomConditions {

    public static Condition classError() {
        return Condition.cssClass("error");
    }

    public static Condition classSuccess(){
        return Condition.cssClass("success");
    }

    public static Condition cannotBeBlankTitleTip() {
        return Condition.attribute("title", "This field cannot be blank");
    }

    public static Condition originalTitleTip(final String text) {
        return Condition.attribute("title", text);
    }

    private static Condition labelRequired(){
        return Condition.and("have '*'", Condition.text("*"), attributeRequired());
    }
    private static Condition attributeRequired(){
        return Condition.or("class 'required' or data-validator='required'", Condition.cssClass("required"), Condition.attribute("data-validator","required"));
    }

    @Step
    public static void signaturesOfFieldsHaveRequiredLabel(By... bys){
        for(By by : bys) {
            $(by).parent().has(labelRequired());
        }
    }
    @Step
    public static void signaturesOfFieldsHaveNoRequiredLabel(By... bys){
        for(By by : bys) {
            $(by).parent().shouldNotHave(labelRequired());
        }
    }

}
