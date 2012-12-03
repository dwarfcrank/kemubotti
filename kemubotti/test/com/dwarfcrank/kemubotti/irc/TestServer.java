/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author madjuntu
 */
public class TestServer extends IRCServer {

    private ArrayList<IRCMessage> sentMessages;
    private ArrayList<IRCMessage> readMessages;
    
    /**
     *
     */
    public TestServer() {
        super();
        
        sentMessages = new ArrayList<IRCMessage>();
        readMessages = new ArrayList<IRCMessage>();
    }
    
    public void addMessage(IRCMessage message) {
        readMessages.add(message);
    }

    public ArrayList<IRCMessage> getSentMessages() {
        return sentMessages;
    }

    public ArrayList<IRCMessage> getReadMessages() {
        return readMessages;
    }
    
    @Override
    public void serverLoop() {
    }

    @Override
    public void sendMessage(IRCMessage message) throws IOException {
        sentMessages.add(message);
    }

    @Override
    public IRCMessage readMessage() throws IOException {
        return readMessages.remove(0);
    }

    @Override
    public void disconnect() throws IOException {
    }

    @Override
    public IRCChannel joinChannel(String name) throws IOException {
        return new IRCChannel(this, name);
    }

    @Override
    public void partChannel(String name) throws IOException {
        
    }
    
}
