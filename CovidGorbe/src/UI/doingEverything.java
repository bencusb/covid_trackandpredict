/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

/**
 *
 * @author Patrik
 */
public class doingEverything implements Runnable {
    private Thread t;
    private mainGUI mg;
    private katze madzsga;
    
    /**
     * Writes out everything
     */
    @Override
    public void run() {
        try{           
            try{
                mg.countrySearch();
                mg.config.Save(mg.countrySelector.getName(), mg.countrySelector.getSelectedItem().toString());
                mg.didItWriteOutTheDownwardTendency = false;
                //mg.saveToCache();
                mg.regionSearch();
                mg.dailyStatsInf=null;
                mg.jTextArea1.setText("");
                mg.writeOutData();
                if(mg.grafikonValto.isSelected()){
                    mg.createDeceasedGraph();
                }else{           
                    mg.createInfectedGraph();
                }
            }
            catch(NullPointerException e){
                if (e.getMessage() == "Country not in list") mg.jTextArea1.setText("Nincs ilyen ország.");
            }           
        }
        catch (Exception e){          
        }
        madzsga.running = false;
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
     * @param madzsga
     */
    public doingEverything(mainGUI mg, katze madzsga){
        this.mg = mg;
        this.madzsga = madzsga;
    }
}
