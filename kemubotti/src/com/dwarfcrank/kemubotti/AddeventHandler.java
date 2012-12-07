/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti;

import com.dwarfcrank.kemubotti.irc.IRCChannel;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dwarfcrank
 */
public class AddeventHandler extends CommandHandler {
    
    /**
     * Adds an event suggestion to the event list.
     * @param sender The nickname of the user who issued this command.
     * @param channel The channel on which this command was issued.
     * @param line The actual message that was sent to the IRC channel.
     */
    @Override
    protected void run(String sender, IRCChannel channel, String line) {
        EventDatabase db = channel.getServer().getBot().getEventDatabase();
        Event e;
        
        try {
            e = Event.parseEvent(line);
        } catch (ParseException ex) {
            channel.say("Invalid date format.");
            return;
        }
        
        db.addEvent(e);
        channel.say("Added event " + e.getName());
    }
    
}
