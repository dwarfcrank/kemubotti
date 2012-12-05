/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;

/**
 *
 * @author dwarfcrank
 */
public class PrivmsgHandler extends IRCMessageHandler {

    private String getSender(String prefix) {
        String[] parts = prefix.split("!");
        
        if(parts[0].startsWith(":")) {
            return parts[0].substring(1);
        }
        
        return parts[0];
    }
    
    @Override
    protected void run(IRCServer server, IRCMessage message) {
        String line = message.getParameters()[1];
        String sender = getSender(message.getPrefix());
        String channelName = message.getParameters()[0];
        
        IRCChannel channel = server.getChannel(channelName);
        
        server.getBot().analyzeLine(sender, channel, line);
    }
    
}
