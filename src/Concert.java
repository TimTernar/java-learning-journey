import java.io.FileWriter;
import java.io.IOException;
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

    public static List<Concert> getByGnere(ArrayList<Concert> concerts, String genre)
    {
        return concerts.stream().filter(c -> c.getGerne().equalsIgnoreCase(genre)).toList();
    }

    public static List<Concert> getByArtist(ArrayList<Concert> concerts, String artist)
    {
        return concerts.stream().filter(c -> c.getArtist().equalsIgnoreCase(artist)).toList();
    }

    public static List<Concert> getByDuration(ArrayList<Concert> concerts, Integer durationMinutes)
    {
        return concerts.stream().filter(c -> c.getDuration() == durationMinutes).toList();
    }

    public static void writeConcert(String filepath, ArrayList<Concert> concerts)
    {
        String[] headers = {"Id", "Description", "Time", "Location", "Price", "Locaiton", "Artist", "Genre", "Seated?", "Duration in Minutes"};

        try (FileWriter fileWriter = new FileWriter(filepath))
        {
            fileWriter.append(String.join(";", headers)).append('\n');

            for (Concert c : concerts)
            {
                String location = c.getLocation().toString()
                        .replace("\r\n", " | ")
                        .replace("\n", " | ")
                        .replace("\r", " | ");

                fileWriter.append(String.valueOf(c.getId())).append(';');
                fileWriter.append(c.getDescription()).append(';');
                fileWriter.append(c.getTime().toString()).append(';');
                fileWriter.append(location).append(';');
                fileWriter.append(String.valueOf(c.getPrice())).append(';');
                fileWriter.append(c.getArtist()).append(";");
                fileWriter.append(c.getGerne()).append(";");
                fileWriter.append(c.getSeated()).append(";");
                //changes int to String so it can be written into csv document or something
                fileWriter.append(Integer.toString(c.getDuration())).append(";");
            }
        }
        catch (IOException e)
        {
            System.out.println("IO error: " + e.getMessage());
        }
    }
}
