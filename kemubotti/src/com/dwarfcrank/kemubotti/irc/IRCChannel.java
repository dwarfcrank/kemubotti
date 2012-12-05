/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author madjuntu
 */
public class IRCChannel {
    private IRCServer server;
    private String name;
    private Set<String> users;

    public IRCChannel(IRCServer server, String name) {
        this.server = server;
        this.name = name;
        users = new HashSet<String>();
    }

    public IRCServer getServer() {
        return server;
    }

    public String getName() {
        return name;
    }
    
    public void part() throws IOException {
        server.partChannel(name);
    }
    
    public void say(String text) throws IOException {
        server.sendMessage(new IRCMessage("PRIVMSG", name, ":" + text));
    }
    
    public void addUser(String name) {
        users.add(name);
    }
    
    public void removeUser(String name) {
        if(users.contains(name)) {
            users.remove(name);
        }
    }
}
