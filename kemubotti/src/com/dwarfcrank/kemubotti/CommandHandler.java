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
    
    /**
     * Processes the command.
     * @param sender The nickname of the user who issued this command.
     * @param channel The channel this command was issued on.
     * @param line The whole message that was sent to IRC.
     */
    protected abstract void run(String sender, IRCChannel channel, String line);
    
    /**
     * Adds a handler for a command.
     * @param command The command string to associate the handler with.
     * @param handler The handler for the command.
     */
    protected static void addHandler(String command, CommandHandler handler) {
        commands.put(command, handler);
    }
    
    /**
     * Locates the command handler for a given command and executes it.
     * @param command The command to execute.
     * @param sender The nickname of the user who issued the command.
     * @param channel The channel this command was issued on.
     * @param line The whole message that was sent to IRC.
     */
    public static void handleCommand(String command, String sender, IRCChannel channel, String line) {
        if(commands.containsKey(command)) {
            commands.get(command).run(sender, channel, line);
        } else {
            System.out.println("Unknown command " + command);
        }
    }
}
