package MessageHandling;

import AudioManagment.AudioHandler;
import UserCommands.HelpCommand;
import UserCommands.PlaySongCommand;
import UserCommands.StopCommand;
import UserCommands.UserCommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;

public class CommandHandler {

    // this hash map stores string commands and the class that handles that command
    private HashMap<String, UserCommand> commandsList;

    public CommandHandler(Guild yourGuild, AudioHandler audioHandler) {

        this.commandsList = new HashMap<String, UserCommand>();

        commandsList.put("PLAY" , new PlaySongCommand(yourGuild, audioHandler));
        //commandsList.put("PAUSE", new pauseSongCommand());
        //commandsList.put("UNPAUSE", new unpauseSongCommand());
        commandsList.put("STOP", new StopCommand(yourGuild, audioHandler.getTrackScheduler()));
        commandsList.put("HELP", new HelpCommand(this.commandsList.keySet()));
    }

    // returns true if a given command exists, false otherwise
    public boolean hasCommand(String command) {
        if (this.commandsList.containsKey(command)) return true;
        return false;
    }

    // broadly speaking, this 'handles' commands by passing the work onto the commands respective class
    public void handleCommand(String command, MessageReceivedEvent event, String messageContent) {
        this.commandsList.get(command).handleCommand(event, messageContent);
    }

}
