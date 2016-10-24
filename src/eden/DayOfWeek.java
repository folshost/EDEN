/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eden;

/**
 *
 * @author Maxwell
 */
public enum DayOfWeek {
    SUNDAY(0), MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), 
    SATURDAY(6);
    
    private int repr;
    private DayOfWeek(int k){
        repr = k;
    }
    
    public int getRepr(){
        return repr;
    }
}
