package com.tempmail;

import com.tempmail.mailutil.Email;
import com.tempmail.mailutil.HttpRequestTempMail;
import gsmserver.Pages.ConfirmationPage;
import gsmserver.Utils.HTMLLinkExtractor;
import gsmserver.Utils.HtmlLink;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.yandex.qatools.allure.annotations.Step;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Tester3 on 19.10.2016.
 */
public class TempMailApiClient {
    public static final String DELETE_MESSAGE_LINK = "http://api.temp-mail.ru/request/delete/id/md5/",
            VIEW_MESSAGE_LINK = "http://api.temp-mail.ru/request/mail/id/md5/",
            DOMAIN_LIST_LINK = "http://api.temp-mail.ru/request/domains";

    /**
     * @return Returns a list of all the emails in the inbox of the address.
     */
    @Step
    public static List<Email> getEmails(final String md5hash)
            throws IOException, ParserConfigurationException, SAXException {
        if (md5hash == null || md5hash.length() != 32)
            throw new IllegalArgumentException("Argument provided not a md5 hash!");
        final ArrayList<Email> EMAIL_LIST = new ArrayList<Email>();
        final HttpRequestTempMail HTTP_REQUEST = new HttpRequestTempMail(VIEW_MESSAGE_LINK.replace("md5", md5hash));
        // must accept xml else server will reject request :)
        HTTP_REQUEST.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        try {
            HTTP_REQUEST.execute();
        } catch (FileNotFoundException fnfe) {
            // fnfe.printStackTrace();
            return EMAIL_LIST;
        }
        System.out.println(VIEW_MESSAGE_LINK.replace("md5", md5hash));

        final String OUTPUT = HTTP_REQUEST.getOutput();// our input
        final DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();
        final DocumentBuilder BUILDER = FACTORY.newDocumentBuilder();
        final ByteArrayInputStream XML_INPUT = new ByteArrayInputStream(OUTPUT.getBytes("UTF-8"));
        final Document document = BUILDER.parse(XML_INPUT);

        final NodeList NODE_LIST = document.getElementsByTagName("item");

        for (int index = 0; index < NODE_LIST.getLength(); index++) {
            final Element EMAIL_NODE = (Element) NODE_LIST.item(index);
            final Email EMAIL = new Email();
            final NodeList EMAIL_ELEMENT_LIST = EMAIL_NODE.getChildNodes();
            // was going to use reflection to get fields from the email class
            // and compare then set their value but performance loss to great.
            for (int i = 0; i < EMAIL_ELEMENT_LIST.getLength(); i++) {
                final Node node = EMAIL_ELEMENT_LIST.item(i);
                final String NODE_NAME = EMAIL_ELEMENT_LIST.item(i).getNodeName();
                switch (NODE_NAME) {

                    case "mail_id":
                        EMAIL.setMailID(node.getTextContent());
                        break;

                    case "mail_address_id":
                        EMAIL.setMailAddressID(node.getTextContent());
                        break;

                    case "mail_from":
                        EMAIL.setMailFrom(node.getTextContent());
                        break;

                    case "mail_subject":
                        EMAIL.setMailSubject(node.getTextContent());
                        break;

                    case "mail_preview":
                        EMAIL.setMailPreview(node.getTextContent());
                        break;

                    case "mail_text_only":
                        EMAIL.setMailTextOnly(node.getTextContent());
                        break;

                    case "mail_text":
                        EMAIL.setMailText(node.getTextContent());
                        break;

                    case "mail_html":
                        EMAIL.setMailHtml(node.getTextContent());
                        break;

                    case "mail_timestamp":
                        EMAIL.setMailTimestamp(Double.parseDouble(node.getTextContent()));
                        break;

                    default:// unknown field
                        break;
                }
            }
            if (EMAIL.getMailID() != null)
                EMAIL_LIST.add(EMAIL);
        }
        return EMAIL_LIST;
    }

    @Step
    public static void deleteEmail(final String md5hash) throws IOException {
        if (md5hash == null || md5hash.length() != 32)
            throw new IllegalArgumentException("Argument provided not a md5 hash!");
        final HttpRequestTempMail HR = new HttpRequestTempMail(DELETE_MESSAGE_LINK.replace("md5", md5hash));
        HR.execute();

    }

