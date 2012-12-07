package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 * Test fixture for testing the IRCStream class.
 *
 * @author dwarfcrank
 */
public class IRCStreamTest {

    TestInputStream inStream;
    TestOutputStream outStream;
    IRCStream stream;
    String[] testLines;

    /**
     *
     */
    public IRCStreamTest() {
    }

    private String buildTestText(String[] lines) {
        StringBuilder builder = new StringBuilder();

        for (String s : lines) {
            builder.append(s);
            builder.append("\r\n");
        }

        return builder.toString();
    }

    /**
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     *
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Sets up the test fixture by generating a set of strings that will be used
     * to test the readLine() method with fake input/output stream classes.
     *
     * @throws IOException
     */
    @Before
    public void setUp() throws IOException {
        testLines = new String[]{
            "Hello World!",
            "Hyvää päivää!",
            "0123456789",};

        String text = buildTestText(testLines);

        inStream = new TestInputStream(text);
        outStream = new TestOutputStream();

        stream = new IRCStream(inStream, outStream);
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Tests reading from IRCStream.
     *
     * @throws IOException
     */
    @Test
    public void testRead() throws IOException {
        for (String s : testLines) {
            String str = stream.readLine();
            assertEquals(s, str);
        }
    }

    /**
     * Tests writing to IRCStream.
     *
     * @throws IOException
     */
    @Test
    public void testWrite() throws IOException {
        for (String s : testLines) {
            stream.writeLine(s);

            // Read back the result and compare
            String str = outStream.getString();

            // IRCStream.writeLine appends \r\n to the end of the string, so
            // remember to account for that.
            assertEquals(s + "\r\n", str);

            outStream.reset();
        }
    }
}
