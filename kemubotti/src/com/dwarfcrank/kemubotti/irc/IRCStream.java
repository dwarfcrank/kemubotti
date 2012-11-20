package com.dwarfcrank.kemubotti.irc;

import java.io.*;

/**
 *
 * @author dwarfcrank
 */
public class IRCStream {

    private OutputStreamWriter outputStreamWriter;
    private InputStreamReader inputStreamReader;
    private static final String CHARSET = "UTF-8";
    // The IRC protocol specifies that the length of a message may not exceed
    // 512 bytes, so we can use that as the minimum capacity for the StringBuilder
    // used for reading.
    private static final int BUFFER_SIZE = 512;

    /**
     * Creates a new IRC stream that can be written to or read from.
     *
     * @param inputStream The stream to read from
     * @param outputStream The stream to write to
     * @throws IOException
     */
    public IRCStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        outputStreamWriter = new OutputStreamWriter(outputStream, CHARSET);
        inputStreamReader = new InputStreamReader(inputStream, CHARSET);
    }

    /**
     * Writes a string to the server stream.
     *
     * @param string The string to write.
     * @throws IOException
     */
    public void writeLine(String string) throws IOException {
        string += "\r\n";

        outputStreamWriter.write(string);
        outputStreamWriter.flush();
    }

    /**
     * Reads a single line from the server stream. The terminating \r\n
     * characters are stripped from the string.
     *
     * @return The line read from the stream.
     * @throws IOException
     */
    public String readLine() throws IOException {
        StringBuilder builder = new StringBuilder();

        builder.ensureCapacity(BUFFER_SIZE);

        int character = inputStreamReader.read();

        // Read characters until \r\n or end of stream is encountered.
        while (character != -1) {
            // IRC messages are separated by \r\n, so check for those first
            if (character == '\r') {
                // Read one character ahead
                int nextChar = inputStreamReader.read();

                if (nextChar == '\n' || nextChar == -1) {
                    // \r\n found, break the loop
                    // Also break in case of end of stream and skip adding \r
                    // to the string.
                    break;
                }

                // No \r\n, write the characters to the string and continue.
                builder.append((char)character);
                builder.append((char)nextChar);

                continue;
            }

            builder.append((char)character);
            character = inputStreamReader.read();
        }

        return builder.toString();
    }
}
