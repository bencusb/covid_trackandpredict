/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import jUnit.basicMath2;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Yama
 */
public class basicMathTest2 {
    @Test
    public void testall(){
        testKivon();
        testOsztas();
        testSquare();
    }
    
    /**
     *
     */
    @Test
    public void testKivon() {
        basicMath2 math = new basicMath2();
        assertEquals((double)0, math.kivonas(1, 1));
        assertEquals((double)0, math.kivonas(1.5,1.5));
        assertEquals((double)0, math.kivonas(1.6, 1.6));
    }
    
    /**
     *
     */
    @Test
    public void testOsztas() {
        basicMath2 math = new basicMath2();
        assertEquals((double)15, math.osztas(45, 3));
        assertEquals((double)8, math.osztas(24, 3));
        assertEquals((double)5, math.osztas(25, 5));
    }
    
    /**
     *
     */
    @Test
    public void testSquare() {
        basicMath2 math = new basicMath2();
        assertEquals((double)36, math.square(6));
        assertEquals((double)25, math.square(5));
        assertEquals((double)16, math.square(4));
    }
}
