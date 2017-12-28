package ca.ftcalberta.rrlivescore;


import org.junit.Before;
import org.junit.Test;

import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.Relic;

import static junit.framework.Assert.assertEquals;

public class RelicTest {

    private Relic relic;

    @Before
    public void setUp() throws Exception {
        relic = new Relic(Alliance.BLUE);
    }

    @Test
    public void testZone1() throws Exception {
        relic.setZone(1);
        relic.setUpright(false);

        assertEquals(10, relic.getScore());
    }

    @Test
    public void testZone2() throws Exception {
        relic.setZone(2);
        relic.setUpright(false);

        assertEquals(20, relic.getScore());
    }

    @Test
    public void testZone3() throws Exception {
        relic.setZone(3);
        relic.setUpright(false);

        assertEquals(40, relic.getScore());
    }

    @Test
    public void testUpright() throws Exception {
        relic.setZone(1);
        relic.setUpright(true);

        assertEquals(15, relic.getUprightScore());
        assertEquals(25, relic.getScore());
    }

    @Test
    public void testUprightNotInZone() throws Exception {
        relic.setZone(0);
        relic.setUpright(true);

        assertEquals(0, relic.getScore());
    }
}
