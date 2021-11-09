package graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Calculates and draws out the graphs
 *
 * @author Boncz Bence, Patrik 👌👌
 */
public class Graph {
    /**
    * Least squares function's implementation with a linear curve
    *
    * @param measuredValues the measured values
    * @param daysNumber the number of days
    * @return an array with the calculated values needed to draw the graph with
     * @throws java.lang.Exception java.lang.Exception 
    */
    public double[] leastSquares(int[] measuredValues, int daysNumber) throws Exception{ //Format: "yyyyy-mm-dd"
        double MeanY=MeanYCalc(measuredValues);
        double MeanX=MeanXCalc(daysNumber);
        System.out.println(MeanY+" Mean x: "+ MeanX);
        // M slope C Yintercept
        double numerirator = 0;
        double denomirator = 0;
        for (int i = 1; i <= daysNumber; i++) {
            numerirator += (i - MeanX)*(measuredValues[i-1] - MeanY); 
            denomirator += (i - MeanX)*(i - MeanX);
        }
        double m = numerirator / denomirator;
        double c = MeanY - (m*MeanX);
        double[] t = {m,c};
        return t;
    }
    /**
    * Y's average
    *
    * @param values ertekek
    * @return mean of Y
    */
    public double MeanYCalc(int[] values){
        double x=0;
        for (int i = 0; i < values.length; i++) {
            x+=values[i];
        }
        x/=values.length;
        return x;
    }
    /**
    * X's average
    *
    * @param values ertekek
    * @return mean of X
    */
    public double MeanXCalc(int values){
        double x=0;
        for (int i = 1; i <= values; i++) {
            x+=i;
        }
        x/=values;
        return x;
    }
    /**
     * Calculates the exponential function of the curve based on data's provided to it
     * 
     * @param values ertekek
     * @param days napok száma
     * @return an array with the calculated values needed to draw the graph with
     */
    public double[] exponencialisCalc (int[] values,int days){
        double[] t= new double[days];
        double xSum=0,x2Sum=0,ySum=0,XYSum=0;
        
        for (int i = 0; i < t.length; i++) {
            if (values[i] != 0) t[i]=Math.log(values[i]);
            else t[i]=Math.log(1);
            
        }
        for (int i = 0; i < t.length; i++) {
            xSum+=values[i];
            ySum+=t[i];
            x2Sum+=Math.pow(values[i], 2);
            XYSum+=values[i]*t[i];
            //System.out.println(xSum+"\n"+ySum+"\n"+x2Sum+"\n"+XYSum+"\n");
        }
        double slope = (days*XYSum-xSum*ySum)/(days*x2Sum-xSum*xSum);
        double YIntercept = (x2Sum*ySum-xSum*XYSum)/(x2Sum*days-xSum*xSum);
        double c = Math.pow(Math.E,YIntercept);
        
        double[] back = {c,slope}; 
        //System.out.println(c+" e^"+ slope+"x");
        return back;
    }
    /**
     * DrawsExponential function
     * 
     * @param g grafikus interface
     * @param c c*e^(mx)
     * @param slope c*e^(mx)
     * @param daysSpace space between days
     * @param h height of canvas
     * @param w width of canvas
     * @param marg margin of canvas
     * @param max biggest value
     */
    public void drawExponential(Graphics g,double c,double slope,int daysSpace, int h, int w, int marg, int max){
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(new Color(15, 191, 62));
        double a = c; //a
        double b = slope;  //b,     a*e^(bx) pl.: 47.29961595961426 e^8.587609698827512E-4x
           
            
        double maxX= Math.log(max/a)/b;
        double minX = marg;
        double xSkala = (maxX-minX)/(w-100);
            
        double maxY = a*Math.pow(Math.E, (b*maxX));
        double ySkala = maxY/(h-100);
            
        int currentX=marg;
        int curvenumber= 1;
            
        //System.out.println("X skála: " + xSkala);
        //System.out.println("Y skála: " + ySkala);
        //System.out.println("w: " + w + " h: " + h+"\n daysspace: " + daysSpace);
            
        for (double i = currentX; i < w-marg; i+=(daysSpace/curvenumber)) { //bejárja az x tengelyt 1 nap között curvenumber vonalat rajzol
            double yvalue = a*Math.pow(Math.E, (b*i*xSkala)); // a*e^(bx)
            double yvalueNext = a*Math.pow(Math.E, (b*(i+daysSpace/curvenumber)*xSkala)); // a*e^(bx)
            //System.out.println("yvalue= "+ yvalue +"\ni: " + i);             
            if (!((int)(h-marg-(yvalueNext/ySkala))<marg)) g2D.drawLine((int)i, (int)(h-marg-(yvalue/ySkala)), (int)(i+(daysSpace/curvenumber)), (int)(h-marg-(yvalueNext/ySkala)));
            //System.out.println("honnan: (" + i + "," +currentY+") \n hova: (" +(int)(currentX+(i)) + "," + (int)(h-50-(yvalue/ySkala))+")");
        }
   }
    /**
    * Returns the next day
    * 
    * @param curDate current date
    * @return The next date
    * @throws ParseException ParseException
    */
    public String getNextDate(String  curDate) throws ParseException {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = format.parse(curDate);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        final SimpleDateFormat formatformat = new SimpleDateFormat("MM-dd");
        return formatformat.format(calendar.getTime()); 
    }
    /**
    * Kirajzol mindent is
    * 
    * @param g graphics interface
    * @param w width of canvas
    * @param h height of canvas
    * @param days days number
    * @param values measured values
    * @param slope slope of linear line
    * @param Yintercept y intercept of linear line
    * @param startDate starting date
    * @param dark dark mode is enabled or not
    * @param nev daily cases
    * @param margin margin
     * @param egy Boolean decides if the graph will be drawn or not
     * @param ketto Boolean decides if the graph will be drawn or not
     * @param harom Boolean decides if the graph will be drawn or not
     * @param runningDayCount the number of how many days we are calculating the running average with
    */                                     
    public void drawing(Graphics g, int w,int h, int days, int[] values,double slope,double Yintercept,Date startDate, Boolean dark, List<String> nev, int margin, boolean egy, boolean ketto, boolean harom, int runningDayCount){
        Graphics2D g2D = (Graphics2D) g;         
        int marg = margin, osztas = 4,x=marg,max=0,vastagsag = 3,gombatmero=8;   
        clearAndFill(g2D, dark, w, h, vastagsag);
        prepare(g2D, dark, w, h, marg, vastagsag);            
        double daysSpace = ((w-2*marg)*1.0)/(days+1)*1.0; 
        max = maxkereses(values);            
        double Yskala= ((h-2*marg)*1.0)/max*1.0;
        //System.out.println("yskala= " +Yskala);             
        if (days>=1)yAxisDraw(g2D, h, marg, osztas, max);            
        SimpleDateFormat igazifincsidate = new SimpleDateFormat("MM-dd");
        String igaziNagyonFincsiMincsiSuperFaszaDate="Igazán álom az élet!";
        igaziNagyonFincsiMincsiSuperFaszaDate = igazifincsidate.format(startDate);         ///igaziNagyonFincsiMincsiSuperFaszaDate => Ez nélkül nem menne sehova ez !               
        x = sum(days, x, daysSpace);            
        if(egy&&days>1&&slope!=0)linDraw(g2D, marg, h, w, Yintercept, Yskala, x, daysSpace, days, slope, dark, vastagsag);   
        double[] t = exponencialisCalc(values, days);
        System.out.println("Slopeee: "+slope);
        if((ketto && days>1)&&!(0>slope))drawExponential(g, t[0], t[1], days, h, w, marg, max);   
        if(harom&&(days>1))avgDrawing(g2D, h, w, values, Yskala, daysSpace, runningDayCount,marg);   
        dayDraw(g2D, dark, marg, h, days, daysSpace, values, osztas, igaziNagyonFincsiMincsiSuperFaszaDate, gombatmero, Yskala, nev);    
    }
    /**
    * Returns the biggest number of an array
    * 
    * @param values values
    * @return max of the given numbers
    */
    private int maxkereses(int[] values){
        int max = 0;
        for (int i = 0; i < values.length; i++) {
            if(max<values[i]) max= values[i];
        }
        return max;
    }
    /**
    * Returns the sum of daysSpaces
    * 
    * @param days the number of days
    * @param x margin
    * @param daysSpace double
    * @return x
    */
    private int sum(int days, int x, double daysSpace){
        for (int i = 0; i <days; i++) {
                x+=daysSpace;
        }
        return x;
    }
    /**
     * Draws the axis of the canvas
     * 
     * @param g2D graphics interface
     * @param dark dark mode
     * @param w width
     * @param h height
     * @param marg margin
     * @param vastagsag thickness
     */
    private void prepare(Graphics2D g2D, Boolean dark, int w, int h, int marg, int vastagsag){         
            if (!dark) g2D.setColor(Color.BLACK);
            else g2D.setColor(Color.WHITE);
            g2D.drawLine(marg, marg, marg, h-marg);
            g2D.drawLine(marg, h-marg, w-marg, h-marg);
    }
    /**
     * Clears the g2D component
     * 
     * @param g2D graphics interface
     * @param dark dark mode
     * @param w width
     * @param h height
     * @param vastagsag thickness
     */
    private void clearAndFill(Graphics2D g2D, boolean dark, int w, int h, int vastagsag){
        g2D.clearRect(0, 0, w, h);
        g2D.setStroke(new BasicStroke(vastagsag));
        if (dark) g2D.setColor(Color.BLACK);
        else g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, w, h);
    }
    
    /**
     * Draws the Y axis lines
     * 
     * @param g2D graphics interface
     * @param h height
     * @param marg margin
     * @param osztas y axis divided into this number
     * @param max max of the values
     */
    private void yAxisDraw(Graphics2D g2D, int h, int marg, int osztas, int max){
        DecimalFormat numberFormat = new DecimalFormat("#.0");
        double y4=(h-2*marg)/osztas;
            double seged=(max*1.0)/osztas;
            for (int i = 1; i <= osztas; i++) {
                g2D.drawLine(marg-5, h-((int)y4*i)-marg, marg+5, h-(int)y4*i-marg);
                g2D.drawString(numberFormat.format((double)(seged*(osztas-(i-1))))+"", marg/15,((int)y4*i)+marg-(int)y4);
            }
    }
    /**
     * Draws the linear line
     * 
     * @param g2D graphics interface
     * @param marg margin
     * @param h height
     * @param w width
     * @param Yintercept intercept of Y axis
     * @param Yskala scale of Y axis
     * @param x margin+ daysspace
     * @param daysSpace space between days
     * @param days number of days
     * @param slope slope of the linear line
     * @param dark dark mode
     * @param vastagsag thickness
     */
    private void linDraw(Graphics2D g2D, int marg, int h, int w, double Yintercept, double Yskala, int x, double daysSpace,int days, double slope, Boolean dark, int vastagsag){
        g2D.setColor(Color.BLUE);
        g2D.drawLine(marg, h-((int)(Yintercept*Yskala)+marg), (int)(x+daysSpace), h-(marg+(int)((Yskala*(Yintercept+((days+1)*slope))))));
        if (dark) g2D.setColor(Color.BLACK);
        else g2D.setColor(Color.WHITE);
        g2D.fillRect(0, h-(marg-(vastagsag)+1), w, marg);
    }
    /**
     * Draws the days out on the X axis
     * 
     * @param g2D g2Dgraphics interface
     * @param dark dark mode
     * @param marg marg margin
     * @param h height
     * @param days number of days
     * @param daysSpace space between days
     * @param values measured values of days
     * @param osztas y axis divided into this number 
     * @param igaziNagyonFincsiMincsiSuperFaszaDate string that writes out the date format
     * @param gombatmero diameter of globe
     * @param Yskala scale of Y axis
     * @param nev daily cases
     */
    private void dayDraw(Graphics2D g2D, Boolean dark, int marg, int h, int days, double daysSpace, int[] values, int osztas, String igaziNagyonFincsiMincsiSuperFaszaDate, int gombatmero, double Yskala, List<String> nev){
        if (!dark) g2D.setColor(Color.BLACK);
            else g2D.setColor(Color.WHITE);
            int x = marg;
            for (int i = 0; i <days; i++) {
                x+=daysSpace;
                g2D.drawLine(x, h-(marg-5), x, h-(marg+5));
                if (days > 0){
                    if(values.length>3){
                        if(i%(values.length/osztas)==0)g2D.drawString(igaziNagyonFincsiMincsiSuperFaszaDate, (int)(x-marg/3.5), (int)(h-marg/4));
                    }else{
                        g2D.drawString(igaziNagyonFincsiMincsiSuperFaszaDate, (int)(x-marg/3.5), (int)(h-marg/2));
                    }
                }
                g2D.setColor(Color.red);
                if (values[i] != 0) g2D.fillOval(x-gombatmero/2, (int)(h-((int)values[i]*Yskala)-marg)-gombatmero/2, gombatmero, gombatmero);
                if (!dark) g2D.setColor(Color.BLACK);
                else g2D.setColor(Color.WHITE);
                try{
                    igaziNagyonFincsiMincsiSuperFaszaDate=getNextDate(nev.get(i));
                }catch(Exception e){System.out.println("Szia Sanyi!");}
            }
           // System.out.println("Meredekség: "+slope + "Kezdő: " + Yintercept);
           //System.out.println("Y skálázása: "+Yskala);
    }
    /**
     * draws the running average graph
     * 
     * @param g2D g2Dgraphics interface
     * @param h height
     * @param w width
     * @param values measured values
     * @param Yskalazas scale of y axis
     * @param daysSpace space between days
     * @param futoAtlag rdays the running average is measured
     * @param marg margin
     */
    public void avgDrawing(Graphics2D g2D,int h, int w, int[] values, double Yskalazas, double daysSpace, int futoAtlag,int marg){
        int a = marg;
        boolean  megakarokhalni=true;
        for (int i = 0; i < values.length; i++) {
            if(values[i]!=0)megakarokhalni=false;
        }
        double atlagelozo=marg;
        g2D.setColor(Color.orange);
        for (int i = 0; i < values.length; i++) {
            a+=(int)daysSpace;
            int counter =0;
            List<Integer> tomb = new ArrayList<>();
            for (int j = 0; j < futoAtlag; j++) {
                try{
                    tomb.add(values[i-j]);
                }catch(Exception e){
                    tomb.add(0);
                    counter++;
                }
            }
            double x = avg(tomb, counter);
            if(megakarokhalni==false)g2D.drawLine((int)(a-daysSpace), h-((int)(atlagelozo*Yskalazas)+marg), (int)(a), h-((int)(x*Yskalazas)+marg));
            atlagelozo=x;
        }
    }
    /**
     * Average calculator
     * 
     * @param t values
     * @param counter number of 0s counted in values
     * @return the average value
     */
    public double avg(List<Integer> t,int counter){
        double x = 0;
        for (int i = 0; i < t.size(); i++) {
            x+=t.get(i);
            //System.out.println(t[i]);
        }
        x/=t.size()-counter;
        return x;
    }
}
