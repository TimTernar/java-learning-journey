import java.time.LocalTime;

public abstract class Event {

    private final int id;

    private final String description;

    private final LocalTime time;

    private final Location location;

    private final double price;

    public Event(int id, String description, LocalTime time, Location location, double price)
    {
        this.id = id;
        this.description = description;
        this.time = time;
        this.location = location;
        this.price = price;
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

    public double getPrice() {return price; }

    @Override
    public String toString() {
        return "---------------------\n" + "Event ID: " + id + "\nDescription: " + description + "\nTime: " + time + "\nLocation:\n" + location + "\nPrice:\n" + price  ;
    }
}
