package CS2020.assignment2;

import java.util.UUID;

/**
* Represents a song.
*/
public class Song {
    private UUID songID;
    private UUID artistID;
    private String title;
    private int duration;

/**
* Creates a song with the specified attributes.
* @param artist The song's author.
* @param title The song's title.
* @param duration The song's length.
*/
    public Song(Artist artist, String title, int duration) {
        this.songID = UUID.randomUUID();
        this.artistID = artist.getArtistID();
        setTitle(title);
        setDuration(duration);
    }

/**
* Gets the song's ID.
* @return A UUID representing song's ID.
*/
    public UUID getSongID() {
        return songID;
    }

/**
* Sets the song's ID.
* @param songID A UUID representing song's ID.
*/
    public void setSongID(UUID songID) {
        this.songID = songID;
    }

/**
* Gets the artist's ID.
* @return A UUID representing artist's ID.
*/
    public UUID getArtistID() {
        return artistID;
    }

/**
* Sets the artist's ID.
* @param artistID A UUID representing artist's ID.
*/
    public void setArtistID(UUID artistID) {
        this.artistID = artistID;
    }

/**
* Gets the song's title.
* @return A song's title.
*/
    public String getTitle() {
        return title;
    }

/**
* Sets the song's title.
* @param title A song's title.
*/
    public void setTitle(String title) {
        this.title = title;
    }

/**
* Gets the song's duration.
* @return A song's duration.
*/
    public int getDuration() {
        return duration;
    }

/**
* Sets the song's duration.
* @param duration A song's duration.
*/
    public void setDuration(int duration) {
        this.duration = duration;
    }

/**
* A method to convert Song object to a string.
* @return A string representing a song.
*/
    public String toString() {
        return title;
    }
}