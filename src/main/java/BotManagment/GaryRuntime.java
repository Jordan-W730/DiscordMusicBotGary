package BotManagment;

public class GaryRuntime {

    // Attributes
    final private MusicBot gary;

    // Constructor
    private GaryRuntime() throws InterruptedException {
        gary = new MusicBot();
    }

    // Main method to run program
    public static void main(String[] args) {
        // attempts to make a new runtime of the bot
        try {
            GaryRuntime garyInstance = new GaryRuntime();
        //prints the stacktrace if given an error
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
