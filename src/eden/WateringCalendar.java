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
    private ArrayList<Month> calendarData;
    
    public Month getMonth(int month_ID){
        return calendarData.get(month_ID);
    }



    public void assessTime(){
        FileInputStream in = null;
        
        try{
            in = new FileInputStream("Saves/" + plotID + "/index.txt");
            string temp;
            while()
            
        }
        
        
    }


}
