package CS2020.assignment2;

import java.util.UUID;
import java.util.ArrayList;

public class Artist {
    private UUID artistID;
    private String firstName;
    private String lastName;
    private String dob;
    private String placeOfBirth;
    private ArrayList<Song> songs = new ArrayList<Song>();

    // Constructor
    public Artist(String firstName, String lastName, String dob, String placeOfBirth) {
        this.artistID = UUID.randomUUID();
        setFirstName(firstName);
        setLastName(lastName);
        setDob(dob);
        setPlaceOfBirth(placeOfBirth);
    }

    // Getter and setter methods
    public UUID getArtistID() {
        return artistID;
    }

    public void setArtistID(UUID artistID) {
        this.artistID = artistID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    //Other methods
    public void addSong(Song song) {
        this.songs.add(song);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}