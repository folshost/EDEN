/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eden;

import java.util.ArrayList;

/**
 *
 * @author Maxwell
 */
public class Month {
    private ArrayList<Day> days;
    private MonthName monthName;
    public int month_id;
    
    public Month(MonthName name, int id){
        monthName = name;
        month_id = id;
    }
    
    public int get_month_id(){
        return month_id;
    }
}
