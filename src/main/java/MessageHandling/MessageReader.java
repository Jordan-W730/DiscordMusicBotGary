package MessageHandling;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class MessageReader {

    public String[] readMessage(@NotNull MessageReceivedEvent event) {

        // Read messages and convert them to uppercase for convenience
        String message = event.getMessage().getContentRaw();

        // check if a message was addressed to Gary
        if (message.substring(0, 5).toUpperCase().startsWith("GARY,")) {

            // remove "GARY," prefix from message
            message = message.substring(5).trim();

            // makes sure the message is not empty
            if (!message.isEmpty()) {

                // Attributes to hold the command given and the text that followed
                String messageCommand;
                String messageContent;

                // splits the string into individual words and checks the first word; that should be the command
                messageCommand = message.split(" ")[0];

                // removes the command and saves remaining text as the message content
                messageContent = message.substring(messageCommand.length()).trim();

                // returns the command and content
                return new String[] {messageCommand, messageContent};

            }
        }
        return null;
    }

}
