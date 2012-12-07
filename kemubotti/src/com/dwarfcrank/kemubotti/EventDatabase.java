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

    /**
     * Constructs a new EventDatabase instance.
     */
    public EventDatabase() {
        events = new ArrayList<Event>();
    }

    /**
     * Adds an event in the list. If the event is already present it will not be
     * added twice.
     * @param event The event to add.
     */
    public void addEvent(Event event) {
        if (!events.contains(event)) {
            events.add(event);

            Collections.sort(events);
        }
    }

    /**
     * Gets a list of all events in this database.
     * @return
     */
    public List<Event> getEvents() {
        return events;
    }
}