    @Step
    public static String getMD5Hash(final String input) throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance("MD5");
        final byte[] bytes = md.digest(input.getBytes());
        String result = "";
        for (int i = 0; i < bytes.length; ++i) {
            result += Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1, 3);
        }
        return result;
    }

    /**
     * @return Returns a list of all the valid domain names for emails on the
     * temp-mail service.
     */
    @Step
    public static List<String> getDomains() throws IOException, ParserConfigurationException, SAXException {
        final ArrayList<String> LIST = new ArrayList<String>();
        final HttpRequestTempMail HR = new HttpRequestTempMail(DOMAIN_LIST_LINK);
        HR.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        HR.execute();
        final String OUTPUT = HR.getOutput();// our input
        final DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();
        final DocumentBuilder BUILDER = FACTORY.newDocumentBuilder();
        final ByteArrayInputStream XML_INPUT = new ByteArrayInputStream(OUTPUT.getBytes("UTF-8"));
        final Document document = BUILDER.parse(XML_INPUT);
        final NodeList NODE_LIST = document.getElementsByTagName("item");
        for (int index = 0; index < NODE_LIST.getLength(); index++) {
            final Node node = NODE_LIST.item(index);
            LIST.add(node.getTextContent());
        }
        return LIST;
    }


    private static String tempEmail;
    //list emails
    private static List<Email> _listEmails = new ArrayList<>();

    /**
     * Create user by String @Name
     * check avialible hosts
     *
     * @param Name
     * @return Email Adress that we should have in tempemail
     * can use optional : 1. get from this method email adress or from getTempEmailApi
     * mb could return void
     */

    @Step
    public static String createTempMailAccountByName(String Name) throws ParserConfigurationException, SAXException, IOException {
        List<String> _listDomain = getDomains();
        if (_listDomain.size() <= 0)
            throw new AssertionError("We cann't dooing this test because no avialible domains");
        String emailAdress = (new StringBuffer(Name).append(_listDomain.get(1))).toString();
        System.out.println("Our emailadress in host temp-mail is : " + emailAdress);

        tempEmail = emailAdress;
        return emailAdress;
    }

    /**
     * Create user by String @Name  from availible host
     *
     * @return Email Adress that we  have got in tempemail
     */

    @Step
    public static String getTempEmailApi() {

        return tempEmail;
    }

    /**
     * you can set how many letter you should wait
     * but no longer than 3 minuts,  otherwise exception letter not found or resourse dont work
     *
     * @param numb
     * @return Email Adress that we should have in tempemail
     * can use optional : 1. get from this method email adress or from getTempEmailApi
     * mb could return void
     */

    @Step
    public static void waitLettersByNumb(int numb) throws InterruptedException {
        long _threeMinuts = 3 * 60000;
        long _counter = 1000;
        int listmessage = 0;
        try {
            while (numb > listmessage) {
                Thread.currentThread().sleep(3000);
                _counter = _counter + 1000;
                if (_threeMinuts == _counter) {
                    throw new InterruptedException("After : " + _threeMinuts / 60000 + " minuts letters don't appear in mail box");
                }

                listmessage = TempMailApiClient.getEmails(TempMailApiClient.getMD5Hash(getTempEmailApi())).size();
            }
            _listEmails = TempMailApiClient.getEmails(TempMailApiClient.getMD5Hash(getTempEmailApi()));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    @Step
    public TempMailApiClient waitFirstLetter() throws InterruptedException {

        waitLettersByNumb(1);

        return this;

    }

    @Step
    public TempMailApiClient waitSecondLetter() throws InterruptedException {

        waitLettersByNumb(2);

        return this;
    }


    //get links from letter by number and by pattern
    @Step
    public static String getUrlFromLetter(int numberLetter, String pattern) {

      /*  String text = _listEmails.get(numberLetter).getMailTextOnly().replaceAll("\\s+", " ");
        String[] animals = text.split(" ");
        for (String str : animals) {
            if (str.contains(pattern)) {
                System.out.println("string substring" + str.substring(6));
            }
        }*/


        Vector<HtmlLink> links = new HTMLLinkExtractor().grabHTMLLinks(_listEmails.get(numberLetter).getMailTextOnly());
        String link = "";
        for (int i = 0; i < links.size(); i++) {
            HtmlLink htmlLinks = links.get(i);
            if (htmlLinks.getLink().contains(pattern)) {
                link = htmlLinks.getLink();
                break;
            }
        }
        return link;
    }

    @Step
    public ConfirmationPage confirmRegistrationByApi(String url) {
        return openPageWhenURLAsseptable(url);
    }

    @Step
    public static String getUrlFromFirstLetter() {
        int _numbLetter = 1;
        String pattern = "http://gsmserver.com/user/signup/";
        return getUrlFromLetter(_numbLetter - 1, pattern);
    }

    @Step
    public ConfirmationPage confirmPasswordRecoveryByApi(String urlFromSecondLetter) {
        return openPageWhenURLAsseptable(urlFromSecondLetter);
    }

    @Step
    public ConfirmationPage openPageWhenURLAsseptable(String url) {
        System.out.println(url);
        open(url);
        return new ConfirmationPage();
    }

    @Step
    public static String getUrlFromSecondLetter() {
        int _numbLetter = 2;
        String pattern = "http://gsmserver.com/account/changepassword";
        return getUrlFromLetter(_numbLetter - 1, pattern);
    }
}
