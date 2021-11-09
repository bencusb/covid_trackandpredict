/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import jUnit.basicMath;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.engine.*;
import org.junit.jupiter.params.*;

/**
 * Defines testing methods for jUnit5
 * @author Patrik
 */
public class basicMathTest {
    
    /**
     *
     */
    @Test
    @DisplayName("Mindent is letesztel")
    public void testall(){
        testOsszead();
        testOsszeszoroz();
        testAbs();
    }
    
    /**
     *
     */
    @Test
    @DisplayName("Osszeadas letesztelese")
    public void testOsszead() {
        basicMath math = new basicMath();
        assertEquals((double)2, math.osszead(1, 1), "1 + 1 = 2");
        assertEquals((double)3, math.osszead(1.5,1.5), "1.5 + 1.5 = 3");
        assertEquals(3.2, math.osszead(1.6, 1.6), "1.6 + 1.6 = 3.2");
    }
    
    /**
     *
     */
    @Test
    @DisplayName("Osszeszorzas letesztelese")
    public void testOsszeszoroz() {
        basicMath math = new basicMath();
        assertEquals((double)96, math.osszeszoroz(1, 96), "1 * 96 = 96");
        assertEquals((double)11, math.osszeszoroz(2.2,5), "2.2 * 5 = 11");
        assertEquals(10.83, math.osszeszoroz(1.6, 1.6), "1.9 * 5.7 = 10.83");
    }
    
    /**
     *
     */
    @Test
    @DisplayName("Abszolut ertek letesztelese")
    public void testAbs() {
        basicMath math = new basicMath();
        assertEquals((double)2, math.abszolutertek(2), "|2| = 2");
        assertEquals((double)2, math.abszolutertek(-2), "|-2| = 2");
        assertEquals(5738216.03, math.abszolutertek(-5738216.03), "|-5738216.03| = 5738216.03");
    }
}
