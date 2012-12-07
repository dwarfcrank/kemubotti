/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti.irc;

import com.dwarfcrank.kemubotti.Bot;
import com.dwarfcrank.kemubotti.Config;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dwarfcrank
 */
public class IRCServer {

    private IRCStream ircStream;
    private Socket socket;
    
    private Bot bot;
    
    private Map<String, IRCChannel> channels;
    
    private IRCServer(Socket socket) throws IOException {
        this.socket = socket;
        
        InputStream inStream = socket.getInputStream();
        OutputStream outStream = socket.getOutputStream();
        
        ircStream = new IRCStream(inStream, outStream);
        
        channels = new HashMap<String, IRCChannel>();
        
        bot = new Bot(this);
    }
    
    // Required for mocking this class. Not actually used otherwise.
    /**
     * 
     */
    protected IRCServer() {
        
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
        server.sendMessage(new IRCMessage("NICK", Config.getString("nick")));
        server.sendMessage(new IRCMessage("USER", Config.getString("user_name"),
                "0", "*", ":" + Config.getString("real_name")));
        
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
    
    /**
     * Joins a channel.
     * @param name Name of the channel to join.
     * @return An IRCChannel object representing this channel.
     * @throws IOException This exception will be thrown if there is an issue
     * sending the join message.
     */
    public IRCChannel joinChannel(String name) throws IOException {        
        if(channels.containsKey(name)) {
            return channels.get(name);
        }
        
        sendMessage(new IRCMessage("JOIN", name));
        
        IRCChannel channel = new IRCChannel(this, name);
        
        channels.put(name, channel);
        
        return channel;
    }
    
    /**
     * Gets a channel.
     * @param name Name of the channel.
     * @return The requested channel or null if the channel has not been joined.
     */
    public IRCChannel getChannel(String name) {
        if(channels.containsKey(name)) {
            return channels.get(name);
        }
        
        return null;
    }
    
    /**
     * Leaves a channel.
     * @param name Name of the channel to leave.
     * @throws IOException This exception will be thrown if there is an issue
     * sending the message.
     */
    public void partChannel(String name) throws IOException {
        if(!channels.containsKey(name)) {
            return;
        }
        
        sendMessage(new IRCMessage("PART", name));
        channels.remove(name);
    }

    /**
     * Gets the Bot associated with this server.
     * @return
     */
    public Bot getBot() {
        return bot;
    }    
}
