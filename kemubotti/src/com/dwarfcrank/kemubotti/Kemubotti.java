package com.dwarfcrank.kemubotti;

import com.dwarfcrank.kemubotti.irc.IRCServer;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author dwarfcrank
 */
public class Kemubotti {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket s = new Socket(Config.getString("server_address"),
                Config.getInteger("server_port"));
        
        IRCServer server = IRCServer.connect(s);
        
        server.serverLoop();
    }
}
