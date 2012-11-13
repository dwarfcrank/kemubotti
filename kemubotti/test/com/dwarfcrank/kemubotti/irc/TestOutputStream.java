/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author dwarfcrank
 */
public class TestOutputStream extends OutputStream {

    private StringBuilder stringBuilder;

    public TestOutputStream() {
        stringBuilder = new StringBuilder();
    }

    @Override
    public void write(int i) throws IOException {
        stringBuilder.append((char) i);
    }

    public String getString() {
        return stringBuilder.toString();
    }

    public void reset() {
        stringBuilder = new StringBuilder();
    }
}