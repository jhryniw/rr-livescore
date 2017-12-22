package ca.ftcalberta.rrlivescore;


import org.junit.Before;
import org.junit.Test;

import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.Jewel;
import ca.ftcalberta.rrlivescore.models.JewelSet;

import static junit.framework.Assert.assertEquals;

public class JewelTest {

    private Jewel blueJewel;
    private Jewel redJewel;
    private JewelSet jewelSet;

    @Before
    public void setUp() throws Exception {
        blueJewel = new Jewel(Alliance.BLUE);
        redJewel = new Jewel(Alliance.RED);

        jewelSet = new JewelSet(redJewel, blueJewel);
    }

    @Test
    public void bothOnPlatform() throws Exception {
        blueJewel.setOnPlatform(true);
        redJewel.setOnPlatform(true);

        assertEquals(0, jewelSet.getScoreForAlliance(Alliance.BLUE));
        assertEquals(0, jewelSet.getScoreForAlliance(Alliance.RED));
    }

    @Test
    public void blueOffPlatform() throws Exception {
        blueJewel.setOnPlatform(false);
        redJewel.setOnPlatform(true);

        assertEquals(0, jewelSet.getScoreForAlliance(Alliance.BLUE));
        assertEquals(30, jewelSet.getScoreForAlliance(Alliance.RED));
    }

    @Test
    public void redOffPlatform() throws Exception {
        blueJewel.setOnPlatform(true);
        redJewel.setOnPlatform(false);

        assertEquals(30, jewelSet.getScoreForAlliance(Alliance.BLUE));
        assertEquals(0, jewelSet.getScoreForAlliance(Alliance.RED));
    }

    @Test
    public void bothOffPlatform() throws Exception {
        blueJewel.setOnPlatform(false);
        redJewel.setOnPlatform(false);

        assertEquals(0, jewelSet.getScoreForAlliance(Alliance.BLUE));
        assertEquals(0, jewelSet.getScoreForAlliance(Alliance.RED));
    }
}
