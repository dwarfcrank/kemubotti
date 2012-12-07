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

    // Extracts the command string from the IRC message.
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
    
    /**
     * Analyzes each line sent to an IRC channel and handles possible commands
     * appropiately.
     * @param sender Name of the user who sent this line.
     * @param channel The channel where the user sent this line.
     * @param line The actual line that was sent to IRC.
     */
    public void analyzeLine(String sender, IRCChannel channel, String line) {
        if(!line.startsWith(Config.getString("nick"))) {
            return;
        }
        
        String command = getCommandFromLine(line);
        if(command.isEmpty()) {
            return;
        }
        
        CommandHandler.handleCommand(command, sender, channel, line);
    }

    /**
     * Gets this bot's event database.
     * @return This bot's event database.
     */
    public EventDatabase getEventDatabase() {
        return eventDatabase;
    }

    /**
     * Constructs a new Bot instance associated to an IRC server.
     * @param server The server this Bot instance is associated to.
     */
    public Bot(IRCServer server) {
        this.server = server;
        eventDatabase = new EventDatabase();
    }
}
