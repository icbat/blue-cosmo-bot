//instantiated in BotMain.java, extends pircbot
//http://www.jibble.org/javadocs/pircbot/index.html <- additional/modifiable/overloadable functions from pircbot

import org.jibble.pircbot.*;
import java.util.regex.*;

public class BlueCosmo_Bot extends PircBot {
    boolean giveawayFlag = false;
    String regex = "Kappa|Keepo";
    String regex2 = "\\b\\w+\\.\\w{2,4}\\b";
    Pattern p = Pattern.compile(regex);
    Pattern b = Pattern.compile(regex2);
    FileManager fm = new FileManager();
    String quote;
    long cooldown = 0;

    public BlueCosmo_Bot() {
        setName("BlueCosmo_Bot");
        setLogin("BlueCosmo_Bot");
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        Matcher kMatcher = p.matcher(message);
        Matcher bMatcher = b.matcher(message);

        if(bMatcher.find()) {

        }

        if(kMatcher.find()) {
            fm.incKappa();
        }

        String[] array = message.split(" ");

        switch(array[0]) {
            case "!raid":
                if((System.currentTimeMillis()/1000 >= cooldown+7)) {
                    sendMessage(channel, fm.getRaid());
                    cooldown = System.currentTimeMillis()/1000;
                }
                break;
            case "!dutch":
                if((System.currentTimeMillis()/1000 >= cooldown+7)) {
                    fm.incDutch();
                    sendMessage(channel, ("THE DUTCH! Lifetime Dutch count: " + fm.getDutch()));
                    cooldown = System.currentTimeMillis()/1000;
                }
                break;
            case "!dutchcount":
                if((System.currentTimeMillis()/1000 >= cooldown+7)) {
                    sendMessage(channel, ("Lifetime Dutch count: " + fm.getDutch()));
                    cooldown = System.currentTimeMillis()/1000;
                }
                break;
            case "!kappa":
                if((System.currentTimeMillis()/1000 >= cooldown+7)) {
                    sendMessage(channel, ("Session Kappa count: " + fm.getSessionKappa() + " || Lifetime Kappa count: " + fm.getTotalKappa()));
                    cooldown = System.currentTimeMillis()/1000;
                }
                break;
            case "!modtest":
                if((System.currentTimeMillis()/1000 >= cooldown+7)) {
                    if (fm.isMod(sender))
                        sendMessage(channel, "ur a mod");
                    else
                        sendMessage(channel, "ur not a mod");
                    cooldown = System.currentTimeMillis()/1000;
                }
                break;
            case "!giveaway":
                if((System.currentTimeMillis()/1000 >= cooldown+7)) {
                    if (fm.isMod(sender)) {
                        sendMessage(channel, "A giveaway has begun! Type !enter to be entered into the drawing.");
                        giveawayFlag = true;
                    }
                    else {
                        sendMessage(channel, sender + ": You do not have permission for this command.");
                    }
                    cooldown = System.currentTimeMillis()/1000;
                }
                break;
            case "!enter":
                if (giveawayFlag) {
                    fm.enter(sender);
                }
                else {
                    sendMessage(channel, sender + ": There's no giveaway right now!");
                }
                break;
            case "!winner":
                if((System.currentTimeMillis()/1000 >= cooldown+7)) {
                    if (fm.isMod(sender)) {
                        String winner = fm.winner();
                        sendMessage(channel, "And the winner is...");
                        sendMessage(channel, (winner + " has won the giveaway!!! Kappa //"));
                        giveawayFlag = false;
                    }
                    else {
                        sendMessage(channel, sender + ": You do not have permission for this command.");
                    }
                    cooldown = System.currentTimeMillis()/1000;
                }
                break;
            case "!quote":
                if((System.currentTimeMillis()/1000 >= cooldown+7)) {
                    if(array.length > 1)
                        quote = fm.getQuote(Integer.parseInt(array[1])-1);
                    else
                        quote = fm.getQuote();
                    sendMessage(channel, ("\"" + quote + "\""));
                    cooldown = System.currentTimeMillis()/1000;
                }
                break;
            case "!addquote":
                if((System.currentTimeMillis()/1000 >= cooldown+7)) {
                    if (fm.isMod(sender)) {
                        quote = array[1];
                        for(int x = 2; x < array.length; x++) {
                            quote += (" " + array[x]);
                        }
                        fm.addQuote(quote);
                        sendMessage(channel, ("Quote added. #" + fm.getQuoteCount()));
                    }
                    else {
                        sendMessage(channel, sender + ": You do not have permission for this command.");
                    }
                    cooldown = System.currentTimeMillis()/1000;
                }
                break;
            default:
                break;
        }
    }
}