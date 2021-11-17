/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrik
 */
public class katze implements Runnable {
    private Thread t;
    private mainGUI mg;
    public Boolean running;
    
    /**
     * 
     */
    @Override
    public void run() {
        mg.jPanel8.setVisible(true);
        while (running){
            try {
                t.sleep(500);
            } catch (InterruptedException ex) {
                System.out.println("Error in the thread");
            }
        }
        mg.jPanel8.setVisible(false);
    }
    /**
     * Starts a new thread
     */
    public void start(){
        if(t == null){
            t = new Thread(this, "Query");
            t.start();
        }
    }
    /**
     * Constructor
     * 
     * @param mg 
     */
    public katze(mainGUI mg){
        this.mg = mg;
        running = true;
    }
}
