package UserCommands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.Set;

public class HelpCommand implements UserCommand {

    Set<String> commandList;

    public HelpCommand(Set<String> commandList) { this.commandList = commandList; }

    @Override
    public void handleCommand(MessageReceivedEvent event, String messageContent) {
        event.getChannel().sendMessage("To interact with me, try typing \"Gary,\" followed by any of the following commands: " + commandList).queue();
    }

}
