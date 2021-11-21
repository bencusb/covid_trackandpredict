/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

/**
 * Draws on the graphics panel in a new thread
 * 
 * @author Patrik
 */
public class drawGraphs implements Runnable {
    /**
     * a Thread
     */
    private Thread thread;
    /**
     * Instance of mainGUI
     */
    private mainGUI maingui;
    /**
     * Instance of loadingPicture
     */
    private loadingPicture pictureLoadClass;
    
    /**
     * Writes out everything
     */
    @Override
    public void run() {
        try{           
            try{
                maingui.countrySearch();
                maingui.config.Save(maingui.countrySelector.getName(), maingui.countrySelector.getSelectedItem().toString());
                maingui.didItWriteOutTheDownwardTendency = false;
                //mg.saveToCache();
                maingui.regionSearch();
                maingui.dailyStatsInf=null;
                maingui.textAreaForWritingOutData.setText("");
                maingui.writeOutData();
                if(maingui.grafikonValto.isSelected()){
                    maingui.createDeceasedGraph();
                }else{           
                    maingui.createInfectedGraph();
                }
            }
            catch(NullPointerException e){
                if (e.getMessage() == "Country not in list") maingui.textAreaForWritingOutData.setText("Nincs ilyen ország.");
            }           
        }
        catch (Exception e){          
        }
        pictureLoadClass.running = false;
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
     * @param maingui a MainGUI class
     * @param pictureLoadClass a pictureLoadClass
     */
    public drawGraphs(mainGUI maingui, loadingPicture pictureLoadClass){
        this.maingui = maingui;
        this.pictureLoadClass = pictureLoadClass;
    }
}
