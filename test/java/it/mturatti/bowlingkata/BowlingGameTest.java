/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mturatti.bowlingkata;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mturatti
 */
public class BowlingGameTest {

    private Game g;

    public BowlingGameTest() {
    }

    @Before
    public void setUp() {
        g = new Game();
    }

    private void rollMany(int rolls, int pins) {
        for (int i = 0; i < rolls; i++) {
            g.roll(pins);
        }
    }

    @Test
    public void testGutterGame() {
        rollMany(20, 0);
        assertEquals(0, g.score());
    }

    @Test
    public void testAllOnes() {
        rollMany(20, 1);
        assertEquals(20, g.score());
    }

    @Test
    public void testSpare() {
        g.roll(5);
        g.roll(5);
        g.roll(3);
        rollMany(17, 0);
        assertEquals(16, g.score());
    }

    @Test
    public void testOneStrike() {
        rollStrike();
        g.roll(5);
        g.roll(3);
        rollMany(16, 0);
        assertEquals(26, g.score());
    }

    @Test
    public void testTurkey() {
        rollStrike(); //30
        rollStrike(); // 22
        rollStrike(); // 15
        g.roll(2);    // 5
        g.roll(3);
        rollMany(12, 0);
        assertEquals(72, g.score());
    }

    @Test
    public void testPerfectGame() {
        rollMany(12, 10);
        assertEquals(300, g.score());
    }

    @Test
    public void testOnlyLastSpare() {
        rollMany(18, 0);
        g.roll(5);
        g.roll(5);
        g.roll(1);
        assertEquals(11, g.score());
    }

    @Test
    public void testOnlyLastStrike() {
        rollMany(18, 0);
        rollStrike();
        g.roll(3);
        g.roll(5);
        assertEquals(18, g.score());
    }
    
    @Test
    public void testAnotherSpare() {
        rollMany(16, 0);
        g.roll(2);
        g.roll(8);
        g.roll(5);
        g.roll(3);
        assertEquals(23, g.score());
    }

    private void rollStrike() {
        g.roll(10);
    }
}
