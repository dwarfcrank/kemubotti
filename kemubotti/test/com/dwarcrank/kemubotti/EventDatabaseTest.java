/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarcrank.kemubotti;

import com.dwarfcrank.kemubotti.Event;
import com.dwarfcrank.kemubotti.EventDatabase;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author dwarfcrank
 */
public class EventDatabaseTest {
    
    EventDatabase db;
    
    public EventDatabaseTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        db = new EventDatabase();
    }
    
    @After
    public void tearDown() {        
    }
    
    private Date newDate(int day, int month, int year) {
        Calendar c = Calendar.getInstance();

        c.clear();
        c.set(year, month - 1, day);
        
        return c.getTime();
    }
    
    @Test
    public void addingEventsWorksProperly() {
        Event e = new Event(newDate(2, 12, 2012), "test event #2");
        db.addEvent(e);
        
        assertEquals(1, db.getEvents().size());
        
        e = new Event(newDate(1, 12, 2012), "test event #1");
        db.addEvent(e);
        
        assertEquals(2, db.getEvents().size());
    }
    
    @Test
    public void addingEventTwice() {
        Event e = new Event(newDate(2, 12, 2012), "test event");
        db.addEvent(e);
        db.addEvent(e);
        
        // The same event should not be added twice, so the size should still
        // be 1.
        assertEquals(1, db.getEvents().size());
    }
    
    @Test
    public void eventsAreOrderedProperly() {
        // Events should be ordered in ascending order by date.
        Event e = new Event(newDate(2, 12, 2012), "test event #2");;
        db.addEvent(e);
        
        e = new Event(newDate(1, 12, 2012), "test event #1");
        db.addEvent(e);
        
        List<Event> events = db.getEvents();
        
        Date d1 = events.get(0).getDate();
        Date d2 = events.get(1).getDate();
        
        String x1 = d1.toString();
        String x2 = d2.toString();
        
        assertTrue(d1.before(d2));
    }
    
    
}
