import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

interface IOrganization
{
    void AddEvent(Event e, Integer eventId);

    void RemoveEvent(Integer eventId);

}

public class Organization implements IOrganization{
    //because dictionary is a abstract class you cannot instance it in java, unlinke in c#, so hashmap is used in java
    private Map<Integer, Event> events = new HashMap<>();

    public void AddEvent(Event e, Integer eventId)
    {
        events.put(eventId, e);
    }

    public void RemoveEvent(Integer eventId)
    {
        events.remove(eventId);
    }
}
