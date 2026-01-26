import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Concert extends Event {

    private final String artist;

    private final String genre;

    private final boolean seated;

    private final int durationMinutes;

    public Concert(int id, String description, LocalTime time, Location location, double price, String artist, String genre, boolean seated, int durationMinutes)
    {
        super(id, description, time, location, price);
        this.artist = artist;
        this.genre = genre;
        this.seated = seated;
        this.durationMinutes = durationMinutes;
    }

    public String getArtist(){ return artist; }

    public String getGerne() {return genre; }

    public String getSeated() {return seated ? "Yes" : "No" ;}

    public int getDuration() {return durationMinutes ; }

    @Override
    public String toString() {
        return super.toString() + "\nArtist: " + artist + "\nGenre: " + genre + "\nSeated: " + (seated ? "Yes" : "No") + "\nDuration: " + durationMinutes + " minutes";
    }

    public static List<String> getAllName(ArrayList<Concert> concerts)
    {
        return concerts.stream().map(Concert::getArtist).toList();
    }

    public static List<String> getAllGenre(ArrayList<Concert> concerts)
    {
        return concerts.stream().map(Concert::getGerne).toList();
    }

    public static List<Integer> geduration(ArrayList<Concert> concerts)
    {
        return concerts.stream().map(Concert::getDuration).toList();
    }

    public static List<String> getAllSeated(ArrayList<Concert> concerts)
    {
        return concerts.stream().map(Concert::getSeated).toList();
    }

    public static List<Concert> getByGnere(ArrayList<Concert> concerts, String genre)
    {
        return concerts.stream().filter(c -> c.getGerne().equalsIgnoreCase(genre)).toList();
    }
}
