/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti.irc;

import com.dwarfcrank.kemubotti.Config;
import java.io.IOException;

/**
 *
 * @author madjuntu
 */
public class RplWelcomeHandler extends IRCMessageHandler {

    @Override
    protected void run(IRCServer server, IRCMessage message) throws IOException {
        server.joinChannel(Config.getString("channel"));
    }
    
}
