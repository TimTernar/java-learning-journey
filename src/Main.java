import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        Location stozice = new Location(1, "Ljubljana", "Vojkova street", 500);
        Location UM = new Location(2, "Maribor", "Koroška street", 100);

        stozice.addSeat(new Seat(1, 1, 1));
        stozice.addSeat(new Seat(2, 1, 2));
        stozice.addSeat(new Seat(3, 1, 3));

        UM.addSeat(new Seat(1, 1, 1));
        UM.addSeat(new Seat(2, 1, 2));
        UM.addSeat(new Seat(3, 1, 3));

        //test
        User u = new User(1, "Tim", "Ternar", 22);

        Concert concert = new Concert(1, "Rock concert in Stožice", LocalTime.now(), stozice, "Parni Valjak", "Rock", true, 120);
        Conference conference = new Conference(1, "Conference about Java and memory optimizazion", LocalTime.now(), stozice, "Do better with Java", "Walter White", true);


        System.out.println(concert);
        System.out.println(conference);
    }
}
