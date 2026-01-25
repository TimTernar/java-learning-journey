import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class Conference extends Event {

    private final String name;

    private final String professor;

    private final boolean hasCatering;

    public Conference(int id, String description, LocalTime time, Location location, double price, String name, String professor, boolean hasCatering) {
        super(id, description, time, location, price);
        this.name = name;
        this.professor = professor;
        this.hasCatering = hasCatering;
    }

    public String getName() {
        return name;
    }

    public String getProfessor() {
        return professor;
    }

    public String returnCatering() {
        return hasCatering ? "Yes" : "No";
    }

    @Override
    public String toString() {
        return super.toString() + "\nName: " + name + "\nProfessor: " + professor + "\nCatering: " + (hasCatering ? "Yes" : "No");
    }

    public static void writeConference(String filepath, ArrayList<Conference> conferences) {
        String[] headers = {"Id", "Description", "Time", "Location", "Price", "Name", "Professor", "Catering included?"};

        try (FileWriter fileWriter = new FileWriter(filepath)) {
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
        } catch (IOException e) {
            System.err.println("Failed to write conference CSV: " + e.getMessage());
        }
    }

    public static ArrayList<Conference> ReadConference(String filepath)
    {
        ArrayList<Conference> conferences = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath)))
        {
            String line;
            br.readLine();

            while((line = br.readLine()) != null)
            {
                String [] parts = line.split(";");

                int id = Integer.parseInt(parts[0]);
                String description = parts[1];
                LocalTime time = LocalTime.parse(parts[2].trim());
                double price = Double.parseDouble(parts[4].trim());
                String name = parts[5].trim();
                String professor = parts[6].trim();
                boolean catering = Boolean.parseBoolean(parts[7].trim());
            }
        }
        catch (IOException e)
        {
        System.err.println("Failed to write conference CSV: " + e.getMessage());
        }

        return conferences;
    }


}


