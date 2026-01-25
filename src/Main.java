void main() {

    Location stozice = new Location(1, "Ljubljana", "Vojkova street", 500);
    Location UM = new Location(2, "Maribor", "Koroška street", 100);

    stozice.addSeat(new Seat(1, 1, 1));
    stozice.addSeat(new Seat(2, 1, 2));
    stozice.addSeat(new Seat(3, 1, 3));

    UM.addSeat(new Seat(1, 1, 1));
    UM.addSeat(new Seat(2, 1, 2));
    UM.addSeat(new Seat(3, 1, 3));

    //test
    //User u = new User(1, "Tim", "Ternar", 22);

    Concert concert = new Concert(1, "Rock concert in Stožice", LocalTime.now(), stozice, 18.50, "Parni Valjak", "Rock", true, 120);
    Conference conference = new Conference(1, "Conference about Java and memory optimizazion", LocalTime.now(), stozice, 5.50, "Do better with Java", "Walter White", true);
    Conference conference2 = new Conference(3, "Why Manchaster United is finally back", LocalTime.now(), stozice, 4.50, "Michael Carrick is the saviour", "Tim Ternar", true);

    //added organization
    Organization eventim = new Organization();

    eventim.AddEvent(concert, 1);
    eventim.AddEvent(conference, 2);

    System.out.println("--------------------------");
    System.out.println("All events listed :");
    eventim.ListAllEvents();
    eventim.RemoveEvent(2);
    System.out.println("--------------------------");
    System.out.println("All events listed :");
    eventim.ListAllEvents();

    //list of conferences I just made
    ArrayList<Conference> conferences = new ArrayList<>();
    conferences.add(conference);
    conferences.add(conference2);

    Conference.writeConference("conferences.csv", conferences);

    List<String> descriptions = Conference.getAllDescrption(conferences);
    System.out.println(descriptions);

    // 2) Description by id
    String desc = Conference.getDescriptionById(conferences, 3);
    System.out.println(desc);

    // 3) By professor
    List<Conference> byProf = Conference.getByProfessor(conferences, "Tim Ternar");
    byProf.forEach(System.out::println);

    // 4) By name
    List<Conference> byName = Conference.getByName(conferences, "Michael Carrick is the saviour");
    byName.forEach(System.out::println);


}
