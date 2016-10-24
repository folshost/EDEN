/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eden;

import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author Maxwell
 */
public class WateringCalendar {
    private int plotID;
    private int currentMonthID;
    private ArrayList<Month> calendarData;
    
    public Month getMonth(int month_ID){
        return calendarData.get(month_ID);
    }

    public WateringCalendar(){
        this.assessTime();
    }
    
    
    //Gets index of recorded months, and initializes them in the calendarData
    //ArrayList. Also determines currentMonthID
    public void assessTime(){
        FileInputStream in = null;
        
        try{
            in = new FileInputStream("Saves/" + plotID + "/index.txt");
            String temp;
            String inputLine2;
            while(temp = in.read()){
                inputLine2 = (String)in.read();
                calendarData.add(new Month());
            }
            
        }catch(FileNotFoundException e){
            System.err.println("File not found: " + e.getMessage());
        }
        
        
    }


}
