import java.time.LocalTime;

public class Concert extends Event {

    private String artist;

    private String genre;

    private boolean seated;

    private int durationMinutes;

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
