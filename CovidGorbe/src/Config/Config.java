/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible for the config file
 * 
 * @author Patrik
 */
public class Config {

    /**
     *New property
     */
    public static Properties property = new Properties();
    /**
     * The name of the file it creates
     */
    String path = null;

    /**
     * Creates the config file if it doesn't exist
     * 
     * @param fileName the name of what the file should be
     */
    public Config(String fileName) {
        this.path = fileName;
        File configFile = new File(path);
        try { 
            if (!configFile.exists()){
                configFile.createNewFile();
                FileOutputStream oFile = new FileOutputStream(configFile, false);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Saves the title value pair in the config file
     * 
     * @param title title
     * @param value value
     */
    public void Save(String title, String value){  
        try{
            System.out.println(title + " ---> " + value);
            property.setProperty(title, value);
            try (FileOutputStream fos = new FileOutputStream(path)) {
                property.store(fos, null);
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    /**
     * Clears the file
     */
    public void Clear(){
        property.clear();
    }
    
    /**
     * Returns the value of the corresponding title from the config file
     * 
     * @param title title
     * @return the value of the title parameter
     */
    public String GetProp(String title) {
        String value ="";
        try {
            FileInputStream fis = new FileInputStream(path);
            property.load(fis);
            value = property.getProperty(title);
        } catch (IOException e) {
            System.out.println(e);
        }
        return value;
    }
}
