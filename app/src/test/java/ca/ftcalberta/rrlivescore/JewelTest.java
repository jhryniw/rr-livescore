package ca.ftcalberta.rrlivescore;


import org.junit.Before;
import org.junit.Test;

import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.JewelSet;

import static junit.framework.Assert.assertEquals;

public class JewelTest {

    private JewelSet jewelSet;

    @Before
    public void setUp() throws Exception {
        jewelSet = new JewelSet();
    }

    @Test
    public void bothOnPlatform() throws Exception {
        assertEquals(0, jewelSet.getBlueScore());
        assertEquals(0, jewelSet.getRedScore());
    }

    @Test
    public void blueOffPlatform() throws Exception {
        jewelSet = new JewelSet(true, false);

        assertEquals(0, jewelSet.getBlueScore());
        assertEquals(30, jewelSet.getRedScore());
    }

    @Test
    public void redOffPlatform() throws Exception {
        jewelSet = new JewelSet(false, true);

        assertEquals(30, jewelSet.getBlueScore());
        assertEquals(0, jewelSet.getRedScore());
    }

    @Test
    public void bothOffPlatform() throws Exception {
        jewelSet = new JewelSet(false, false);

        assertEquals(0, jewelSet.getBlueScore());
        assertEquals(0, jewelSet.getRedScore());
    }
}
