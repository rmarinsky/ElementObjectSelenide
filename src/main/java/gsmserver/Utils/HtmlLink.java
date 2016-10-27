package gsmserver.Utils;

/**
 * Created by Tester3 on 20.10.2016.
 */
public class HtmlLink {

    String link;
    String linkText;



    @Override
    public String toString() {
        return new StringBuffer("Link : ").append(this.link)
                .append(" Link Text : ").append(this.linkText).toString();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = replaceInvalidChar(link);
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    private String replaceInvalidChar(String link) {
        link = link.replaceAll("'", "");
        link = link.replaceAll("\"", "");
        return link;
    }


}
