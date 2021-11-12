/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import jUnit.basicMath2;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.engine.*;
import org.junit.jupiter.params.*;

/**
 *
 * @author Yama
 */
public class basicMathTest2 {
    @Test
    @DisplayName("Mindent is letesztel")
    public void testall(){
        testKivon();
        testOsztas();
        testSquare();
    }
    
    /**
     *
     */
    @Test
    @DisplayName("Osszeadas letesztelese")
    public void testKivon() {
        basicMath2 math = new basicMath2();
        assertEquals((double)0, math.kivonas(1, 1), "1 - 1 = 0");
        assertEquals((double)0, math.kivonas(1.5,1.5), "1.5 - 1.5 = 0");
        assertEquals((double)0, math.kivonas(1.6, 1.6), "1.6 - 1.6 = 0");
    }
    
    /**
     *
     */
    @Test
    @DisplayName("Osszeszorzas letesztelese")
    public void testOsztas() {
        basicMath2 math = new basicMath2();
        assertEquals((double)15, math.osztas(45, 3), "45 / 3 = 15");
        assertEquals((double)8, math.osztas(24, 3), "24 / 3 = 8");
        assertEquals((double)5, math.osztas(25, 5), "25 / 5 = 5");
    }
    
    /**
     *
     */
    @Test
    @DisplayName("Abszolut ertek letesztelese")
    public void testSquare() {
        basicMath2 math = new basicMath2();
        assertEquals((double)36, math.square(6), "6^2 = 36");
        assertEquals((double)25, math.square(5), "5^2 = 25");
        assertEquals((double)16, math.square(4), "4^2 = 16");
    }
}
