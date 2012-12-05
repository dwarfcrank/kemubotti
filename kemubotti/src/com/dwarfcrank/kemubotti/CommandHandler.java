/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti;

import com.dwarfcrank.kemubotti.irc.IRCChannel;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dwarfcrank
 */
public abstract class CommandHandler {
    
    private static Map<String, CommandHandler> commands 
            = new HashMap<String, CommandHandler>();
    
    static {
        addHandler("addevent", new AddeventHandler());
        addHandler("listevents", new ListeventsHandler());
    }
    
    protected abstract void run(String sender, IRCChannel channel, String line);
    
    protected static void addHandler(String command, CommandHandler handler) {
        commands.put(command, handler);
    }
    
    public static void HandleCommand(String command, String sender, IRCChannel channel, String line) {
        if(commands.containsKey(command)) {
            commands.get(command).run(sender, channel, line);
        } else {
            System.out.println("Unknown command " + command);
        }
    }
}
