package CS2020.assignment2;

import java.util.UUID;

//import CS2020.assignment2.Artist;

public class Song {
    private UUID songID;
    private UUID artistID;
    private String title;
    private int duration;

    // Constructors
    public Song(Artist artist, String title, int duration) {
        this.songID = UUID.randomUUID();
        this.artistID = artist.getArtistID();
        setTitle(title);
        setDuration(duration);
    }

    public Song(UUID songID, UUID artistID, String title, int duration) {
        setSongID(songID);
        setArtistID(artistID);
        setTitle(title);
        setDuration(duration);
    }

    // Getter and setter methods
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