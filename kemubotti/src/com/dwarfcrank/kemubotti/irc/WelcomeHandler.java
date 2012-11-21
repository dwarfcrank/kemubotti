/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;

/**
 *
 * @author madjuntu
 */
public class WelcomeHandler extends IRCMessageHandler {

    @Override
    protected void run(IRCServer server, IRCMessage message) throws IOException {
        server.joinChannel("#datism");
    }
    
}
