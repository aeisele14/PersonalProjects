package edu.hastings.hastingscollege;

import java.util.HashMap;
import java.util.List;

public class Event {

    public String eventName;
    public String eventWeek;
    public String[] eventDays;
    public List<List<HashMap<String, String>>> eventsOfDay;

    public Event(String name, String week, String[] days, List<List<HashMap<String, String>>> dailyEvents) {
        this.eventName = name;
        this.eventWeek = week;
        this.eventDays = days;
        this.eventsOfDay = dailyEvents;
    }
}
