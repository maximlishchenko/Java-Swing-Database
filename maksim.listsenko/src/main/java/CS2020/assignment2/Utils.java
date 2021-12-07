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
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;
import java.sql.*;

//import CS2020.assignment2.Artist;
//import CS2020.assignment2.Song;

public class Utils {

    public static HashMap<UUID, String> returnSongDurationAndTitleFormatted(ArrayList<Song> songs) {
        HashMap<UUID, String> myMap = new HashMap<UUID, String>();
        for (Song song : songs) {
            UUID key = song.getSongID();
            int duration = (int) song.getDuration();
            int minutes = (int) Math.floor(duration / 60);
            int seconds = (int) duration % 60;
            //String value = song.getTitle() + " (" + minutes.toString() + ":" + seconds.toString() + ")";
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
        //String name1 = artist1.getFirstName() + " " + artist1.getLastName();
        listModel.addElement(artist1);
        //String name2 = artist2.getFirstName() + " " + artist2.getLastName();
        listModel.addElement(artist2);
        list.setModel(listModel);
    }


//     public static void connectToDatabase() {
//         Connection conn = null;
//         try {
//             String url = "jdbc:sqlite:home/codio/workspace/CS2020_CA2/maksim.listsenko/resources/CS2020-assignment2.db";
//             conn = DriverManager.getConnection(url);
//             System.out.println("Connection to SQLite has been established.");
//         } catch (SQLException e) {
//             System.out.println(e.getMessage());
//         }
//         return conn;
//         finally {
//             try {
//                 if (conn != null) {
//                     conn.close();
//                 }
//             } catch (SQLException ex) {
//                 System.out.println(ex.getMessage());
//             }
//         }
//     }


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
        String sql = "SELECT * FROM Artist";
        try (Connection conn = connectToDatabase();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String fullName = rs.getString("name");
                String[] split = fullName.split(" ");
                Artist artist = new Artist(split[0], split[split.length - 1], rs.getString("dob"), rs.getString("placeOfBirth"));
                String stringArtistID = rs.getString("artistID");
                UUID artistID = UUID.fromString(stringArtistID);
                artist.setArtistID(artistID);
//                 ArrayList<Song> songs = artist.getSongs();
//                 String sql2 = "SELECT * FROM Song WHERE Song.artistID = Artist." + stringArtistID;
//                 Statement stmt2 = conn.createStatement();
//                 ResultSet rs2 = stmt.executeQuery(sql2);
//                 while (rs2.next()) {
//                     Song song = new Song(artist, rs2.getString("title"), (int) rs2.getObject("duration"));
//                     String stringSongID = rs2.getString("songID");
//                     UUID songID = UUID.fromString(stringSongID);
//                     song.setSongID(songID);
//                     songs.add(song);
//                 }
//                 artist.setSongs(songs);
                listModel.addElement(artist);
            }
            String sql2 = "SELECT * FROM Song";
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(sql);
            ArrayList<Artist> artistArray = new ArrayList<Artist>();
            for (int i = 0; i < listModel.getSize(); i++) {
                Artist artist = (Artist) listModel.getElementAt(i);
                artistArray.add(artist);
            }
            for (Artist artist : artistArray) {
                ArrayList<Song> songs = artist.getSongs();
                while (rs2.next()) {
                    String stringArtistID = rs2.getString("artistID");
                    UUID artistID = UUID.fromString(stringArtistID);
                    if (artist.getArtistID() == artistID) {
                        Song song = new Song(artist, rs2.getString("title"), (int) rs2.getObject("duration"));
                        String stringSongID = rs2.getString("songID");
                        stringSongID = stringSongID.replaceAll("[\\n\\t ]", "");
                        UUID songID = UUID.fromString(stringSongID);
                        song.setSongID(songID);
                        artist.addSong(song);
                    }
                }
                //artist.setSongs(songs);
            }
            list.setModel(listModel);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}