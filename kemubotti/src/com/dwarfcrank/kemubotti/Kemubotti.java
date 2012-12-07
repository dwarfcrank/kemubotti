package com.dwarfcrank.kemubotti;

import com.dwarfcrank.kemubotti.irc.IRCServer;
import java.net.Socket;

/**
 *
 * @author dwarfcrank
 */
public class Kemubotti {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Socket s = new Socket(Config.getString("server_address"),
                    Config.getInteger("server_port"));

            IRCServer server = IRCServer.connect(s);

            server.serverLoop();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
