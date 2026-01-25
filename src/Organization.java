import java.util.HashMap;
import java.util.Map;

interface IOrganization
{
    void AddEvent(Event e, Integer eventId);

    void RemoveEvent(Integer eventId);

    void ListAllEvents();

}

public class Organization implements IOrganization{
    //because dictionary is an abstract class you cannot instance it in java, unlike in c#, so hashmap is used in java
    private final Map<Integer, Event> events = new HashMap<>();

    public void AddEvent(Event e, Integer eventId)
    {
        System.out.println("--------------------------");
        events.put(eventId, e);
        System.out.println("Event added : " + e.getDescription());
    }

    public void RemoveEvent(Integer eventId) {
        System.out.println("--------------------------");
        events.remove(eventId);
        System.out.println("Event Removed");
    }

    public void ListAllEvents()
    {
        for (Event e : events.values())
        {
            System.out.println(e.getDescription());
        }
    }

}
