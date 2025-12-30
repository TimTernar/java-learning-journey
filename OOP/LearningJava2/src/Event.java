import java.time.LocalTime;

public class Event {

    private int id;

    private String description;

    private LocalTime time;

    private Location location;

    public Event(int id, String description, LocalTime time, Location location)
    {
        this.id = id;
        this.description = description;
        this.time = time;
        this.location = location;
    }

    public int getId()
    {
        return id;
    }

    public String getDescription()
    {
        return description;
    }

    public LocalTime getTime()
    {
        return time;
    }

    public Location getLocation()
    {
        return location;
    }

    @Override
    public String toString() {
        return "---------------------\n" + "Event ID: " + id + "\nDescription: " + description + "\nTime: " + time + "\nLocation:\n" + location;
    }
}
