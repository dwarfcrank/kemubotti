/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarcrank.kemubotti;

import com.dwarfcrank.kemubotti.Event;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author dwarfcrank
 */
public class EventTest {
    
    public EventTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {    
    }
    
    @After
    public void tearDown() {
    }
    
    private Date newDate(int day, int month, int year) {
        Calendar c = Calendar.getInstance();

        c.clear();
        // need to subtract 1 from month because Java's calendar semantics
        // are *beep* braindead...
        c.set(year, month - 1, day);
        
        return c.getTime();
    }
    
    @Test
    public void testParseEvent() throws ParseException {
        Event e = Event.parseEvent("1/12/2012 test event #1");
        Date expected = newDate(1, 12, 2012);
        
        assertEquals(expected, e.getDate());
        assertEquals("test event #1", e.getName());
    }
    
    @Test
    public void testParseEventWithInvalidDate() {
        boolean gotException = false;
        
        try {
            Event e = Event.parseEvent("a/b/cccc this test shouldn't succeed");
        } catch(ParseException ex) {
            gotException = true;
        }
        
        assertTrue(gotException);
    }
}
