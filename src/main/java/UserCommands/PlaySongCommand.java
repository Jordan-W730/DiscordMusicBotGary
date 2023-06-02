package UserCommands;

import AudioManagment.AudioHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class PlaySongCommand extends ListenerAdapter implements UserCommand {

    // Attribute
    private AudioHandler audioHandler;
    private Guild yourGuild;
    private AudioManager audioManager; //****

    // 1 - have gary join the vc of the user that input the command
    private boolean joinVC(MessageReceivedEvent event, AudioManager audioManager) {

        // find the user that sent the message
        Member commandingUser = event.getMember();
        AudioChannel voiceChannel = commandingUser.getVoiceState().getChannel();

        // check that the user is in a voice channel
        if(voiceChannel != null) {

            // if the user is in a voice chat different from the one Gary is in, he will join it
            if (audioManager.getConnectedChannel() != voiceChannel) {
                audioManager.openAudioConnection(voiceChannel);
            }

            // if gary is already in the users voice channel, return true and do nothing
            return true;
        }

        return false;

    }

    // 2 - have gary play desired song
    private void findSong(MessageReceivedEvent event, String messageContent) {

        // attempts to load a new song
        audioHandler.getPlayerManager().loadItem(messageContent, new AudioLoadResultHandler() {

            @Override
            // if the song is loaded successfully, call queueTrack
            public void trackLoaded(AudioTrack track) {
                System.out.println("track loaded");
                audioHandler.getTrackScheduler().queueTrack(event, track, messageContent);
            }

            @Override
            // if a playlist is successfully added, queue all the tracks
            public void playlistLoaded(AudioPlaylist playlist) {
                for (AudioTrack track : playlist.getTracks()) {
                    audioHandler.getTrackScheduler().queue(track);
                }
                System.out.println("playlist loaded");
            }

            @Override
            // print an error message for if no matches were found matching the query
            public void noMatches() {
                System.out.println("no matches");
            }

            @Override
            // print an error message if the attempt to load the track failed
            public void loadFailed(FriendlyException throwable) {
                System.out.println("load failed");
            }

        });

    }

    // 3 - handleCommand by: Joining VC then Playing Song
    @Override
    public void handleCommand(MessageReceivedEvent event, String messageContent) {

        // checks that gary is in/joined a vc
        if(this.joinVC(event, this.audioManager)) {
            this.findSong(event, messageContent);

        // informs user to join a vc if they try to call this command without being in one
        } else {
            event.getChannel().sendMessage("Please join a voice channel to use the PLAY command.").queue();
        }

    }

    // PlaySong constructor
    public PlaySongCommand(Guild guild, AudioHandler audioHandler) {

        this.audioHandler = audioHandler;
        this.yourGuild = guild;
        this.audioManager = guild.getAudioManager();

    }


}
