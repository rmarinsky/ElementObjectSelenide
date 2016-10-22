package gsmserver.Components;

import gsmserver.Pages.ComparePage;

import static com.codeborne.selenide.Selenide.$;

public class RightFixedButtons {

    public ComparePage openComparePage(){
        $("#r-buttons a.icon.compare-icon").hover();
        $("#r-buttons [href^='/product/compare/?ids']").click();
        return new ComparePage();
    }

}
