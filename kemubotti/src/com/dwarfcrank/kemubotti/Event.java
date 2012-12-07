/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dwarfcrank
 */
public class Event implements Comparable<Event> {
    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Date date;
    private String name;
    
    /**
     * Constructs a new Event instance with the given date and name.
     * @param date
     * @param name
     */
    public Event(Date date, String name) {
        this.date = date;
        this.name = name;
    }
    
    /**
     * Gets the date of this event.
     * @return
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * Gets the name of this event.
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * Compares this Event to another Event. The event name is completely irrelevant
     * to this comparison as the comparison is based entirely on dates.
     * @param t The other Event to compare to.
     * @return < 0 if the event is earlier than t, > 0 if the event is later than t and 0 if it is at the same time as t.
     */
    @Override
    public int compareTo(Event t) {
        return date.compareTo(t.date);
    }
    
    /**
     * Returns a string representation of this Event.
     * @return
     */
    @Override
    public String toString() {        
        return dateFormat.format(date) + " : " + name;
    }
    
    private static Date parseDate(String text) throws ParseException {        
        Date d = dateFormat.parse(text);
        return d;
    }
    
    
    
    /**
     * Parses a new Event from a string. The string format is expected to be
     * dd/mm/yyyy with the rest of the line being the name of the event.
     * @param text The text to parse from.
     * @return A new Event with the parsed date and name.
     */
    public static Event parseEvent(String text) throws ParseException {
        String[] parts = text.split(" ");
        
        Date d = null;
        String eventName = "";
        
        for(int i = 0; i < parts.length; i++) {
            if(d == null) {
                d = parseDate(parts[i]);
                continue;
            }
            
            eventName += parts[i];
            
            if(i < (parts.length - 1)) {
                eventName += " ";
            }
        }
        
        return new Event(d, eventName);
    }
}
