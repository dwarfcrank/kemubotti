/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti;

import com.dwarfcrank.kemubotti.irc.IRCChannel;
import com.dwarfcrank.kemubotti.irc.IRCServer;

/**
 * This class represents the actual bot logic. Each server has one bot instance
 * associated to it.
 *
 * @author dwarfcrank
 */
public class Bot {

    private IRCServer server;
    private EventDatabase eventDatabase;

    private String getCommandFromLine(String line) {
        String[] parts = line.split(" ");
        
        for(int i = 1; i < parts.length; i++) {
            if(parts[i].isEmpty()) {
                continue;
            }
            
            return parts[i];
        }
        
        return "";
    }
    
    public void analyzeLine(String sender, IRCChannel channel, String line) {
        if(!line.startsWith(Config.getString("nick"))) {
            return;
        }
        
        String command = getCommandFromLine(line);
        if(command.isEmpty()) {
            return;
        }
        
        CommandHandler.HandleCommand(command, sender, channel, line);
    }

    public EventDatabase getEventDatabase() {
        return eventDatabase;
    }

    public Bot(IRCServer server) {
        this.server = server;
        eventDatabase = new EventDatabase();
    }
}
