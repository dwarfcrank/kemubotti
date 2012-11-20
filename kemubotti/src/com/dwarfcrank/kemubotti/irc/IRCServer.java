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
     * Connects to an IRC server and sends basic initialization messages.
     * @param socket The socket to use for the connection.
     * @return Returns a new IRCServer object that can be used to interface with
     * the remote server.
     * @throws IOException
     */
    public static IRCServer connect(Socket socket) throws IOException {
        IRCServer server = new IRCServer(socket);
        
        // TODO: Add a config system for nicks and such!
        server.sendMessage(new IRCMessage("NICK", "kemubotti"));
        server.sendMessage(new IRCMessage("USER", "kemubotti", "kemubotti",
                "kemubotti", ":KEMUbotti"));
        
        return server;
    }
    
    /**
     * The server loop handles incoming messages as they are received and ends
     * if the connection with the server has been lost.
     */
    public void serverLoop() {
        while(socket.isConnected()) {
            try {
                IRCMessage message = readMessage();
                IRCMessageHandler.handleMessage(message, this);
            } catch (IOException ex) {
                break;
            }  catch (UnknownMessageException ex) {
                // TODO: Some better error reporting...
                System.out.println("Unknown message " + ex.getMessageName() + "!");
                break;
            }
        }
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
     * Reads a single message from the server.
     * @return The read message.
     * @throws IOException
     */
    public IRCMessage readMessage() throws IOException {
        String line = ircStream.readLine();
        
        return IRCMessage.parseMessage(line);
    }
    
    /**
     * Disconnects from the remote server.
     * @throws IOException
     */
    public void disconnect() throws IOException {
        socket.close();
    }
    
    
}
