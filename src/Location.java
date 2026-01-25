import java.util.ArrayList;
import java.util.List;

public class Location {

    private final int id;

    private final String city;

    private final String street;

    private final int number;

    private final List<Seat> seats;

    public Location(int id, String city, String street, int number)
    {
        this.id = id;
        this.city = city;
        this.street = street;
        this.number = number;
        this.seats = new ArrayList<>();
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    @Override
    public String toString()
    {
        return   id + ", "  + city + ", " + street + " " + number + "\nSeats: " + seats.size();
    }

    public int getId()
    {
        return id;
    }
}
