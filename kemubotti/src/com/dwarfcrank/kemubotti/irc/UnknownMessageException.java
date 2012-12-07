package com.dwarfcrank.kemubotti.irc;

/**
 *
 * @author dwarfcrank
 */
class UnknownMessageException extends Exception {

    private String messageName;
    private IRCServer sender;

    public UnknownMessageException(String messageName, IRCServer sender) {
        this.messageName = messageName;
        this.sender = sender;
    }

    public String getMessageName() {
        return messageName;
    }

    public IRCServer getSender() {
        return sender;
    }
}
