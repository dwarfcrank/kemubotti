package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author dwarfcrank
 */
public class IRCStream {

    private OutputStreamWriter outputStream;
    private InputStreamReader inputStream;
    private String hostName;
    private static final String CHARSET = "UTF-8";
    // The IRC protocol specifies that the length of a message may not exceed
    // 512 bytes, so we can use that as the minimum capacity for the StringBuilder
    // used for reading.
    private static final int BUFFER_SIZE = 512;

    /**
     * Gets the hostname this stream is currently connected to.
     * @return The hostname of the connection
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Creates a new stream that can be written to or read from.
     * @param socket The socket that will be used to read and write messages.
     * @throws IOException
     */
    public IRCStream(Socket socket) throws IOException {
        outputStream = new OutputStreamWriter(socket.getOutputStream(), CHARSET);
        inputStream = new InputStreamReader(socket.getInputStream(), CHARSET);

        hostName = socket.getInetAddress().getHostName();
    }

    /**
     * Writes a string to the server stream.
     * @param string The string to write.
     * @throws IOException
     */
    public void writeString(String string) throws IOException {
        string += "\r\n";
        
        outputStream.write(string);
        outputStream.flush();
    }

    /**
     * Reads a single line from the server stream. The terminating \r\n characters
     * are stripped from the string.
     * @return The line read from the stream.
     * @throws IOException
     */
    public String readString() throws IOException {        
        StringBuilder builder = new StringBuilder();

        builder.ensureCapacity(BUFFER_SIZE);

        char character = (char)inputStream.read();

        while (true) {
            // IRC messages are separated by \r\n, so check for those first
            if (character == '\r') {
                // Read one character ahead
                char nextChar = (char)inputStream.read();

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
            character = (char)inputStream.read();
        }

        return builder.toString();
    }
}
