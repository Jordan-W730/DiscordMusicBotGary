package AudioManagment;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.entities.Guild;

public class AudioHandler {

    // AudioClass attributes
    final private AudioPlayerManager playerManager;
    final private AudioPlayer player;
    final private TrackScheduler trackScheduler;

    // Constructor
    public AudioHandler(Guild guild) {

        // Initialize attributes
        this.playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(this.playerManager);
        this.player = playerManager.createPlayer();
        this.trackScheduler = new TrackScheduler(this.player);
        player.addListener(this.trackScheduler);

        // Set the AudioPlayerSendHandler to the AudioManager
        net.dv8tion.jda.api.managers.AudioManager audioManager = guild.getAudioManager();
        audioManager.setSendingHandler(new AudioPlayerSendHandler(player));

        System.out.println("AudioHandler: " + this.player);
        System.out.println("AudioHandler: " + this.playerManager);
        System.out.println("AudioHandler: " + this.trackScheduler);

    }

    // getters
    public AudioPlayer getPlayer() { return this.player; }

    public AudioPlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public TrackScheduler getTrackScheduler() {
        return trackScheduler;
    }


}
