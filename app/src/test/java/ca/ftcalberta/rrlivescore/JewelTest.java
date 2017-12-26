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
        assertEquals(0, jewelSet.getScoreForAlliance(Alliance.BLUE));
        assertEquals(0, jewelSet.getScoreForAlliance(Alliance.RED));
    }

    @Test
    public void blueOffPlatform() throws Exception {
        jewelSet.setOnPlatform(Alliance.BLUE, false);
        jewelSet.setOnPlatform(Alliance.RED, false);

        assertEquals(0, jewelSet.getScoreForAlliance(Alliance.BLUE));
        assertEquals(30, jewelSet.getScoreForAlliance(Alliance.RED));
    }

    @Test
    public void redOffPlatform() throws Exception {
        jewelSet.setOnPlatform(Alliance.BLUE, true);
        jewelSet.setOnPlatform(Alliance.BLUE, false);

        assertEquals(30, jewelSet.getScoreForAlliance(Alliance.BLUE));
        assertEquals(0, jewelSet.getScoreForAlliance(Alliance.RED));
    }

    @Test
    public void bothOffPlatform() throws Exception {
        jewelSet.setOnPlatform(Alliance.BLUE, false);
        jewelSet.setOnPlatform(Alliance.RED, false);

        assertEquals(0, jewelSet.getScoreForAlliance(Alliance.BLUE));
        assertEquals(0, jewelSet.getScoreForAlliance(Alliance.RED));
    }
}
