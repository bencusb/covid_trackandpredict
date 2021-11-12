/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import graph.Graph;
import java.util.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.engine.*;
import org.junit.jupiter.params.*;


/**
 *
 * @author Boncz Bence
 */
public class GraphTest {

    /**
     *
     */
    @Test
    @DisplayName("Fincsi tesztelések 🐼")
    public void testmindentis(){
        avgtest();
    }

    /**
     *
     */
    @Test
    @DisplayName("Fincsi avg teszt 😒")
    public void avgtest(){
        Graph g = new Graph();
        List<Integer> t = new ArrayList<>();
        t.add(2);
        t.add(6);
        assertEquals((double)4, g.avg(t, 0),"(2+6)/2=4");
        t.add(4);
        assertEquals((double)4, g.avg(t, 0),"(2+6+4)/3=4");
        t.add(4);
        assertEquals((double)4, g.avg(t, 0),"(2+6+4+4)/4=4");
    }
    
    /**
     *
     */
    @Test
    @DisplayName("Fincsi MeanYCalc teszt 😒")
    public void MeanYCalc(){
        Graph g = new Graph();
        int[] t = {2,6};
        assertEquals((double)4, g.MeanYCalc(t),"(2+6)/2=4");
        int[] tt = {1};
        assertEquals((double)1, g.MeanYCalc(tt),"(1)/1=1");
        int[] ttt = {1,3,4,4};
        assertEquals((double)3, g.MeanYCalc(ttt),"(1+3+4+4+)/4=4");
    }

    /**
     *
     */
    @Test
    @DisplayName("Fincsi MeanYCalc teszt 😳")
    public void MeanXCalc(){
        Graph g = new Graph();
        assertEquals((double)2, g.MeanXCalc(3),"(1+2+3)/3=2");
        assertEquals((double)1, g.MeanXCalc(1),"(1)/=1");
        assertEquals((double)3, g.MeanXCalc(5),"(1+2+3+4+5)/5=3");
    }
    
}
