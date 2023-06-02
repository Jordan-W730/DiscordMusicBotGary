package UserCommands;

import AudioManagment.TrackScheduler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class StopCommand implements UserCommand {

    //
    Guild yourGuild;
    TrackScheduler trackScheduler;

    @Override
    public void handleCommand(MessageReceivedEvent event, String messageContent) {

        // verify user is in the same connected channel as the audio bot
        if(event.getMember().getVoiceState().getChannel().equals(this.yourGuild.getAudioManager().getConnectedChannel())) {

            // end current track
            this.trackScheduler.endTrack();

        }

    }

    public StopCommand(Guild yourGuild, TrackScheduler trackScheduler) {
        this.yourGuild = yourGuild;
        this.trackScheduler = trackScheduler;
    }

}
