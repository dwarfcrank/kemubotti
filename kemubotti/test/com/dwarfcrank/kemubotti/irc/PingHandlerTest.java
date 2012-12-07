package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author madjuntu
 */
public class PingHandlerTest {

    PingHandler pingHandler;
    TestServer server;

    public PingHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pingHandler = new PingHandler();
        server = new TestServer();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPing() throws IOException {
        pingHandler.run(server, new IRCMessage("PING", "abcdefg"));

        IRCMessage message = server.getSentMessages().get(0);

        assertEquals("PONG", message.getCommand());
        assertEquals("abcdefg", message.getParameters()[0]);
    }
}
