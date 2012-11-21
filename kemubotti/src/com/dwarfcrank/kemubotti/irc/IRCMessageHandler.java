/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dwarfcrank
 */
public abstract class IRCMessageHandler {

    static {
        messageHandlers = new HashMap<String, IRCMessageHandler>();        
        
        // Ignore MOTD as it has no meaning to a bot.
        ignoreMessage("375"); // RPL_MOTDSTART
        ignoreMessage("372"); // RPL_MOTD
        ignoreMessage("376"); // RPL_MOTDEND
        
        addMessageHandler("PING", new PingHandler());
        addMessageHandler("001", new WelcomeHandler());
    }
    private static Map<String, IRCMessageHandler> messageHandlers;

    /**
     * This "actually" handles the message. Need a better name for it!
     *
     * @param server The server the message originated from.
     * @param message The message contents.
     */
    protected abstract void run(IRCServer server, IRCMessage message)
            throws IOException;

    /**
     * Adds a message handler to the list of available handlers. It is marked as
     * protected because only message handlers themselves are allowed to add
     * themselves to the list.
     *
     * @param messageName Message to handle.
     * @param handler The actual handler object that receives this message.
     */
    protected static void addMessageHandler(String messageName,
            IRCMessageHandler handler) {
        messageHandlers.put(messageName, handler);
    }

    private static void ignoreMessage(String message) {
        messageHandlers.put(message, null);
    }
    
    /**
     * Routes a message to the appropiate message handler. This only handles
     * messages incoming from the server!
     *
     * @param message The message to handle (case sensitive).
     * @param server The server this message originates from.
     * @throws UnknownMessageException If there is no handler for this message.
     */
    public static void handleMessage(IRCMessage message, IRCServer server)
            throws UnknownMessageException, IOException {
        if (messageHandlers.containsKey(message.getCommand())) {
            IRCMessageHandler handler = messageHandlers.get(message.getCommand());
            
            // If the message name exists but the handler is null, the message
            // is ignored.
            if(handler == null) {
                return;
            }
            
            handler.run(server, message);
        } else {
            throw new UnknownMessageException(message.getCommand(), server);
        }
    }
}
