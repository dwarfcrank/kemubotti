/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author dwarfcrank
 */
public class EventDatabase {

    private List<Event> events;

    public EventDatabase() {
        events = new ArrayList<Event>();
    }

    public void addEvent(Event event) {
        if (!events.contains(event)) {
            events.add(event);

            Collections.sort(events);
        }
    }

    public List<Event> getEvents() {
        return events;
    }
}
