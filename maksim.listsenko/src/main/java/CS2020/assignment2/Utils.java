package CS2020.assignment2;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Arrays;
import java.lang.Math.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;
import javax.swing.*;
import java.sql.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
* A class containing utility methods for working
* with artists, songs and databases.
*/
public class Utils {

/**
* Accepts an ArrayList of songs and formats each song accordingly.
* For example, "We are the champions (2:36)".
* @param songs An ArrayList of songs.
* @return A HashMap containing formatted songs.
*/
    public static HashMap<UUID, String> returnSongDurationAndTitleFormatted(ArrayList<Song> songs) {
        HashMap<UUID, String> myMap = new HashMap<UUID, String>();
        for (Song song : songs) {
            UUID key = song.getSongID();
            int duration = (int) song.getDuration();
            int minutes = (int) Math.floor(duration / 60);
            int seconds = (int) duration % 60;
            String value = song.getTitle() + " (" + String.valueOf(minutes) + ":" + String.valueOf(seconds) + ")";
            myMap.put(key, value);
        }
        return myMap;
    }

/**
* Accepts a date of birth string and checks whether
* an artist was born on weekend or not.
* @param dob A string representing an artist's date of birth.
* @return A boolean value representhing whether an artist
* was born on weekend or not.
*/
    public static boolean checkIfBornOnWeekend(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        LocalDate date = LocalDate.parse(dob, formatter);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
    }

/**
* Creates two example instances of artists with two songs each.
* @param list A JList where to insert the created artists.
*/
    public static void createExampleArtists(JList<Artist> list) {
        Artist artist1 = new Artist("Kanye", "West*", "8 Jun 1977", "Atlanta");
        ArrayList<Song> songs1 = new ArrayList<Song>();
        songs1.add(new Song(artist1, "Wolves", 301));
        songs1.add(new Song(artist1, "No Child Left Behind", 178));
        artist1.setSongs(songs1);

        Artist artist2 = new Artist("Dua", "Lipa*", "22 Aug 1995", "London");
        ArrayList<Song> songs2 = new ArrayList<Song>();
        songs2.add(new Song(artist2, "Levitating", 203));
        songs2.add(new Song(artist2, "Don't Start Now", 183));
        artist2.setSongs(songs2);

        DefaultListModel listModel = (DefaultListModel) list.getModel();
        listModel.addElement(artist1);
        listModel.addElement(artist2);
        list.setModel(listModel);
    }

/**
* Establishes a connection to an SQLite database.
* @return A connection object.
*/
    public static Connection connectToDatabase() {
        Connection conn = null;
        String url = "jdbc:sqlite:resources/CS2020-assignment2.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

/**
* First, establishes a connection to an SQLite database, then
* reads artists and songs from a database and adds them to a JList.
* @param list A JList where to add the read artists and songs.
*/
    public static void readArtistAndSongsFromDatabase(JList<Artist> list) {
        DefaultListModel listModel = (DefaultListModel) list.getModel();
        String sql = "SELECT * FROM Song, Artist WHERE Song.artistID = Artist.artistID";
        try (Connection conn = connectToDatabase();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
            ArrayList<Artist> artists = new ArrayList<Artist>();
            ArrayList<Song> songs = new ArrayList<Song>();
            while (rs.next()) {
                String fullName = rs.getString("name");
                String[] split = fullName.split(" ");
                Artist artist = new Artist(split[0], split[split.length - 1], rs.getString("dob"), rs.getString("placeOfBirth"));
                String stringArtistID = rs.getString("artistID");
                UUID artistID = UUID.fromString(stringArtistID);
                artist.setArtistID(artistID);
                boolean alreadyInArray = false;
                //Prevents adding duplicate artists
                for (Artist item : artists) {
                    if (String.valueOf(item.getArtistID()).equals(String.valueOf(artist.getArtistID()))) {
                        alreadyInArray = true;
                    }
                }
                if (!alreadyInArray) {
                    artists.add(artist);
                }
                Song song = new Song(artist, rs.getString("title").replaceAll("[\\n\\t]", ""), (int) rs.getObject("duration"));
                songs.add(song);
            }
            for (Artist artist : artists) {
                for (Song song : songs) {
                    if (String.valueOf(song.getArtistID()).equals(String.valueOf(artist.getArtistID()))) {
                        artist.addSong(song);
                    }
                }
                listModel.addElement(artist);
            }
            list.setModel(listModel);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

/**
* Accepts a JList with artists and writes information about
* them and their songs into two separate csv files.
* @param list A JList with artists.
*/
    public static void exportToCsv(JList<Artist> list) {
        try {
            File artists = new File("resources/artists.csv");
            File songs = new File("resources/songs.csv");
            FileWriter writer1 = new FileWriter("resources/artists.csv");
            FileWriter writer2 = new FileWriter("resources/songs.csv");
            writer1.write("artistID,dob,placeOfBirth\n");
            writer2.write("songID,artistID,title,duration\n");
            ArrayList<Artist> artistList = new ArrayList<Artist>();
            for (int i = 0; i < list.getModel().getSize(); i++) {
                artistList.add(list.getModel().getElementAt(i));
            }
            for (Artist artist : artistList) {
                String artistData = artist.getArtistID().toString() + "," + artist.getDob() +
                    "," + artist.getPlaceOfBirth() + "\n";
                writer1.write(artistData);
                for (Song song : artist.getSongs()) {
                    String songData = song.getSongID().toString() + "," + song.getArtistID().toString() + "," +
                        song.getTitle() + "," + String.valueOf(song.getDuration()) + "\n";
                    writer2.write(songData);
                }
            }
            writer1.close();
            writer2.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

/**
* Accepts a JList with artists and gets basic information about them
* including: number of artists, total number of songs from all artists, oldest artist,
* youngest artist, longest song name and shortest song name.
* @return A long string with the details.
*/
    public static String getDetails(JList<Artist> list) {
        ArrayList<Artist> artistList = new ArrayList<Artist>();
        for (int i = 0; i < list.getModel().getSize(); i++) {
            artistList.add(list.getModel().getElementAt(i));
        }
        if (artistList.isEmpty()) {
            return "The list is empty";
        }
        StringBuilder str = new StringBuilder();
        str.append("Number of artists: " + String.valueOf(artistList.size()) + "\n");
        int songCount = 0;
        Artist oldestArtist = artistList.get(0);
        Artist youngestArtist = artistList.get(0);
        ArrayList<Song> longestSongs = new ArrayList<Song>();
        ArrayList<Song> shortestSongs = new ArrayList<Song>();
        longestSongs.add(artistList.get(0).getSongs().get(0));
        shortestSongs.add(artistList.get(0).getSongs().get(0));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        for (Artist artist : artistList) {
            songCount += artist.getSongs().size();
            LocalDate oldestArtistDob = LocalDate.parse(oldestArtist.getDob(), formatter);
            LocalDate youngestArtistDob = LocalDate.parse(youngestArtist.getDob(), formatter);
            LocalDate currentArtistDob = LocalDate.parse(artist.getDob(), formatter);
            if (oldestArtistDob.compareTo(currentArtistDob) > 0) {
                oldestArtist = artist;
            }
            if (youngestArtistDob.compareTo(currentArtistDob) < 0) {
                youngestArtist = artist;
            }
            for (Song song : artist.getSongs()) {
                if (song.getTitle().length() > longestSongs.get(0).getTitle().length()) {
                    longestSongs.clear();
                    longestSongs.add(song);
                } else if (song.getTitle().length() == longestSongs.get(0).getTitle().length() && song != longestSongs.get(0)) {
                    longestSongs.add(song);
                }
                if (song.getTitle().length() < shortestSongs.get(0).getTitle().length()) {
                    shortestSongs.clear();
                    shortestSongs.add(song);
                } else if (song.getTitle().length() == shortestSongs.get(0).getTitle().length() && song != shortestSongs.get(0)) {
                    shortestSongs.add(song);
                }
            }
        }
        str.append("Total number of songs: " + String.valueOf(songCount) + "\n");
        str.append("Oldest artist is " + oldestArtist + "\n");
        str.append("Youngest artist is " + youngestArtist + "\n");
        str.append("Shortest song names are: " + shortestSongs + "\n");
        str.append("Longest song names are: " + longestSongs + "\n");
        return str.toString();
    }
}