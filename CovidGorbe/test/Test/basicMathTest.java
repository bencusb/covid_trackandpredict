/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import jUnit.basicMath;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Defines testing methods for jUnit5
 * @author Patrik
 */
public class basicMathTest {
    
    /**
     *
     */
    @Test
    public void testall(){
        testOsszead();
        testOsszeszoroz();
        testAbs();
    }
    
    /**
     *
     */
    @Test
    public void testOsszead() {
        basicMath math = new basicMath();
        assertEquals((double)2, math.osszead(1, 1),0);
        assertEquals((double)3, math.osszead(1.5,1.5),0);
        assertEquals(3.2, math.osszead(1.6, 1.6),0);
    }
    
    /**
     *
     */
    @Test
    public void testOsszeszoroz() {
        basicMath math = new basicMath();
        assertEquals((double)96, math.osszeszoroz(1, 96),0);
        assertEquals((double)11, math.osszeszoroz(2.2,5),0);
        assertEquals(10.83, math.osszeszoroz(1.6, 1.6),0);
    }
    
    /**
     *
     */
    @Test
    public void testAbs() {
        basicMath math = new basicMath();
        assertEquals((double)2, math.abszolutertek(2),0);
        assertEquals((double)2, math.abszolutertek(-2),0);
        assertEquals(5738216.03, math.abszolutertek(-5738216.03),0);
    }
}
