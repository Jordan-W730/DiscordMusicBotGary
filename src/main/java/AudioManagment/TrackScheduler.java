package AudioManagment;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEvent;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackScheduler extends AudioEventAdapter {

    // Attributes
    private AudioPlayer player;
    private BlockingQueue<AudioTrack> queue;

    // Constructor to create a new TrackScheduler object
    public TrackScheduler(AudioPlayer player) {

        this.player = player;
        this.queue = new LinkedBlockingQueue<>();

        System.out.println("TrackScheduler: " + this.player);

    }

    // Adds an audio track to the queue only if it cannot be immediately played
    public void queueTrack(MessageReceivedEvent event, AudioTrack track, String messageContent) {

        // If a song is already playing, inform user and add it to the queue
        if (!player.startTrack(track, true)) {

            queue.offer(track);
            event.getChannel().sendMessage("Track could not be played, added to queue instead.").queue();
            System.out.println(this.queue);

        // If no track is playing, play the offered track
        } else {
            event.getChannel().sendMessage("Now playing: `" + messageContent + "`").queue();
        }

    }

    // Play the song at the top of the queue
    public void playNextTrack() {

        // Checks that queue is not empty
        if (!queue.isEmpty()) {

            // Find the first element of the queue and remove it
            AudioTrack track = queue.poll();

            // Start playing whichever queue was at the front of the queue
            player.startTrack(track, false);
            System.out.println("playing");

        }

    }

    // Adds a track to queue
    public void queue(AudioTrack track) {
        this.queue.add(track);
    }

    // ends current track
    public void endTrack() {

        if(this.player.getPlayingTrack() != null) {
            AudioTrack track = this.player.getPlayingTrack();
            System.out.println("called endTrack() in TrackScheduler");
            this.player.getPlayingTrack().stop();
        }

    }

    @Override
    public void onEvent(AudioEvent audioEvent) {

    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if(endReason == AudioTrackEndReason.FINISHED) {
            System.out.println("track ended");
            this.playNextTrack();
        } else {
            System.out.println("Track stopped for unknown reason");
        }
    }

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {
        System.out.println("Started playing: " + this.player.getPlayingTrack());
    }

}
