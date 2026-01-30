import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


//not creative I know, sue me
class InvalidCsvExceptionConcert extends Exception
{
    public InvalidCsvExceptionConcert (String m)
    {
        super(m);
    }
}

interface ConcertCsvListener
{
    void OnWritten(String filepath, int rowsWritten);
}

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

    public static void writeConcert(String filepath, ArrayList<Concert> concerts, ConcertCsvListener listener)
    {
        String[] headers = {"Id", "Description", "Time", "Location", "Price", "Locaiton", "Artist", "Genre", "Seated?", "Duration in Minutes"};
        int rowsWritten = 0;

        try (FileWriter fileWriter = new FileWriter(filepath))
        {
            fileWriter.append(String.join(";", headers)).append('\n');

            for (Concert c : concerts)
            {
                if (c == null) {
                    throw new InvalidCsvExceptionConcert("Concert entry is null in list");
                }

                String location = c.getLocation().toString()
                        .replace("\r\n", " | ")
                        .replace("\n", " | ")
                        .replace("\r", " | ");

                if (c.getDescription().contains(";") ||
                        location.contains(";") ||
                        c.getArtist().contains(";") ||
                        c.getGerne().contains(";")) {
                    throw new InvalidCsvExceptionConcert("Field contains ';' which breaks CSV: " + c.getId());
                }

                fileWriter.append(String.valueOf(c.getId())).append(';');
                fileWriter.append(c.getDescription()).append(';');
                fileWriter.append(c.getTime().toString()).append(';');
                fileWriter.append(location).append(';');
                fileWriter.append(String.valueOf(c.getPrice())).append(';');
                fileWriter.append(c.getArtist()).append(";");
                fileWriter.append(c.getGerne()).append(";");
                fileWriter.append(c.getSeated()).append(";");
                //changes int to String so it can be written into csv document or something
                fileWriter.append(Integer.toString(c.getDuration())).append("\n");

                ++rowsWritten;
            }

            if (listener != null) {
                listener.OnWritten(filepath,rowsWritten);
            }

        }
        catch (InvalidCsvExceptionConcert e)
        {
            System.out.println("Invalid CSV: " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("IO error: " + e.getMessage());
        }

    }

    public static ArrayList<Concert> ReadConcert(String filepath)
    {
        ArrayList<Concert> concerts = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filepath)))
        {
            String line;
            br.readLine();

            while((line = br.readLine()) != null)
            {
                String[] parts = line.split(";", -1);

                int id = Integer.parseInt(parts[0].trim());
                String description = parts[1].trim();
                LocalTime time = LocalTime.parse(parts[2].trim());
                String locationRaw = parts[3].trim().replace(" | ", System.lineSeparator());
                Location location = parseLocationFromCsv(locationRaw);
                double price = Double.parseDouble(parts[4].trim());
                String artist = parts[5].trim();
                String genre = parts[6].trim();
                String seatedRaw = parts[7].trim();
                boolean seated = seatedRaw.equalsIgnoreCase("yes") || seatedRaw.equalsIgnoreCase("true");

                int durationMinutes = Integer.parseInt(parts[8].trim());

                Concert c = new Concert(id, description, time, location, price, artist, genre, seated, durationMinutes);
                concerts.add(c);
            }

        }catch (IOException e)
        {
            System.err.println("Failed to read concert CSV: " + e.getMessage());
        }

        return concerts;
    }

    private static Location parseLocationFromCsv(String locationRaw) {
        String[] lines = locationRaw.split("\\R");
        String firstLine = lines[0].trim();

        String[] main = firstLine.split(",", 3);
        int locId = Integer.parseInt(main[0].trim());
        String city = main[1].trim();

        String streetAndNo = main[2].trim();
        int lastSpace = streetAndNo.lastIndexOf(' ');
        String street = (lastSpace >= 0) ? streetAndNo.substring(0, lastSpace).trim() : streetAndNo;
        int number = (lastSpace >= 0) ? Integer.parseInt(streetAndNo.substring(lastSpace + 1).trim()) : 0;

        return new Location(locId, city, street, number);
    }

    //method for writing into log
}
