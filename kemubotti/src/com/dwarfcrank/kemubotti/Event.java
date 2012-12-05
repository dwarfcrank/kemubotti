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
    private Date date;
    private String name;
    
    public Event(Date date, String name) {
        this.date = date;
        this.name = name;
    }
    
    public Date getDate() {
        return date;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public int compareTo(Event t) {
        return date.compareTo(t.date);
    }
    
    @Override
    public String toString() {        
        return date.toString() + " : " + name;
    }
    
    private static Date parseDate(String text) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            Date d = df.parse(text);
            return d;
        } catch (ParseException ex) {
            return null;
        }
    }
    
    public static Event parseEvent(String text) {
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
