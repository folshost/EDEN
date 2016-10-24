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
public enum MonthName {
    JANUARY(0), FEBRUARY(1), MARCH(2), APRIL(3), MAY(4), JUNE(5), JULY(6), 
    AUGUST(7), SEPTEMBER(8), OCTOBER(9), NOVEMBER(10), DECEMBER(11);
    
    private int repr;
    
    private MonthName(int k){
        repr = k;
    }
    
    public int getRepr(){
        return repr;
    }
}
