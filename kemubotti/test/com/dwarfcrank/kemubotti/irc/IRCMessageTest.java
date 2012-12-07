package com.dwarfcrank.kemubotti.irc;

import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author dwarfcrank
 */
public class IRCMessageTest {

    public IRCMessageTest() {
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
    public void testParse() {
        String command = "TESTMESSAGE";
        String[] parameters = new String[]{
            "#channel",
            "word1",
            "word2"
        };

        String messageText = String.format("%s %s %s %s",
                command, parameters[0], parameters[1], parameters[2]);
        IRCMessage message = IRCMessage.parseMessage(messageText);

        assertTrue(message.getPrefix().isEmpty());
        assertEquals(message.getCommand(), command);

        String[] messageParameters = message.getParameters();
        assertEquals(messageParameters.length, parameters.length);

        for (int i = 0; i < parameters.length; i++) {
            assertEquals(messageParameters[i], parameters[i]);
        }
    }

    @Test
    public void testParseWithPrefix() {
        String prefix = "TestUser!testuser@test.user.org";
        String command = "TESTMESSAGE";
        String[] parameters = new String[]{
            "#channel",
            "word1",
            "word2"
        };

        String messageText = String.format(":%s %s %s %s %s",
                prefix, command, parameters[0], parameters[1], parameters[2]);
        IRCMessage message = IRCMessage.parseMessage(messageText);

        assertEquals(message.getPrefix(), prefix);
        assertEquals(message.getCommand(), command);

        String[] messageParameters = message.getParameters();
        assertEquals(messageParameters.length, parameters.length);

        for (int i = 0; i < parameters.length; i++) {
            assertEquals(messageParameters[i], parameters[i]);
        }
    }

    @Test
    public void testParseWithWholeSentence() {
        String prefix = "TestUser!testuser@test.user.org";
        String command = "PRIVMSG";
        String channel = "#channel";
        String sentence = "this is a sentence";

        String messageText = String.format(":%s %s %s :%s",
                prefix, command, channel, sentence);
        IRCMessage message = IRCMessage.parseMessage(messageText);

        assertEquals(message.getPrefix(), prefix);
        assertEquals(message.getCommand(), command);

        String[] messageParameters = message.getParameters();
        assertEquals(messageParameters.length, 2);

        assertEquals(messageParameters[0], channel);
        assertEquals(messageParameters[1], sentence);
    }

    @Test
    public void testCreate() {
        IRCMessage msg = new IRCMessage("PRIVMSG", "#channel", "word1");

        assertTrue(msg.getPrefix().isEmpty());
        assertEquals(msg.getCommand(), "PRIVMSG");
        assertEquals(msg.getParameters()[0], "#channel");
        assertEquals(msg.getParameters()[1], "word1");
    }

    @Test
    public void testToString() {
        IRCMessage msg = new IRCMessage("PRIVMSG", "#channel", ":my sentence");

        assertTrue(msg.toString().equals("PRIVMSG #channel :my sentence"));
    }
}
