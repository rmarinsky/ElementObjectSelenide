package gsmserver.Utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Random {

    public static String generateRandomString(){
        return "TEST"+RandomStringUtils.random(8, "abcdefghjklmnopqrstuvwxyz");
    }

    public static String generateRandomString(String firstPart){
        return firstPart+RandomStringUtils.random(8, "abcdefghjklmnopqrstuvwxyz");
    }

    public static String generateRandomEmail(){
        return generateRandomString()+"@"+generateRandomString()+".com";
    }

}
