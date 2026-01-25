import java.time.LocalTime;

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

    @Override
    public String toString() {
        return super.toString() + "\nArtist: " + artist + "\nGenre: " + genre + "\nSeated: " + (seated ? "Yes" : "No") + "\nDuration: " + durationMinutes + " minutes";
    }
}
