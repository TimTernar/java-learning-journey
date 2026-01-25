import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class Conference extends Event{

    private final String name;

    private final String professor;

    private final boolean hasCatering;

    public Conference(int id, String description, LocalTime time, Location location, double price, String name, String professor, boolean hasCatering)
    {
        super(id, description, time, location, price);
        this.name = name;
        this.professor = professor;
        this.hasCatering = hasCatering;
    }

    public String getName()
    {
        return name;
    }

    public String getProfessor()
    {
        return professor;
    }

    public String returnCatering()
    {
        return hasCatering ? "Yes" : "No";
    }

    @Override
    public String toString()
    {
        return super.toString() + "\nName: " + name + "\nProfessor: " + professor + "\nCatering: " + (hasCatering ? "Yes" : "No");
    }

    public static void writeConference(String filepath, ArrayList<Conference> conferences)
    {
        String [] headers = {"Id", "Description", "Time", "Location", "Price", "Name", "Professor", "Catering included?"};

        try(FileWriter fileWriter = new FileWriter(filepath))
        {
            fileWriter.append(String.join(";", headers));
            fileWriter.append("\n");

            for (Conference c : conferences) {
                fileWriter.append(String.valueOf(c.getId())).append(';');
                fileWriter.append(c.getDescription()).append(';');
                fileWriter.append(c.getTime().toString()).append(';');
                fileWriter.append(c.getLocation().toString()).append(';');
                fileWriter.append(String.valueOf(c.getPrice())).append(';');
                fileWriter.append(c.getName()).append(';');
                fileWriter.append(c.getProfessor()).append(';');
                fileWriter.append(c.returnCatering()).append('\n');
            }
        }
        catch (IOException e) {
            System.err.println("Failed to write conference CSV: " + e.getMessage());
        }
    }

}
