import java.time.LocalTime;

public class Conference extends Event{

    private String name;

    private String professor;

    private boolean hasCatering;

    public Conference(int id, String description, LocalTime time, Location location, String name, String professor, boolean hasCatering)
    {
        super(id, description, time, location);
        this.name = name;
        this.professor = professor;
        this.hasCatering = hasCatering;
    }

    @Override
    public String toString()
    {
        return super.toString() + "\nName: " + name + "\nProfessor: " + professor + "\nCatering: " + (hasCatering ? "Yes" : "No");
    }
}
