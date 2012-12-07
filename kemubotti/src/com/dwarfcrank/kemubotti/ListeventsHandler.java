package com.dwarfcrank.kemubotti;

import com.dwarfcrank.kemubotti.irc.IRCChannel;
import java.util.List;

/**
 *
 * @author dwarfcrank
 */
public class ListeventsHandler extends CommandHandler {

    @Override
    protected void run(String sender, IRCChannel channel, String line) {
        List<Event> events = channel.getServer().getBot().getEventDatabase().getEvents();

        int i = 1;

        for (Event e : events) {
            channel.say(i + ": " + e.toString());
        }
    }
}
