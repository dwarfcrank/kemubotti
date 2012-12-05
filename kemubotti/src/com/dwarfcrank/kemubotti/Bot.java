/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti;

import com.dwarfcrank.kemubotti.irc.IRCChannel;
import com.dwarfcrank.kemubotti.irc.IRCServer;

/**
 * This class represents the actual bot logic. Each server has one bot instance
 * associated to it.
 *
 * @author dwarfcrank
 */
public class Bot {

    private IRCServer server;

    public void analyzeLine(String sender, IRCChannel channel, String line) {
        
    }

    public Bot(IRCServer server) {
        this.server = server;
    }
}
