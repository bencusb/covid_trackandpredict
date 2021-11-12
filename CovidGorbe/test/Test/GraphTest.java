/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import graph.Graph;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Boncz Bence
 */
public class GraphTest {

    /**
     *
     */
    @Test
    public void testmindentis(){
        avgtest();
    }

    /**
     *
     */
    @Test
    public void avgtest(){
        Graph g = new Graph();
        List<Integer> t = new ArrayList<>();
        t.add(2);
        t.add(6);
        assertEquals((double)4, g.avg(t, 0));
        t.add(4);
        assertEquals((double)4, g.avg(t, 0));
        t.add(4);
        assertEquals((double)4, g.avg(t, 0));
    }
    
    /**
     *
     */
    @Test
    public void MeanYCalc(){
        Graph g = new Graph();
        int[] t = {2,6};
        assertEquals((double)4, g.MeanYCalc(t));
        int[] tt = {1};
        assertEquals((double)1, g.MeanYCalc(tt));
        int[] ttt = {1,3,4,4};
        assertEquals((double)3, g.MeanYCalc(ttt));
    }

    /**
     *
     */
    @Test
    public void MeanXCalc(){
        Graph g = new Graph();
        assertEquals((double)2, g.MeanXCalc(3));
        assertEquals((double)1, g.MeanXCalc(1));
        assertEquals((double)3, g.MeanXCalc(5));
    }
    
}
