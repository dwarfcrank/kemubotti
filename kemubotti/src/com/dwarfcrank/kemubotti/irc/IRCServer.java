/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author dwarfcrank
 */
public class IRCServer {

    private IRCStream ircStream;
    private Socket socket;
    
    private IRCServer(Socket socket) throws IOException {
        this.socket = socket;
        
        InputStream inStream = socket.getInputStream();
        OutputStream outStream = socket.getOutputStream();
        
        ircStream = new IRCStream(inStream, outStream);
    }
    
    /**
     * Connects to an IRC server.
     * @param socket The socket to use for the connection.
     * @return Returns a new IRCServer object that can be used to interface with
     * the remote server.
     * @throws IOException
     */
    public static IRCServer connect(Socket socket) throws IOException {
        return new IRCServer(socket);
    }
    
    /**
     * Sends a message to the remote server.
     * @param message The message to send.
     * @throws IOException
     */
    public void sendMessage(IRCMessage message) throws IOException {
        ircStream.writeLine(message.toString());
    }
    
    /**
     * Disconnects from the remote server.
     * @throws IOException
     */
    public void disconnect() throws IOException {
        socket.close();
    }
    
    
}
