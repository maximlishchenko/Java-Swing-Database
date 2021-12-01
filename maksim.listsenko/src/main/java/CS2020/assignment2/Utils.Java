package CS2020.assignment2;

import java.util.Map;
import java.util.HashMap;
import.java.util.ArrayList;
import java.util.UUID;
import Math.floor;
import java.time.LocalDate;
import java.time.DayOfWeek;
import javax.swing*;

import CS2020.assignment2.Artist;
import CS2020.assignment2.Song;

public class Utils {

    public static HashMap<UUID, String> returnSongDurationAndTitleFormatted(ArrayList<Song> songs) {
        HashMap<UUID, String> myMap = new HashMap<UUID, String>();
        for (Song song : songs) {
            UUID key = song.getSongID();
            int minutes = Math.floor(duration / 60);
            int seconds = duration % 60;
            String value = song.getTitle() + " (" + minutes.toString() + ":" + seconds.toString() + ")";
            myMap.put(key, value);
        }
        return myMap;
    }


    public static boolean checkIfBornOnWeekend(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate date = LocalDate.parse(dob, formatter);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
    }


    public static void createExampleArtists(JList<Artist> list) {
        Artist artist1 = new Artist("Kanye", "West*", "8 Jun 1977", "Atlanta");
        ArrayList<Song> songs1 = new ArrayList<Song>;
        songs1.add(new Song(artist1, "No Child Left Behind", 178));
        songs1.add(new Song(artist1, "Wolves", 301));
        artist1.setSongs(songs1);

        Artist artist2 = new Artist("Dua", "Lipa*", "22 Aug 1995", "London");
        ArrayList<Song> songs2 = new ArrayList<Song>;
        songs2.add(new Song(artist2, "Levitating", 203));
        songs2.add(new Song(artist2, "Don't Start Now", 183));
        artist2.setSongs(songs2);

        DefaultListModel listModel = new DefaultListModel();
        listModel.addElement(artist1);
        listModel.addElement(artist2);
        list.setModel(listModel);
    }
}
