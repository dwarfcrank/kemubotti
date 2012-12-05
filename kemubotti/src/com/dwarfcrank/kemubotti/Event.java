/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti;

import java.util.Date;

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
}
