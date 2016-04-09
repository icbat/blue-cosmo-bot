import org.jibble.pircbot.*;
import java.util.regex.*;
import java.util.Scanner;
import java.io.*;

public class BCB_LRR extends PircBot {
    String regex = "kappa|keepo";
    Pattern p = Pattern.compile(regex);
    FileManager fm = new FileManager();

    public BCB_LRR() {
        setName("BlueCosmo_Bot");
        setLogin("BlueCosmo_Bot");
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        message = message.toLowerCase();
        Matcher kMatcher = p.matcher(message);

        if(kMatcher.find()) {
            fm.incKappa();
        }

        String[] array = message.split(" ");

        switch(array[0]) {
            case "!kappa":
                sendMessage(channel, ("Session Kappa count: " + fm.getSessionKappa() + " || Lifetime Kappa count: " + fm.getTotalKappa()));
                break;
            default:
                break;
        }
    }
}