package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;
import static org.junit.Assert.assertTrue;
import org.junit.*;

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
        protected void run(IRCServer server, IRCMessage message) {
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
    public void testUnknownMessage() throws IOException {
        try {
            IRCMessageHandler.handleMessage(new IRCMessage("NOTFOUND"), null);
        } catch (UnknownMessageException ex) {
            // Success!
        }
    }

    @Test
    public void testMessage() throws UnknownMessageException, IOException {
        TestMessageHandler handler = new TestMessageHandler();

        IRCMessageHandler.handleMessage(new IRCMessage("TESTMSG"), null);

        assertTrue(handler.hasBeenRun);
    }
}
