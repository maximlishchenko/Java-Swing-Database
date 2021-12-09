package CS2020.assignment2;

import java.util.UUID;
import java.util.ArrayList;

/**
* Represents an artist.
*/
public class Artist {
    private UUID artistID;
    private String firstName;
    private String lastName;
    private String dob;
    private String placeOfBirth;
    private ArrayList<Song> songs = new ArrayList<Song>();

/**
* Creates an artist with the specified attributes.
* @param firstName The artist's first name.
* @param lastName The artist's last name.
* @param dob The artist's date of birth.
* @param placeOfBirth The artist's place of birth.
*/
    public Artist(String firstName, String lastName, String dob, String placeOfBirth) {
        this.artistID = UUID.randomUUID();
        setFirstName(firstName);
        setLastName(lastName);
        setDob(dob);
        setPlaceOfBirth(placeOfBirth);
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
* Gets the artist's first name.
* @return A String representing artist's first name.
*/
    public String getFirstName() {
        return firstName;
    }

/**
* Sets the artist's first name.
* @param firstName A String representing artist's first name.
*/
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

/**
* Gets the artist's last name.
* @return A String representing artist's last name.
*/
    public String getLastName() {
        return lastName;
    }

/**
* Sets the artist's last name.
* @param lastName A String representing artist's last name.
*/
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

/**
* Gets the artist's date of birth.
* @return A String representing artist's date of birth.
*/
    public String getDob() {
        return dob;
    }

/**
* Sets the artist's date of birth.
* @param dob A String representing artist's date of birth.
*/
    public void setDob(String dob) {
        this.dob = dob;
    }

/**
* Gets the artist's place of birth.
* @return A String representing artist's place of birth.
*/
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

/**
* Sets the artist's place of birth.
* @param placeOfBirth A String representing artist's place of birth.
*/
    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

/**
* Gets the artist's songs.
* @return An array list representing artist's songs.
*/
    public ArrayList<Song> getSongs() {
        return songs;
    }

/**
* Sets the artist's songs.
* @param songs An array list representing artist's songs.
*/
    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

/**
* Adds a song to the artist's songs.
* @param song A song object to be added.
*/
    public void addSong(Song song) {
        this.songs.add(song);
    }

/**
* A method to convert Artist object to a string.
* @return A string representing an artist.
*/
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}