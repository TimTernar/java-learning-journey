import java.util.ArrayList;
import java.util.List;

public class Location {

    private int id;

    private String city;

    private String street;

    private int number;

    private List<Seat> seats;

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
        return city + ", " + street + " " + number + "\nSeats: " + seats.size();
    }

}
