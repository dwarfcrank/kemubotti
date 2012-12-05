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
public class RplNamesHandler extends IRCMessageHandler {

    private String stripStatusSymbolsFromName(String name) {
        if (name.startsWith("@") || name.startsWith("+")) {
            name = name.substring(1);
        }
        
        return name;
    }

    private void addUsersToChannel(String[] names, IRCChannel channel) {
        for (String name : names) {
            if (name.isEmpty()) {
                continue;
            }

            name = stripStatusSymbolsFromName(name);

            channel.addUser(name);
        }
    }

    @Override
    protected void run(IRCServer server, IRCMessage message) throws IOException {
        String[] names = message.getParameters()[3].split(" ");
        String channelName = message.getParameters()[2];
        IRCChannel channel = server.getChannel(channelName);

        addUsersToChannel(names, channel);
    }
}
