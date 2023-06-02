package UserCommands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface UserCommand {

    // Upon receiving a command, we call a general "handleCommand" statement
    // This provides an abstract view of the inner workings of the bot
    // This also ensures that adding new commands won't require adding new logic elsewhere
    // By having every UserCommand inherit this interface, we ensure they implement handleCommand
    // Each command will have a different implementation of handleCommand

    void handleCommand(MessageReceivedEvent event, String messageContent);

}
