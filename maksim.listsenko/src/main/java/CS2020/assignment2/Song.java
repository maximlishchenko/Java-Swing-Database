package CS2020.assignment2;

import java.util.UUID;

public class Artist {
    private UUID songID;
    private UUID artistID;
    private String title;
    private int duration;

    public UUID getSongID() {
        return songID;
    }

    public void setSongID(UUID songID) {
        this.songID = songID;
    }

    public UUID getArtistID() {
        return artistID;
    }

    public void setArtistID(UUID artistID) {
        this.artistID = artistID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}