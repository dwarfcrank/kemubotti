package com.dwarfcrank.kemubotti.irc;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dwarfcrank
 */
public class TestOutputStream extends OutputStream {

    private StringBuilder stringBuilder;
    private List<String> strings;

    public TestOutputStream() {
        stringBuilder = new StringBuilder();
        strings = new ArrayList<String>();
    }

    @Override
    public void write(int i) throws IOException {
        stringBuilder.append((char) i);

        if ((char) i == '\n') {
            strings.add(stringBuilder.toString());
            stringBuilder = new StringBuilder();
        }
    }

    public String getString() {
        if (strings.isEmpty()) {
            return "";
        }

        String ret = strings.get(0);
        strings.remove(0);
        return ret;
    }

    public void reset() {
        stringBuilder = new StringBuilder();
    }
}