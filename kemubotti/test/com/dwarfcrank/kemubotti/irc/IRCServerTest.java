package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;

/**
 *
 * @author dwarfcrank
 */
public class IRCServerTest {

    private IRCServer server;
    private MockSocket socket;

    class MockSocket extends Socket {

        public TestInputStream inputStream;
        public TestOutputStream outputStream;
        boolean closed;
        boolean throwException;

        public MockSocket(String inputStreamData) {
            inputStream = new TestInputStream(inputStreamData);
            outputStream = new TestOutputStream();

            closed = false;
            throwException = false;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            if (throwException) {
                throw new IOException("testing exceptions in getInputStream");
            }

            return inputStream;
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            if (throwException) {
                throw new IOException("testing exceptions in getOutputStream");
            }

            return outputStream;
        }

        @Override
        public synchronized void close() throws IOException {
            if (throwException) {
                throw new IOException("testing exceptions in close");
            }

            closed = true;
        }

        public boolean isClosed() {
            return closed;
        }

        public void setThrowException(boolean throwException) {
            this.throwException = throwException;
        }
    }

    public IRCServerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        String text = "TestUser!testuser@test.user.org PRVIMSG #channel :test sentence";
        socket = new MockSocket(text);

        try {
            server = IRCServer.connect(socket);
        } catch (IOException ex) {
            server = null;
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConnect() throws IOException {
        assertTrue(server != null);

        assertEquals("NICK kemubotti\r\n", socket.outputStream.getString());
        assertEquals("USER kemubotti kemubotti kemubotti :KEMUbotti\r\n", socket.outputStream.getString());
    }

    @Test
    public void testDisconnect() throws IOException {
        server.disconnect();

        assertTrue(socket.isClosed());
    }

    @Test
    public void testSendMessage() throws IOException {
        IRCMessage msg = new IRCMessage("PRIVMSG", "#channel", ":test sentence");

        server.sendMessage(msg);

        // IRCServer.connect() sends two messages automatically, get them out
        // of the message queue.
        socket.outputStream.getString();
        socket.outputStream.getString();

        assertEquals("PRIVMSG #channel :test sentence\r\n", socket.outputStream.getString());
    }
}
