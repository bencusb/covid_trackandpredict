/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import java.awt.Color;
import javax.swing.ImageIcon;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.time.format.DateTimeFormatter;
import graph.Graph;
import java.awt.Graphics;
import API.apiCalling;
import Config.Config;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;



/**
 *  Generates the MainGUI, and calls the api to Hungary with the current date
 * 
 * @author Yama
 */
public final class mainGUI extends javax.swing.JFrame {
    /**
     * Initial api calling used to display countries and regions when it can
     */
    public static apiCalling initialApiCalling;
    /**
     * The statistics of a country is stored here
     */
    public static apiCalling stats;   
    /**
     * Daily stats of infected people
     */
    public int dailyStatsInf[];   
    /**
     * Daily stats of deceased people
     */
    public int dailyStatsDe[];   
    /**
     * Creates a new config file
     */
    Config config = new Config("config.alomazelet");
    /**
     * Daily new data
     */
    List<String> nev=new ArrayList<>();
    /**
     * A String list containing all the countries
     */
    private List<String> countries = new ArrayList<String>();
    /**
     * A list containing the infected number of each day
     */
    private List<Integer> dailyStatsInfected = new ArrayList<>();
    /**
     * A list containing the death number of each day
     */
    private List<Integer> dailyStatsDeaths = new ArrayList<>();
    /**
     * The Selected Country
     */
    private String selectedCountry;
    /**
     * The starting date
     */
    private String date1;
    /**
     * The ending date
     */
    private String date2;
    /**
     * Stores wether or not we wrote out the downward tendency (if there is a downward tendency)
     */
    protected Boolean didItWriteOutTheDownwardTendency = false;
    /**
     * The Graphics panel
     */
    Graphics graphics;
    /**
     * Creates new form mainGUI
     */
    public mainGUI() {       
        initComponents();
        AutoCompleteDecorator.decorate(countrySelector);    
        
        loadingPicturePanel.setVisible(false);
        try {
            countrySearchButton.setIcon(new ImageIcon(ImageIO.read(new URL("http://karakaip.web.elte.hu/EVP%20kepek/search.png"))));
            regionSearchButton.setIcon(new ImageIcon(ImageIO.read(new URL("http://karakaip.web.elte.hu/EVP%20kepek/search.png"))));       
            loadingPictureIconLabel.setIcon(new ImageIcon(new URL("https://i.imgur.com/RAhitxq.gif")));
        }catch (IOException e){
            textAreaForWritingOutData.setText("Egy vagy több kép betöltése nem sikerült.\nEllenőrizze, hogy van-e internetkapcsolat.");
        }
        
        Boolean isDarkModeOn = false;
        try {
            if (countries.contains(config.GetProp(countrySelector.getName()))){
                countrySelector.setSelectedItem(config.GetProp(countrySelector.getName()));
            }
            else {
                countrySelector.setSelectedItem("Hungary");
            }
            isDarkModeOn = Boolean.parseBoolean(config.GetProp(darkModeToggleButton.getName()));
            runningAvgCheck.setSelected(Boolean.parseBoolean(config.GetProp(runningAvgCheck.getName())));
            linearCheck.setSelected(Boolean.parseBoolean(config.GetProp(linearCheck.getName())));
            exponencialCheck.setSelected(Boolean.parseBoolean(config.GetProp(exponencialCheck.getName())));
        } catch (Exception e) {
        }        
        if(isDarkModeOn){
            setDark();
            darkModeToggleButton.setSelected(true);
        }
        else {
            setLight();
            darkModeToggleButton.setSelected(false);
        }       
        
        regionSearchPanel.setVisible(false);
        try{
            initialApiCalling = new apiCalling("fetchCountryList");
        }catch (IOException | InterruptedException | ParseException ex) {
            Logger.getLogger(mainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        countries = initialApiCalling.getCountries();
        for(int i=0; i < countries.size(); i++){
            countrySelector.addItem(countries.get(i));
        }
             
        fromDate.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        tillDate.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        //System.out.println(java.sql.Date.valueOf(java.time.LocalDate.now()));
        try{
            countrySearch();          
        }
        catch(NullPointerException e){
            if ("Country not in list".equals(e.getMessage())) textAreaForWritingOutData.setText("Nincs ilyen ország.");
        }
        catch (Exception e){
            
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logoPanel = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        setDataPanel = new javax.swing.JPanel();
        countrySelector = new javax.swing.JComboBox<>();
        numberOfInfectedLabel = new javax.swing.JLabel();
        numberOfDeceased = new javax.swing.JLabel();
        numberOfDeceasedLabel = new javax.swing.JLabel();
        numberOfInfected = new javax.swing.JLabel();
        darkModeToggleButton = new javax.swing.JToggleButton();
        selectCountryLabel = new javax.swing.JLabel();
        fromDatePanel = new javax.swing.JPanel();
        fromDate = new com.toedter.calendar.JDateChooser();
        countrySearchButton = new javax.swing.JButton();
        regionSearchPanel = new javax.swing.JPanel();
        regionSelect = new javax.swing.JComboBox<>();
        regionSearchButton = new javax.swing.JButton();
        tillDate = new com.toedter.calendar.JDateChooser();
        exponencialCheck = new javax.swing.JCheckBox();
        linearCheck = new javax.swing.JCheckBox();
        runningAvgCheck = new javax.swing.JCheckBox();
        runningAverageDays = new javax.swing.JSpinner();
        grafikonValto = new javax.swing.JToggleButton();
        loadingPicturePanel = new javax.swing.JPanel();
        loadingPictureIconLabel = new javax.swing.JLabel();
        outputPanel = new javax.swing.JPanel();
        graphicsPanel = new javax.swing.JPanel();
        scrollPanel = new javax.swing.JScrollPane();
        textAreaForWritingOutData = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(192, 226, 238));
        setBounds(new java.awt.Rectangle(0, 0, 1024, 800));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        logoPanel.setBackground(new java.awt.Color(192, 226, 238));
        logoPanel.setPreferredSize(new java.awt.Dimension(914, 87));

        logo.setFont(new java.awt.Font("Microsoft JhengHei", 1, 36)); // NOI18N
        logo.setForeground(new java.awt.Color(193, 240, 219));

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logo)
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(logo))
        );

