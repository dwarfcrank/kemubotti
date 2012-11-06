package com.dwarfcrank.kemubotti.irc;

import java.io.*;

/**
 *
 * @author dwarfcrank
 */
public class IRCStream {

    private OutputStreamWriter outputStreamWriter;
    private InputStreamReader inputStreamReader;
    private String hostName;
    private static final String CHARSET = "UTF-8";
    // The IRC protocol specifies that the length of a message may not exceed
    // 512 bytes, so we can use that as the minimum capacity for the StringBuilder
    // used for reading.
    private static final int BUFFER_SIZE = 512;

    /**
     * Gets the hostname this stream is currently connected to.
     *
     * @return The hostname of the connection
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Creates a new IRC stream that can be written to or read from.
     *
     * @param inputStream The stream to read from
     * @param outputStream The stream to write to
     * @param hostName The hostname that this stream is connected to
     * @throws IOException
     */
    public IRCStream(InputStream inputStream, OutputStream outputStream, String hostName) throws IOException {
        outputStreamWriter = new OutputStreamWriter(outputStream, CHARSET);
        inputStreamReader = new InputStreamReader(inputStream, CHARSET);

        this.hostName = hostName;
    }

    /**
     * Writes a string to the server stream.
     *
     * @param string The string to write.
     * @throws IOException
     */
    public void writeString(String string) throws IOException {
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
    public String readString() throws IOException {
        StringBuilder builder = new StringBuilder();

        builder.ensureCapacity(BUFFER_SIZE);

        char character = (char) inputStreamReader.read();

        while (true) {
            // IRC messages are separated by \r\n, so check for those first
            if (character == '\r') {
                // Read one character ahead
                char nextChar = (char) inputStreamReader.read();

                if (nextChar == '\n') {
                    // \r\n found, break the loop
                    break;
                }

                // If they weren't, write the characters to the string and
                // continue.
                builder.append(character);
                builder.append(nextChar);

                continue;
            }

            builder.append(character);
            character = (char) inputStreamReader.read();
        }

        return builder.toString();
    }
}
