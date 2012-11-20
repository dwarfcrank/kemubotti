/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti.irc;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author dwarfcrank
 */
public class IRCMessageHandlerTest {
    
    class TestMessageHandler extends IRCMessageHandler {

        public boolean hasBeenRun;
        
        public TestMessageHandler() {
            hasBeenRun = false;
            
            IRCMessageHandler.addMessageHandler("TESTMSG", this);
        }
        
        @Override
        protected void run(IRCServer server) {
            hasBeenRun = true;
        }
        
    }
    
    public IRCMessageHandlerTest() {
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

    @Test
    public void testUnknownMessage() {
        try {
            IRCMessageHandler.handleMessage("NOTFOUND", null);
        } catch(UnknownMessageException ex) {
            // Success!
        }
    }
    
    @Test
    public void testMessage() throws UnknownMessageException {
        TestMessageHandler handler = new TestMessageHandler();
        
        IRCMessageHandler.handleMessage("TESTMSG", null);
        
        assertTrue(handler.hasBeenRun);
    }
}
