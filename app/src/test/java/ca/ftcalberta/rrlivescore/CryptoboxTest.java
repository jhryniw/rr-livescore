package ca.ftcalberta.rrlivescore;


import org.junit.Before;
import org.junit.Test;

import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.Cryptobox;
import ca.ftcalberta.rrlivescore.models.Glyph;

import static junit.framework.Assert.assertEquals;

public class CryptoboxTest {

    private Cryptobox cryptobox;
    private Glyph grayGlyph;
    private Glyph brownGlyph;

    @Before
    public void setUp() throws Exception {
        grayGlyph = new Glyph(Glyph.Color.GRAY);
        brownGlyph = new Glyph(Glyph.Color.BROWN);

        Glyph[][] defaultBox = new Glyph[Cryptobox.ROWS][Cryptobox.COLS];
        defaultBox[3] = new Glyph[]{null,       null,       brownGlyph};
        defaultBox[2] = new Glyph[]{null,       null,       grayGlyph};
        defaultBox[1] = new Glyph[]{grayGlyph,  brownGlyph, grayGlyph};
        defaultBox[0] = new Glyph[]{grayGlyph,  grayGlyph,  brownGlyph};

        cryptobox = new Cryptobox(Alliance.BLUE, 1, defaultBox);
    }

    @Test
    public void testGlyphCountAutonomous() throws Exception {
        Glyph[][] simpleBox = new Glyph[Cryptobox.ROWS][Cryptobox.COLS];
        simpleBox[3] = new Glyph[]{null,       null, null};
        simpleBox[2] = new Glyph[]{null,       null, null};
        simpleBox[1] = new Glyph[]{brownGlyph, null, null};
        simpleBox[0] = new Glyph[]{grayGlyph,  null, null};

        cryptobox.setBox(simpleBox);

        assertEquals(2, cryptobox.getGlyphCount());

        int initialScore = cryptobox.getAutonomousScore();

        cryptobox.addGlyph(2, 0, Glyph.Color.BROWN);
        assertEquals(3, cryptobox.getGlyphCount());

        // Test the increment
        assertEquals(initialScore + 15, cryptobox.getAutonomousScore());
    }

    @Test
    public void testPartialGlyphRowCount() throws Exception {
        // Start from an empty box
        cryptobox = new Cryptobox(Alliance.BLUE);

        cryptobox.addGlyph(0, 0, Glyph.Color.BROWN);
        cryptobox.addGlyph(0, 1, Glyph.Color.GRAY);
        cryptobox.addGlyph(0, 2, Glyph.Color.BROWN);
        cryptobox.addGlyph(1, 0, Glyph.Color.GRAY);
        cryptobox.addGlyph(1, 1, Glyph.Color.GRAY);

        // only one row
        assertEquals(1, cryptobox.getRowCount());
        assertEquals(5, cryptobox.getGlyphCount());

        // only one row
        cryptobox.addPartialGlyph(1, 2);
        assertEquals(2, cryptobox.getRowCount());
        assertEquals(5, cryptobox.getGlyphCount());
    }

    @Test
    public void testPartialGlyphColumnCount() throws Exception {
        // Start from an empty box
        cryptobox = new Cryptobox(Alliance.BLUE);

        cryptobox.addGlyph(0, 0, Glyph.Color.BROWN);
        cryptobox.addGlyph(0, 1, Glyph.Color.GRAY);
        cryptobox.addGlyph(0, 2, Glyph.Color.BROWN);
        cryptobox.addGlyph(1, 0, Glyph.Color.BROWN);
        cryptobox.addGlyph(1, 1, Glyph.Color.GRAY);
        cryptobox.addGlyph(1, 2, Glyph.Color.BROWN);
        cryptobox.addGlyph(2, 0, Glyph.Color.BROWN);
        cryptobox.addGlyph(2, 1, Glyph.Color.GRAY);
        cryptobox.addGlyph(2, 2, Glyph.Color.BROWN);
        cryptobox.addGlyph(3, 0, Glyph.Color.BROWN);
        cryptobox.addGlyph(3, 1, Glyph.Color.GRAY);

        // only one row
        assertEquals(2, cryptobox.getColCount());
        assertEquals(11, cryptobox.getGlyphCount());

        // only one row
        cryptobox.addPartialGlyph(3, 2);
        assertEquals(2, cryptobox.getColCount());
        assertEquals(11, cryptobox.getGlyphCount());
    }

    @Test
    public void testGlyphCountTeleop() throws Exception {
        assertEquals(8, cryptobox.getGlyphCount());

        int initialScore = cryptobox.getTeleopScore();

        cryptobox.addGlyph(2, 0, Glyph.Color.BROWN);
        assertEquals(9, cryptobox.getGlyphCount());

        // Test the increment
        assertEquals(initialScore + 2, cryptobox.getTeleopScore());
    }