        setDataPanel.setBackground(new java.awt.Color(192, 226, 238));

        countrySelector.setBackground(new java.awt.Color(51, 51, 51));
        countrySelector.setEditable(true);
        countrySelector.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        countrySelector.setForeground(new java.awt.Color(102, 102, 102));
        countrySelector.setName("countrySelector"); // NOI18N

        numberOfInfectedLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        numberOfInfectedLabel.setText("Fertőzöttek száma(összes): ");

        numberOfDeceased.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        numberOfDeceased.setForeground(new java.awt.Color(255, 0, 0));
        numberOfDeceased.setText("123123123");

        numberOfDeceasedLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        numberOfDeceasedLabel.setText("Halottak száma(összes):");

        numberOfInfected.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        numberOfInfected.setText("123123123");

        darkModeToggleButton.setFocusPainted(false);
        darkModeToggleButton.setName("DarkMode"); // NOI18N
        darkModeToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darkModeToggleButtonActionPerformed(evt);
            }
        });

        selectCountryLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        selectCountryLabel.setText("Ország kiválasztása:");

        fromDatePanel.setBackground(new java.awt.Color(192, 226, 238));

        fromDate.setDateFormatString("y-MM-d");

        javax.swing.GroupLayout fromDatePanelLayout = new javax.swing.GroupLayout(fromDatePanel);
        fromDatePanel.setLayout(fromDatePanelLayout);
        fromDatePanelLayout.setHorizontalGroup(
            fromDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fromDatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );
        fromDatePanelLayout.setVerticalGroup(
            fromDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fromDatePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        countrySearchButton.setBackground(new java.awt.Color(102, 102, 102));
        countrySearchButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        countrySearchButton.setToolTipText("");
        countrySearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countrySearchButtonActionPerformed(evt);
            }
        });

        regionSearchPanel.setBackground(new java.awt.Color(192, 226, 238));

        regionSelect.setBackground(new java.awt.Color(51, 51, 51));
        regionSelect.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        regionSelect.setName("regionSelect"); // NOI18N

        regionSearchButton.setBackground(new java.awt.Color(102, 102, 102));
        regionSearchButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        regionSearchButton.setToolTipText("");
        regionSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regionSearchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout regionSearchPanelLayout = new javax.swing.GroupLayout(regionSearchPanel);
        regionSearchPanel.setLayout(regionSearchPanelLayout);
        regionSearchPanelLayout.setHorizontalGroup(
            regionSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regionSearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(regionSelect, 0, 274, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regionSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        regionSearchPanelLayout.setVerticalGroup(
            regionSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(regionSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            .addComponent(regionSearchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tillDate.setDateFormatString("y-MM-d");

        exponencialCheck.setText("Exponenciális");
        exponencialCheck.setContentAreaFilled(false);
        exponencialCheck.setName("exponential"); // NOI18N
        exponencialCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exponencialCheckActionPerformed(evt);
            }
        });

        linearCheck.setText("Lineáris");
        linearCheck.setContentAreaFilled(false);
        linearCheck.setName("linear"); // NOI18N
        linearCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linearCheckActionPerformed(evt);
            }
        });

        runningAvgCheck.setText("Futó átlag");
        runningAvgCheck.setContentAreaFilled(false);
        runningAvgCheck.setName("runningavg"); // NOI18N
        runningAvgCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runningAvgCheckActionPerformed(evt);
            }
        });

        runningAverageDays.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        runningAverageDays.setName("runningavgval"); // NOI18N
        runningAverageDays.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                valueChanged(evt);
            }
        });

        grafikonValto.setBackground(new java.awt.Color(51, 51, 51));
        grafikonValto.setFont(new java.awt.Font("Dialog", 0, 30)); // NOI18N
        grafikonValto.setText("Fertőzöttek");
        grafikonValto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        grafikonValto.setContentAreaFilled(false);
        grafikonValto.setFocusPainted(false);
        grafikonValto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grafikonValtoActionPerformed(evt);
            }
        });

        loadingPictureIconLabel.setBackground(new java.awt.Color(255, 255, 255));
        loadingPictureIconLabel.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        loadingPictureIconLabel.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout loadingPicturePanelLayout = new javax.swing.GroupLayout(loadingPicturePanel);
        loadingPicturePanel.setLayout(loadingPicturePanelLayout);
        loadingPicturePanelLayout.setHorizontalGroup(
            loadingPicturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loadingPictureIconLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        loadingPicturePanelLayout.setVerticalGroup(
            loadingPicturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loadingPictureIconLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout setDataPanelLayout = new javax.swing.GroupLayout(setDataPanel);
        setDataPanel.setLayout(setDataPanelLayout);
        setDataPanelLayout.setHorizontalGroup(
            setDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(setDataPanelLayout.createSequentialGroup()
                .addGroup(setDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, setDataPanelLayout.createSequentialGroup()
                        .addComponent(fromDatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(tillDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, setDataPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(regionSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(darkModeToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(setDataPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(countrySelector, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(countrySearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(setDataPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(selectCountryLabel))
                    .addGroup(setDataPanelLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(setDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numberOfDeceased)
                            .addComponent(numberOfInfected))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, setDataPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(numberOfDeceasedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, setDataPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(numberOfInfectedLabel)
                .addGap(41, 41, 41))
            .addGroup(setDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(setDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(setDataPanelLayout.createSequentialGroup()
                        .addGroup(setDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(runningAverageDays, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(runningAvgCheck)
                            .addComponent(linearCheck)
                            .addComponent(exponencialCheck))
                        .addGap(13, 13, 13)
                        .addComponent(grafikonValto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(loadingPicturePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        setDataPanelLayout.setVerticalGroup(
            setDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(setDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectCountryLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(setDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(countrySearchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(countrySelector, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regionSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(setDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fromDatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tillDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(setDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(setDataPanelLayout.createSequentialGroup()
                        .addComponent(linearCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exponencialCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(runningAvgCheck)
                        .addGap(5, 5, 5)
                        .addComponent(runningAverageDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(grafikonValto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(numberOfInfectedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numberOfInfected)
                .addGap(40, 40, 40)
                .addComponent(numberOfDeceasedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numberOfDeceased)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(loadingPicturePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(darkModeToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        outputPanel.setBackground(new java.awt.Color(192, 226, 238));

        graphicsPanel.setBackground(new java.awt.Color(255, 255, 255));
        graphicsPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                graphicsPanelComponentResized(evt);
            }
        });

        javax.swing.GroupLayout graphicsPanelLayout = new javax.swing.GroupLayout(graphicsPanel);
        graphicsPanel.setLayout(graphicsPanelLayout);
        graphicsPanelLayout.setHorizontalGroup(
            graphicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1058, Short.MAX_VALUE)
        );
        graphicsPanelLayout.setVerticalGroup(
            graphicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        textAreaForWritingOutData.setEditable(false);
        textAreaForWritingOutData.setColumns(20);
        textAreaForWritingOutData.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textAreaForWritingOutData.setLineWrap(true);
        textAreaForWritingOutData.setRows(5);
        textAreaForWritingOutData.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        scrollPanel.setViewportView(textAreaForWritingOutData);

        javax.swing.GroupLayout outputPanelLayout = new javax.swing.GroupLayout(outputPanel);
        outputPanel.setLayout(outputPanelLayout);
        outputPanelLayout.setHorizontalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanel)
            .addComponent(graphicsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        outputPanelLayout.setVerticalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputPanelLayout.createSequentialGroup()
                .addComponent(graphicsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1404, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(setDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(outputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(setDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(outputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Turns Night mode on or off
     * 
     * @author Patrik
     * @param evt An event
     */
    private void darkModeToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_darkModeToggleButtonActionPerformed
        setMode();
        SwingUtilities.invokeLater(() -> {
            if(grafikonValto.isSelected()){
                createDeceasedGraph();
            }else{
                createInfectedGraph();
            }   
        });
    }//GEN-LAST:event_darkModeToggleButtonActionPerformed

    /**
     * Calls the API with the given parameters
     * 
     * @param evt An button click
     */
    private void countrySearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countrySearchButtonActionPerformed
        SwingUtilities.invokeLater(() -> {
            loadingPicture loadPicture = new loadingPicture(this);
            loadPicture.start();
            drawGraphs graphDraw = new drawGraphs(this, loadPicture);
            graphDraw.start();
        });    
    }//GEN-LAST:event_countrySearchButtonActionPerformed
    /**
     * Saves the data to a cache file.
     * Currently not in use, possible point of improvement in a new revision of the program.
     */
    void saveToCache(){
        LocalDate curdate = LocalDate.parse(date1);
        for (int i = 0; i < dailyStatsInfected.size(); i++){
            config.Save(selectedCountry + "_" + curdate.toString() + "_c", dailyStatsInfected.get(i).toString());
            config.Save(selectedCountry + "_" + curdate.toString() + "_d", dailyStatsDeaths.get(i).toString());
            curdate = curdate.plusDays(1);
        }
    }
        
    /**
    * Writes out data 
    */
    protected void writeOutData(){
        textAreaForWritingOutData.setText(textAreaForWritingOutData.getText() + "Napi lebontásban:\r\n");             
        LocalDate curdate = LocalDate.parse(date1);
        for (int i = 0; i < dailyStatsInfected.size(); i++){
            String curday = curdate.toString();
            textAreaForWritingOutData.setText(textAreaForWritingOutData.getText() + "\t"+ curday + ": Fertőzöttek: " + dailyStatsInfected.get(i) + ", Halottak: " + dailyStatsDeaths.get(i) + "\r\n");
            curdate = curdate.plusDays(1);
        }
    }
    
    /**
     * Calls the API with the given parameters
     * 
     * @param evt An button click
     */
    private void regionSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regionSearchButtonActionPerformed
        regionDataSearch();
    }//GEN-LAST:event_regionSearchButtonActionPerformed
    
    /**
     * Redraws the graph if you click on it, and saves wether its on or not in the config file, also makes the jSpinner visible or not accordingly
     * 
     * @param evt a checkbox
     */
    private void runningAvgCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runningAvgCheckActionPerformed
        if(grafikonValto.isSelected()){
            createDeceasedGraph();
        }else{
            createInfectedGraph();
        } 
        config.Save(runningAvgCheck.getName(), runningAvgCheck.isSelected()+"");
        if(!runningAvgCheck.isSelected()) runningAverageDays.setVisible(false);
        else runningAverageDays.setVisible(true);
    }//GEN-LAST:event_runningAvgCheckActionPerformed
    
    /**
     * Redraws the graph if you click on it, and saves wether its on or not in the config file
     * 
     * @param evt a checkbox
     */
    private void exponencialCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exponencialCheckActionPerformed
            if(grafikonValto.isSelected()){
                createDeceasedGraph();
            }else{
                createInfectedGraph();
            }
            config.Save(exponencialCheck.getName(), exponencialCheck.isSelected()+"");     
    }//GEN-LAST:event_exponencialCheckActionPerformed
    
    /**
     * Redraws the graph if you click on it, and saves wether its on or not in the config file
     * 
     * @param evt a checkbox
     */
    private void linearCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linearCheckActionPerformed
            if(grafikonValto.isSelected()){
                createDeceasedGraph();
            }else{
                createInfectedGraph();
            }
        config.Save(linearCheck.getName(), linearCheck.isSelected()+"");
    }//GEN-LAST:event_linearCheckActionPerformed

    /**
     * Redraws the graph if you change the value of the jSpinner
     * 
     * @param evt a numericupdown / jSpinner
     */
    private void valueChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_valueChanged
        if(grafikonValto.isSelected()){
            createDeceasedGraph();
        }else{
            createInfectedGraph();
        }
    }//GEN-LAST:event_valueChanged

    /**
     * Redraws the graph if you resize the application
     * 
     * @param evt the resizing of the screen
     */
    private void graphicsPanelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_graphicsPanelComponentResized
        SwingUtilities.invokeLater(() -> {
            if(grafikonValto.isSelected()){
                createDeceasedGraph();
            }else{
                createInfectedGraph();
            }  
        });
    }//GEN-LAST:event_graphicsPanelComponentResized

    /**
     * Changes between the infected graph and the death graph
     * 
     * @param evt a jToggleButton
     */
    private void grafikonValtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grafikonValtoActionPerformed
        didItWriteOutTheDownwardTendency = false;
        textAreaForWritingOutData.setText("");
        writeOutData();
        if(grafikonValto.isSelected()){
            grafikonValto.setText("Halottak");
            createDeceasedGraph();
        }
        else{
            grafikonValto.setText("Fertőzöttek");
            createInfectedGraph();
        }
    }//GEN-LAST:event_grafikonValtoActionPerformed
    
    /**
     * It creates the infected graph to display on the graphics panel
     * 
     * @author Yama, T. Dani
     */
    protected void createInfectedGraph(){ 
        LocalDate dateBefore = LocalDate.parse(date1);
	LocalDate dateAfter = LocalDate.parse(date2);
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        int noOfDays = (int)noOfDaysBetween+1;
        
        //System.out.println("the number of days is "+noOfDays);
        
        dailyStatsInf = new int[dailyStatsInfected.size()];
        
        for (int i = 0; i < dailyStatsInfected.size(); i++) {
            dailyStatsInf[i] = dailyStatsInfected.get(i);
        }
        
        Graph graph = new Graph();
        Date date;
        Date startDate = fromDate.getDate();
        Date endDate= tillDate.getDate();
        if (startDate.after(endDate)){
            date = endDate;
        } 
        else{
            date = startDate;
        }
        try{
            graph.leastSquares(dailyStatsInf, noOfDays);//4.
            if (graph.leastSquares(dailyStatsInf, noOfDays)[0] < 0 && !didItWriteOutTheDownwardTendency){
                didItWriteOutTheDownwardTendency = true;
                textAreaForWritingOutData.setText(textAreaForWritingOutData.getText() + "Mivel csökkenő tendencia van, ezért az exponenciális függvény nem illeszkedik.\r\n");
            }
            graph.drawing(graphicsPanel.getGraphics(), graphicsPanel.getWidth(), graphicsPanel.getHeight(), noOfDays, dailyStatsInf,
                graph.leastSquares(dailyStatsInf, noOfDays)[0], graph.leastSquares(dailyStatsInf,
                        noOfDays)[1],date, darkModeToggleButton.isSelected(), nev, 50, 
                        linearCheck.isSelected(), exponencialCheck.isSelected(), runningAvgCheck.isSelected(), (int)runningAverageDays.getValue());
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e){
            textAreaForWritingOutData.setText("A mai adatokat nem lehet még lekérni. Próbáld újra később!");
        } 
        catch (Exception e){
            textAreaForWritingOutData.setText("Hiba történt! Próbáld meg másokkal!");
        } 
    }
    
    /**
    * It creates the deceased graph to display on the graphics panel
    * 
    * @author Yama
    */
    protected void createDeceasedGraph(){
        
        LocalDate dateBefore = LocalDate.parse(date1);
	LocalDate dateAfter = LocalDate.parse(date2);
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        int noOfDays = (int)noOfDaysBetween+1;
        
        //System.out.println("the number of days is "+noOfDays);
        
        dailyStatsDe = new int[dailyStatsDeaths.size()];
        
        for (int i = 0; i < dailyStatsDeaths.size(); i++) {
            dailyStatsDe[i] = dailyStatsDeaths.get(i);
        }
        Graph graph = new Graph();
        try{
            graph.leastSquares(dailyStatsDe, noOfDays);//4.
            if (graph.leastSquares(dailyStatsDe, noOfDays)[0] < 0 && !didItWriteOutTheDownwardTendency){
                didItWriteOutTheDownwardTendency = true;
                textAreaForWritingOutData.setText(textAreaForWritingOutData.getText() + "Mivel csökkenő tendencia van, ezért az exponenciális függvény nem illeszkedik.\r\n");
            }
            graph.drawing(graphicsPanel.getGraphics(), graphicsPanel.getWidth(), graphicsPanel.getHeight(), noOfDays, dailyStatsDe,
                graph.leastSquares(dailyStatsDe, noOfDays)[0], graph.leastSquares(dailyStatsDe,
                        noOfDays)[1],fromDate.getDate(), darkModeToggleButton.isSelected(), nev, 50, 
                        linearCheck.isSelected(), exponencialCheck.isSelected(), runningAvgCheck.isSelected(), (int)runningAverageDays.getValue());
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e){
            textAreaForWritingOutData.setText("A mai adatokat nem lehet még lekérni. Próbáld újra később!");
        } 
        catch (Exception e){
            textAreaForWritingOutData.setText("Hiba történt! Próbáld meg másokkal!");
        } 
    }
    
    /**
    * Checks if the country has regions, if it has then it will display them in a new combobox
    * 
    * @author Yama
    */
    protected void regionSearch(){
        selectedCountry = countrySelector.getSelectedItem().toString();
        regionSelect.removeAllItems();
        try{
            initialApiCalling.fetchRegionList(selectedCountry);
        }catch(IOException | InterruptedException | URISyntaxException | ParseException e){
            
        }
        List<String> regions = new ArrayList<>();
        try {
            regions = initialApiCalling.getRegions(selectedCountry);
        } catch (Exception e) {
            System.out.println("Region exception");
        }
        finally{
            if (!(regions.isEmpty())){
                regionSearchPanel.setVisible(true);           
                for(int i=0; i < regions.size(); i++){
                    regionSelect.addItem(regions.get(i));
                }
            }
            else regionSearchPanel.setVisible(false);
        }
    }
    
    /**
    * Stores the data of given country
    * 
    * @author Yama
    */
    protected void countrySearch(){
        if(fromDate.getDate() != null && tillDate.getDate() != null){
            Date date_date = fromDate.getDate();
            Date date_date2 = tillDate.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (date_date.after(date_date2)){
                date1 = dateFormat.format(date_date2);
                date2 = dateFormat.format(date_date);
            } 
            else{
                date1 = dateFormat.format(date_date);
                date2 = dateFormat.format(date_date2);
            }
            //System.out.println(date1);
        }
        
        selectedCountry = countrySelector.getSelectedItem().toString();
        if (!(countries.contains(selectedCountry))) throw new NullPointerException("Country not in list");
        else{
            try{
            stats = new apiCalling(date1, date2, selectedCountry);
            }catch (IOException | InterruptedException | ParseException | URISyntaxException ex) {
                Logger.getLogger(mainGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            int infected = 0;
            int deceased = 0;
            dailyStatsInfected.clear();
            infected = stats.getTodayNewConfirmed().values().stream().map(i -> {
                dailyStatsInfected.add(i);
                return i;
            }).map(i -> i).reduce(infected, Integer::sum);
            nev.clear();
            nev=new ArrayList<>(stats.getTodayNewConfirmed().keySet());
            dailyStatsDeaths.clear();
            deceased = stats.getTodayNewDeaths().values().stream().map(i -> {
                dailyStatsDeaths.add(i);
                return i;
            }).map(i -> i).reduce(deceased, Integer::sum);
            String infected_string = String.valueOf(infected);
            numberOfInfected.setText(infected_string);
            String deceased_string = String.valueOf(deceased);
            numberOfDeceased.setText(deceased_string);
        }
        
    }
    
    /**
    * Stores the data of given region
    * Currently this doesn't work because of API limitations
    * @author Yama
    */
    public void regionDataSearch(){
        String actual_date = "";
        
        int infected = 0;
        int deceased = 0;
        LocalDate dateBefore = null;
        LocalDate dateAfter;
        long noOfDaysBetween = 0;
        if(fromDate.getDate() != null && tillDate.getDate() != null){
            dateBefore = LocalDate.parse(date1);
            dateAfter = LocalDate.parse(date2);
            noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
            if (dateBefore.isAfter(dateAfter)){
                dateBefore = dateAfter;
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for(int i = 0; i <= noOfDaysBetween; i++){
            actual_date = formatter.format(dateBefore);
            dateBefore.plusDays(1);
            System.out.println(dateBefore);
            System.out.println(selectedCountry);
            System.out.println(regionSelect.getSelectedItem().toString());
            try{
                stats = new apiCalling(actual_date ,selectedCountry, regionSelect.getSelectedItem().toString(), (int)noOfDaysBetween);
            }catch (IOException | InterruptedException | ParseException | URISyntaxException ex) {
                Logger.getLogger(mainGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            infected += Integer.parseInt(stats.getRegionNewConfirmed().values().toString());
            infected += Integer.parseInt(stats.getRegionNewConfirmed().values().toString());
        }
        System.out.println(infected);
        String infected_string = String.valueOf(infected);
        numberOfInfected.setText(infected_string);
        String deceased_string = String.valueOf(deceased);
        numberOfDeceased.setText(deceased_string);
    }
    
    /**
     * Decides wether to turn on Night or Light mode
     * 
     * @author Patrik
     */
     private void setMode(){
        Boolean isDarkModeSelected = darkModeToggleButton.isSelected();
        config.Save(darkModeToggleButton.getName(), isDarkModeSelected.toString());
        if (isDarkModeSelected){
            setDark();
        }
        else {
            setLight();
        }
    }
    
     /**
     * Enables dark mode, by setting the background colors of elements
     * Also switches the image to the correct one
     * 
     * @author Patrik
     */
    private void setDark(){
        int r = 19;
        int g = 3;
        int b = 64;
        Color darkmodeColor = new Color(r, g, b);
     
        try {
            darkModeToggleButton.setIcon(new ImageIcon(ImageIO.read(new URL("http://karakaip.web.elte.hu/EVP%20kepek/LightMode.png"))));
            logo.setIcon(new ImageIcon(ImageIO.read(new URL("http://karakaip.web.elte.hu/EVP%20kepek/LogoW.png"))));
        } catch (IOException e) {
            textAreaForWritingOutData.setText("Egy vagy több kép betöltése nem sikerült.\nEllenőrizze, hogy van-e internetkapcsolat.");
        }

        grafikonValto.setForeground(Color.WHITE);
        
        graphicsPanel.setBackground(Color.black);
        setDataPanel.setBackground(darkmodeColor);
        logoPanel.setBackground(darkmodeColor);
        outputPanel.setBackground(darkmodeColor);
        fromDatePanel.setBackground(darkmodeColor);
        regionSearchPanel.setBackground(darkmodeColor);
            
        numberOfInfectedLabel.setForeground(Color.white);
        selectCountryLabel.setForeground(Color.white);
        numberOfDeceasedLabel.setForeground(Color.white);
        numberOfInfected.setForeground(Color.white);
        exponencialCheck.setForeground(Color.white);
        linearCheck.setForeground(Color.white);
        runningAvgCheck.setForeground(Color.white);
            
        textAreaForWritingOutData.setBackground(Color.black);
        textAreaForWritingOutData.setForeground(Color.white);
    }
    
    /**
     * Enables dark mode, by setting the background colors of elements
     * 
     * Also switches the image to the correct one
     * 
     * @author Patrik
     */
    private void setLight(){
        int r = 192;
        int g = 226;
        int b = 238;
        Color color = new Color(r,g,b);       
        try {
            darkModeToggleButton.setIcon(new ImageIcon(ImageIO.read(new URL("http://karakaip.web.elte.hu/EVP%20kepek/DarkMode.png"))));
            logo.setIcon(new ImageIcon(ImageIO.read(new URL("http://karakaip.web.elte.hu/EVP%20kepek/LogoB.png"))));
        } catch (IOException e) {
            textAreaForWritingOutData.setText("Egy vagy töb bkép betöltése nem sikerült.\nEllenőrizze, hogy van-e internetkapcsolat.");
        }
            
        grafikonValto.setForeground(Color.BLACK);
        
        graphicsPanel.setBackground(Color.white);
        setDataPanel.setBackground(color);
        logoPanel.setBackground(color);
        outputPanel.setBackground(color);
        fromDatePanel.setBackground(color);
        regionSearchPanel.setBackground(color);    
        
        numberOfInfectedLabel.setForeground(Color.black);
        selectCountryLabel.setForeground(Color.black);
        numberOfDeceasedLabel.setForeground(Color.black);
        numberOfInfected.setForeground(Color.black);
        
        exponencialCheck.setForeground(Color.black);
        linearCheck.setForeground(Color.black);
        runningAvgCheck.setForeground(Color.black);
            
        textAreaForWritingOutData.setBackground(Color.white);
        textAreaForWritingOutData.setForeground(Color.black);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new mainGUI().setVisible(true);
        });
    }   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton countrySearchButton;
    protected javax.swing.JComboBox<String> countrySelector;
    private javax.swing.JToggleButton darkModeToggleButton;
    private javax.swing.JCheckBox exponencialCheck;
    private com.toedter.calendar.JDateChooser fromDate;
    private javax.swing.JPanel fromDatePanel;
    protected javax.swing.JToggleButton grafikonValto;
    private javax.swing.JPanel graphicsPanel;
    private javax.swing.JCheckBox linearCheck;
    private javax.swing.JLabel loadingPictureIconLabel;
    protected javax.swing.JPanel loadingPicturePanel;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JLabel numberOfDeceased;
    private javax.swing.JLabel numberOfDeceasedLabel;
    private javax.swing.JLabel numberOfInfected;
    private javax.swing.JLabel numberOfInfectedLabel;
    private javax.swing.JPanel outputPanel;
    private javax.swing.JButton regionSearchButton;
    private javax.swing.JPanel regionSearchPanel;
    private javax.swing.JComboBox<String> regionSelect;
    private javax.swing.JSpinner runningAverageDays;
    private javax.swing.JCheckBox runningAvgCheck;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JLabel selectCountryLabel;
    private javax.swing.JPanel setDataPanel;
    protected javax.swing.JTextArea textAreaForWritingOutData;
    private com.toedter.calendar.JDateChooser tillDate;
    // End of variables declaration//GEN-END:variables
}