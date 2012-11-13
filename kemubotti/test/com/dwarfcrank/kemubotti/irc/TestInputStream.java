/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author dwarfcrank
 */
public class TestInputStream extends InputStream {

    private byte[] bytes;
    private int position;

    public TestInputStream(String text) {
        try {
            // Try converting to UTF-8 first, it's what the IRCStream class
            // uses as the encoding. Otherwise strings with certain characters
            // will break and thus fail the test.
            bytes = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            bytes = text.getBytes();
        }

        position = 0;
    }

    @Override
    public void reset() throws IOException {
        position = 0;

        super.reset();
    }

    @Override
    public int read() throws IOException {
        if (position >= bytes.length) {
            return -1;
        }

        int ret = bytes[position];
        position++;

        return ret;
    }
}