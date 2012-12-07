/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author madjuntu
 */
public class IRCChannel {
    private IRCServer server;
    private String name;
    private Set<String> users;

    /**
     * Creates a new IRCChannel instance on the given server and with the given name.
     * @param server The server this channel is on.
     * @param name The name of this channel.
     */
    public IRCChannel(IRCServer server, String name) {
        this.server = server;
        this.name = name;
        users = new HashSet<String>();
    }

    /**
     * Gets the server this channel is on.
     * @return
     */
    public IRCServer getServer() {
        return server;
    }

    /**
     * Gets the name of this channel.
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * Leaves the channel.
     */
    public void part() {
        try {
            server.partChannel(name);
        } catch (IOException ex) {
            Logger.getLogger(IRCChannel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Sends a message to the channel.
     * @param text The text to send to the channel.
     */
    public void say(String text) {
        try {
            server.sendMessage(new IRCMessage("PRIVMSG", name, ":" + text));
        } catch (IOException ex) {
            Logger.getLogger(IRCChannel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Adds an user to the channel user list.
     * @param name
     */
    public void addUser(String name) {
        users.add(name);
    }
    
    /**
     * Removes an user from the channel user list.
     * @param name
     */
    public void removeUser(String name) {
        if(users.contains(name)) {
            users.remove(name);
        }
    }
}
