/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;

/**
 *
 * @author madjuntu
 */
public class IRCChannel {
    private IRCServer server;
    private String name;

    public IRCChannel(IRCServer server, String name) {
        this.server = server;
        this.name = name;
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
}