    @Test
    public void testRowCount() throws Exception {
        assertEquals(2, cryptobox.getCompleteRows());

        int initialScore = cryptobox.getTeleopScore();

        cryptobox.removeGlyph(1, 1);
        assertEquals(1, cryptobox.getCompleteRows());

        // Test the decrement
        assertEquals(initialScore - 12, cryptobox.getTeleopScore());
    }

    @Test
    public void testColCount() throws Exception {
        assertEquals(1, cryptobox.getCompleteColumns());

        int initialScore = cryptobox.getTeleopScore();

        cryptobox.removeGlyph(3, 2);
        assertEquals(0, cryptobox.getCompleteColumns());

        // Test the decrement
        assertEquals(initialScore - 22, cryptobox.getTeleopScore());
    }

    @Test
    public void testKeyColumnFirst() throws Exception {
        // Start from an empty box
        cryptobox = new Cryptobox(Alliance.BLUE);
        cryptobox.setKeyColumn(1);

        cryptobox.addGlyph(0, 1, Glyph.Color.GRAY);

        // key column + glyph score
        assertEquals(1, cryptobox.getKeyColumnCount());
        assertEquals(45, cryptobox.getAutonomousScore());
    }

    @Test
    public void testKeyColumnSecond() throws Exception {
        // Start from an empty box
        cryptobox = new Cryptobox(Alliance.BLUE);
        cryptobox.setKeyColumn(0);

        cryptobox.addGlyph(0, 1, Glyph.Color.BROWN);
        cryptobox.addGlyph(0, 0, Glyph.Color.GRAY);

        // No key column score, only points for glyphs
        assertEquals(0, cryptobox.getKeyColumnCount());
        assertEquals(30, cryptobox.getAutonomousScore());
    }

    @Test
    public void testKeyColumnWithReset() throws Exception {
        // Start from an empty box
        cryptobox = new Cryptobox(Alliance.BLUE);
        cryptobox.setKeyColumn(1);

        cryptobox.addGlyph(0, 1, Glyph.Color.GRAY);

        // key column + glyph score
        assertEquals(1, cryptobox.getKeyColumnCount());
        assertEquals(45, cryptobox.getAutonomousScore());

        cryptobox.reset();

        cryptobox.setKeyColumn(0);

        cryptobox.addGlyph(0, 1, Glyph.Color.BROWN);
        cryptobox.addGlyph(0, 0, Glyph.Color.GRAY);

        // No key column score, only points for glyphs
        assertEquals(0, cryptobox.getKeyColumnCount());
        assertEquals(30, cryptobox.getAutonomousScore());
    }

    @Test
    public void testNoCipher() throws Exception {
        assertEquals(false, cryptobox.isCipherComplete());
    }

    @Test
    public void testFrogCipher() throws Exception {
        Glyph[][] grayFrog = new Glyph[Cryptobox.ROWS][Cryptobox.COLS];
        grayFrog[3] = new Glyph[]{brownGlyph, grayGlyph,  brownGlyph};
        grayFrog[2] = new Glyph[]{grayGlyph,  brownGlyph, grayGlyph};
        grayFrog[1] = new Glyph[]{brownGlyph, grayGlyph,  brownGlyph};
        grayFrog[0] = new Glyph[]{grayGlyph,  brownGlyph, grayGlyph};

        oneCipherTest(grayFrog);
    }

    @Test
    public void testBirdCipher() throws Exception {
        Glyph[][] grayBird = new Glyph[Cryptobox.ROWS][Cryptobox.COLS];
        grayBird[3] = new Glyph[]{brownGlyph, grayGlyph,  brownGlyph};
        grayBird[2] = new Glyph[]{grayGlyph,  brownGlyph, grayGlyph};
        grayBird[1] = new Glyph[]{grayGlyph,  brownGlyph, grayGlyph};
        grayBird[0] = new Glyph[]{brownGlyph, grayGlyph,  brownGlyph};

        oneCipherTest(grayBird);
    }

    @Test
    public void testSnakeCipher() throws Exception {
        Glyph[][] graySnake = new Glyph[Cryptobox.ROWS][Cryptobox.COLS];
        graySnake[3] = new Glyph[]{grayGlyph,  brownGlyph, brownGlyph};
        graySnake[2] = new Glyph[]{grayGlyph,  grayGlyph,  brownGlyph};
        graySnake[1] = new Glyph[]{brownGlyph, grayGlyph,  grayGlyph};
        graySnake[0] = new Glyph[]{brownGlyph, brownGlyph, grayGlyph};

        oneCipherTest(graySnake);
    }

    private void oneCipherTest(Glyph[][] cipher) {
        // There is a singular teleop score for full cipher boxes
        int fullCipherScore = 154;

        Cryptobox cipherBox = new Cryptobox(Alliance.BLUE, 0, cipher);
        assertEquals(true, cipherBox.isCipherComplete());
        assertEquals(fullCipherScore, cipherBox.getTeleopScore());

        Cryptobox swappedCipherBox = cipherBox.swapGlyphColors();
        assertEquals(true, swappedCipherBox.isCipherComplete());
        assertEquals(fullCipherScore, swappedCipherBox.getTeleopScore());
    }
}
