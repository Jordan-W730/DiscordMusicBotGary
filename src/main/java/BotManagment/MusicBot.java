package BotManagment;

import AudioManagment.AudioHandler;
import MessageHandling.CommandHandler;
import MessageHandling.MessageReader;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;

public class MusicBot extends ListenerAdapter {

    // Attributes that we only need one instance of per music bot
    MessageReader messageReader;
    CommandHandler commandHandler;
    AudioHandler audioHandler;
    Guild yourGuild;

    public MusicBot() throws InterruptedException {

        // INSERT YOUR BOT TOKEN HERE
        JDABuilder builder = JDABuilder.createDefault("MTEwMDg0OTAyOTQ3NjI2MjAxMA.G3aQ2Y.g7eKjRcXX0jJ1V6ouVvcSrEmwBMpusknCWlu6w");

        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT);
        builder.addEventListeners(this);

        // get the JDA instance
        JDA jda = builder.build().awaitReady();

        // Shutdown hook to clean up resources
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // disconnect Gary from any voice channels he's in and shutdown JDA
            if (jda.getGuilds().size() > 0) {
                jda.getGuilds().forEach(guild -> guild.getAudioManager().closeAudioConnection());
            }
            jda.shutdownNow();
        }));

        // Set Gary's status to being online
        jda.getPresence().setStatus(OnlineStatus.ONLINE);

        // Set Gary's current activity
        jda.getPresence().setActivity(Activity.watching("type \"Gary, help\" for commands."));

        // create a MessageReader
        this.messageReader = new MessageReader();

        // INPUT YOUR SERVERS ID HERE
        this.yourGuild = jda.getGuildById("1100952687983480872");

        // create an audioHandler
        this.audioHandler = new AudioHandler(yourGuild);

        // create a CommandHandler
        this.commandHandler = new CommandHandler(yourGuild, audioHandler);

        // verify that an instance of each attribute has been initialized
        System.out.println(this.messageReader.toString() + " " + this.commandHandler.toString());

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        // stop Gary from responding to other bots
        if (event.getAuthor().isBot()) return;

        // String to store message information
        String[] commandAndContent;
        String messageCommand;
        String messageContent;

        // if the sender was not a bot, read the message
        commandAndContent = this.messageReader.readMessage(event);
        messageCommand = commandAndContent[0].toUpperCase();
        messageContent = commandAndContent[1];

        // check if the given command exists then handle it, inform user if it does not
        if(this.commandHandler.hasCommand(messageCommand)) {
            this.commandHandler.handleCommand(messageCommand, event, messageContent);
        } else {
            event.getChannel().sendMessage("I'm sorry " + messageCommand + " is not a valid command. " +
                    "If you would like a list of available commands, please type \"Gary, help\".").queue();
        }

    }

    public AudioHandler getAudioHandler() { return this.audioHandler; }

}
