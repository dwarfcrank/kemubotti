/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti;

import com.dwarfcrank.kemubotti.irc.IRCChannel;

/**
 *
 * @author dwarfcrank
 */
public class AddeventHandler extends CommandHandler {
    
    @Override
    protected void run(String sender, IRCChannel channel, String line) {
        EventDatabase db = channel.getServer().getBot().getEventDatabase();
        Event e = Event.parseEvent(line);
        
        db.addEvent(e);
        channel.say("Added event " + e.getName());
    }
    
}
