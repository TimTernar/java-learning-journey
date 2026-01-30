import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

class InvalidCsvException extends Exception
{
    public InvalidCsvException(String m)
    {
        super(m);
    }
}

interface ConferenceCsvListener
{
    void OnWritten(String filepath);
}


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

    public static void writeConference(String filepath, ArrayList<Conference> conferences, ConferenceCsvListener listener) {
        String[] headers = {"Id", "Description", "Time", "Location", "Price", "Name", "Professor", "Catering included?"};

        try (FileWriter fileWriter = new FileWriter(filepath)) {

            fileWriter.append(String.join(";", headers)).append('\n');

            for (Conference c : conferences) {

                String location = c.getLocation().toString()
                        .replace("\r\n", " | ")
                        .replace("\n", " | ")
                        .replace("\r", " | ");

                if (c.getDescription().contains(";") ||
                        location.contains(";") ||
                        c.getName().contains(";") ||
                        c.getProfessor().contains(";")) {
                    throw new InvalidCsvException("Field contains ';' which breaks CSV: " + c.getId());
                }

                fileWriter.append(String.valueOf(c.getId())).append(';');
                fileWriter.append(c.getDescription()).append(';');
                fileWriter.append(c.getTime().toString()).append(';');
                fileWriter.append(location).append(';');
                fileWriter.append(String.valueOf(c.getPrice())).append(';');
                fileWriter.append(c.getName()).append(';');
                fileWriter.append(c.getProfessor()).append(';');
                fileWriter.append(c.returnCatering()).append('\n');
            }

            if (listener != null) {
                listener.OnWritten(filepath);
            }

        } catch (InvalidCsvException e) {
            System.out.println("Invalid CSV: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
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
                String[] parts = line.split(";", -1);

                int id = Integer.parseInt(parts[0].trim());
                String description = parts[1].trim();
                LocalTime time = LocalTime.parse(parts[2].trim());

                String locationRaw = parts[3].trim()
                        .replace(" | ", System.lineSeparator());
                double price = Double.parseDouble(parts[4].trim());
                String name = parts[5].trim();
                String professor = parts[6].trim();
                String cateringRaw = parts[7].trim();
                boolean catering = cateringRaw.equalsIgnoreCase("yes") || cateringRaw.equalsIgnoreCase("true");
                Location location = parseLocationFromCsv(locationRaw);

                Conference c = new Conference(id, description, time, location, price, name, professor, catering);
                conferences.add(c);
            }
        }
        catch (IOException e)
        {
            System.err.println("Failed to read conference CSV: " + e.getMessage());
        }

        return conferences;
    }


    //since location is a object inside of A object we need to parse it out
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



    public static List<String> getAllDescrption(ArrayList<Conference> conferences)
    {
        return conferences.stream().map(Conference::getDescription).toList();
    }

    public static String getDescriptionById(ArrayList<Conference> conferences, int id)
    {
        return conferences.stream().filter(c -> c.getId() == id).map(Conference::getDescription).findFirst().orElse(null);
    }

    public static List<Conference> getByProfessor(ArrayList<Conference> conferences, String professor)
    {
        return conferences.stream().filter(c -> c.getProfessor().equalsIgnoreCase(professor)).toList();
    }

    public static List<Conference> getByName(ArrayList<Conference> conferences, String name)
    {
        return conferences.stream().filter(c -> c.getName().equalsIgnoreCase(name)).toList();
    }
}


