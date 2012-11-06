/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author dwarfcrank
 */
public class IRCStreamTest {

    TestInputStream inStream;
    TestOutputStream outStream;
    IRCStream stream;
    String[] testLines;

    class TestInputStream extends InputStream {

        private byte[] bytes;
        private int position;

        public TestInputStream(String text) {
            try {
                // Try converting to UTF-8 first, it's what the IRCStream class
                // uses as the encoding. Otherwise strings with certain characters
                // will break and thus fail the test.
                bytes = text.getBytes("UTF-8");
            } catch (UnsupportedEncodingException ex) {
                bytes = text.getBytes();
            }
            
            position = 0;
        }

        @Override
        public void reset() throws IOException {
            position = 0;

            super.reset();
        }

        @Override
        public int read() throws IOException {
            if (position >= bytes.length) {
                return -1;
            }

            int ret = bytes[position];
            position++;

            return ret;
        }
    }
    
    class TestOutputStream extends OutputStream {
        private StringBuilder stringBuilder;

        public TestOutputStream() {
            stringBuilder = new StringBuilder();
        }
        
        @Override
        public void write(int i) throws IOException {
            stringBuilder.append((char)i);
        }
        
        public String getString() {
            return stringBuilder.toString();
        }
        
        public void reset() {
            stringBuilder = new StringBuilder();
        }
    }

    /**
     * 
     */
    public IRCStreamTest() {
    }
    
    private String buildTestText(String[] lines) {
        StringBuilder builder = new StringBuilder();
        
        for(String s : lines) {
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
     * to test the readString() method with fake input/output stream classes.
     * @throws IOException
     */
    @Before
    public void setUp() throws IOException {
        testLines = new String[] {
            "Hello World!",
            "Hyvää päivää!",
            "0123456789",
        };
        
        String text = buildTestText(testLines);
        
        inStream = new TestInputStream(text);
        outStream = new TestOutputStream();
        
        stream = new IRCStream(inStream, outStream, "localhost");
    }

    /**
     * 
     */
    @After
    public void tearDown() {
    }
    
    /**
     * Tests reading from IRCStream.
     * @throws IOException
     */
    @Test
    public void testRead() throws IOException {
        for(String s : testLines) {
            String str = stream.readString();
            assertEquals(s, str);
        }
    }
    
    /**
     * Tests writing to IRCStream.
     * @throws IOException
     */
    @Test
    public void testWrite() throws IOException {
        for(String s : testLines) {
            stream.writeString(s);
            
            // Read back the result and compare
            String str = outStream.getString();
            
            // IRCStream.writeString appends \r\n to the end of the string, so
            // remember to account for that.
            assertEquals(s + "\r\n", str);
            
            outStream.reset();
        }
    }
}
