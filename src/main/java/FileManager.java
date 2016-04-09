import java.io.*;
import java.util.*;

public class FileManager {
    List<String> giveList;
    List<String> quoteList;
    int sessionKappa;
    int totalKappa;
    int dutchCount;
    Random rand = new Random();

    public FileManager() {
        giveList = new ArrayList<String>();
        String line = null;
        String [] array;
        sessionKappa = 0;

        try {
            BufferedReader stats = new BufferedReader(new FileReader("seabatsStats.txt"));
            line = stats.readLine();
            array = line.split(" ");
            totalKappa = Integer.parseInt(array[1]);
            line = stats.readLine();
            array = line.split(" ");
            dutchCount = Integer.parseInt(array[1]);
            stats.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("unable to open file");
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        try {
            line = null;
            BufferedReader quotes = new BufferedReader(new FileReader("seabatsQuotes.txt"));
            quoteList = new ArrayList<String>();
            while((line = quotes.readLine()) != null) {
                quoteList.add(line);
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("unable to open file");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public void incKappa() {
        sessionKappa++;
        totalKappa++;
        System.out.println("total kappa is now: " + totalKappa + "\nsession kappa is now: " + sessionKappa);
        try {
            BufferedWriter stats = new BufferedWriter(new FileWriter("seabatsStats.txt"));
            stats.write("Kappa " + totalKappa);
            stats.newLine();
            stats.write("Dutch " + dutchCount);
            stats.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getRaid() {
        String line = null;
        int num = rand.nextInt(25);
        System.out.println("attempting to read line " + num);
        try {
            BufferedReader onyx = new BufferedReader(new FileReader("seabatsRaid.txt"));
            for(int x = 0; x <= num; x++) {
                line = onyx.readLine();
            }
            onyx.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("unable to open file");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return line;
    }

    public void incDutch() {
        dutchCount++;
        try {
            BufferedWriter stats = new BufferedWriter(new FileWriter("seabatsStats.txt"));
            stats.write("Kappa " + totalKappa);
            stats.newLine();
            stats.write("Dutch " + dutchCount);
            stats.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getDutch() {
        return dutchCount;
    }

    public int getTotalKappa() {
        return totalKappa;
    }

    public int getSessionKappa() {
        return sessionKappa;
    }

    public boolean isMod(String user) {
        user = user.toLowerCase();
        String line = null;
        boolean ret = false;
        try {
            BufferedReader mods = new BufferedReader(new FileReader("seabatsMods.txt"));
            List<String> list = new ArrayList<String>();
            while((line = mods.readLine()) != null) {
                list.add(line);
            }
            for (String item : list) {
                if(item.equals(user))
                    ret = true;
            }
            mods.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("unable to open file");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    public void enter(String user) {
        boolean ok = true;
        for(String item : giveList) {
            if (item.equals(user)) {
                ok = false;
            }
        }
        if (ok) {
            giveList.add(user);
            for(String item : giveList) {
                System.out.println(item);
            }
        }
    }

    public String winner() {
        int num = rand.nextInt(giveList.size());
        String user = giveList.get(num);
        giveList.clear();
        return user;
    }

    public String getQuote() {
        int num = rand.nextInt(quoteList.size());
        String quote = quoteList.get(num);
        return quote;
    }

    public String getQuote(int num) {
        String quote;
        try {
            quote = quoteList.get(num);
        }
        catch (IndexOutOfBoundsException ex) {
            quote = "Error: Given value is out of bounds.";
        }
        return quote;
    }

    public void addQuote(String quote) {
        quoteList.add(quote);
        try {
            BufferedWriter quoteWriter = new BufferedWriter(new FileWriter("seabatsQuotes.txt."));
            for (String item : quoteList) {
                quoteWriter.write(item);
                quoteWriter.newLine();
            }
            quoteWriter.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("unable to open file");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public int getQuoteCount() {
        return quoteList.size();
    }
}