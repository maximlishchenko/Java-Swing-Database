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

public class Utils {

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


    public static boolean checkIfBornOnWeekend(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        LocalDate date = LocalDate.parse(dob, formatter);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
    }


    public static void createExampleArtists(JList<Artist> list) {
        Artist artist1 = new Artist("Kanye", "West*", "8 Jun 1977", "Atlanta");
        ArrayList<Song> songs1 = new ArrayList<Song>();
        songs1.add(new Song(artist1, "No Child Left Behind", 178));
        songs1.add(new Song(artist1, "Wolves", 301));
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
}