import org.jibble.pircbot.*;
import java.util.Scanner;
import java.io.*;

public class BotMain {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter channel to try connecting to: ");
        String channel = scan.nextLine();
        if (channel.equals("loadingreadyrun")) {
            BCB_LRR bot = new BCB_LRR();
            bot.setVerbose(true);

            bot.connect("irc.chat.twitch.tv", 6667, "oauth:ld5r77jv247ed26j6v9c8a8a2y2b1j");
            bot.joinChannel("#" + channel);
        } else {
            BlueCosmo_Bot bot = new BlueCosmo_Bot();
            bot.setVerbose(true);

            bot.connect("irc.chat.twitch.tv", 6667, "oauth:ld5r77jv247ed26j6v9c8a8a2y2b1j");
            bot.joinChannel("#" + channel);
        }
    }
}