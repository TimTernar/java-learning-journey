import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class Conference extends Event{

    private String name;

    private String professor;

    private boolean hasCatering;

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

            for (Event e : conferences)
            {
                fileWriter.append(e.getId() + ";");
                fileWriter.append(e.getDescription() +";");
                fileWriter.append(e.getTime() + ";");
                fileWriter.append(e.getLocation() + ";");
                fileWriter.append(e.getPrice() + ";");

                if (e instanceof Conference c) {
                    fileWriter.append(c.getName() + ";");
                    fileWriter.append(c.getProfessor() + ";");
                    fileWriter.append(c.returnCatering() + "\n");
                } else {
                    fileWriter.append(";;;"+ "\n");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
