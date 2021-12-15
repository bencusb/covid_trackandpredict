/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that's responsible for showing the loading cat picture
 *
 * @author Patrik
 */
public class loadingPicture implements Runnable {
    /**
     * A thread
     */
    private Thread thread;
    /**
     * Instance of MainGUI
     */
    private mainGUI maingui;
    /**
     * Boolean
     * if it's true: the API is fetching data
     */
    public Boolean running;
    
    /**
     * 
     */
    @Override
    public void run() {
        maingui.loadingPicturePanel.setVisible(true);
        while (running){
            try {
                thread.sleep(500);
            } catch (InterruptedException ex) {
                System.out.println("Error in the thread");
            }
        }
        maingui.loadingPicturePanel.setVisible(false);
    }
    /**
     * Starts a new thread
     */
    public void start(){
        if(thread == null){
            thread = new Thread(this, "Query");
            thread.start();
        }
    }
    /**
     * Constructor
     * 
     * @param maingui instance of MainGUI
     */
    public loadingPicture(mainGUI maingui){
        this.maingui = maingui;
        running = true;
    }
}
