package com.dwarfcrank.kemubotti;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple class for storing configuration data. TODO: Serialization.
 *
 * @author dwarfcrank
 */
public class Config {

    private static Map<String, String> strings = new HashMap<String, String>();
    private static Map<String, Integer> integers = new HashMap<String, Integer>();

    // Add some defaults
    static {
        setString("nick", "kemubotti");
        setString("user_name", "kemubotti");
        setString("real_name", "kemubotti");

        setString("server_address", "underworld2.no.quakenet.org");
        setInteger("server_port", 6667);

        setString("channel", "#kemu-dev");
    }

    /**
     * Gets a string value corresponding to the given key.
     *
     * @param key
     * @return The string value corresponding the key, otherwise null.
     */
    public static String getString(String key) {
        if (strings.containsKey(key)) {
            return strings.get(key);
        }

        return null;
    }

    /**
     * Gets an integer value corresponding to the given key.
     *
     * @param key
     * @return The integer value corresponding the key, otherwise -1.
     */
    public static int getInteger(String key) {
        if (integers.containsKey(key)) {
            return integers.get(key);
        }

        return -1;
    }

    /**
     * Sets the string value corresponding to the given key. Will overwrite the
     * existing value, if any.
     *
     * @param key
     * @param value
     */
    public static void setString(String key, String value) {
        strings.put(key, value);
    }

    /**
     * Sets the integer value corresponding to the given key. Will overwrite the
     * existing value, if any.
     *
     * @param key
     * @param value
     */
    public static void setInteger(String key, int value) {
        integers.put(key, value);
    }
}
