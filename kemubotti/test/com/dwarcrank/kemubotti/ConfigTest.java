package com.dwarcrank.kemubotti;

import com.dwarfcrank.kemubotti.Config;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author dwarfcrank
 */
public class ConfigTest {

    public ConfigTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void tryUnknownKeys() {
        assertEquals(-1, Config.getInteger("asddgasgdjalxcvxcCV_XCVZBC"));
        assertTrue(Config.getString("_:DGASDG;DG;Gxccgsagae") == null);
    }

    @Test
    public void setAndGetString() {
        Config.setString("a", "value");
        assertEquals("value", Config.getString("a"));
    }

    @Test
    public void setAndGetInteger() {
        Config.setInteger("a", 1337);
        assertEquals(1337, Config.getInteger("a"));
    }

    @Test
    public void integerAndStringWithSameKey() {
        String key = "this_is_a_key";

        Config.setInteger(key, 1337);
        Config.setString(key, "value");

        assertEquals("value", Config.getString(key));
        assertEquals(1337, Config.getInteger(key));
    }
}
