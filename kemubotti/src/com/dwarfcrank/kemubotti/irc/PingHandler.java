package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;

/**
 *
 * @author dwarfcrank
 */
public class PingHandler extends IRCMessageHandler {

    @Override
    protected void run(IRCServer server, IRCMessage message) throws IOException {
        IRCMessage reply = new IRCMessage("PONG", message.getParameters());

        server.sendMessage(reply);
    }
}
