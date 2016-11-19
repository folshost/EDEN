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
public interface DataManager<T> {
    
    public ArrayList<T> loadData(String filename);
    
}
