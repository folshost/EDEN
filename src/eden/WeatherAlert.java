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
class WeatherAlert {
    private AlertType alert;
    
    public WeatherAlert(AlertType type){
        alert = type;
    }
    
    public void setAlert(AlertType type){
        alert = type;
    }
}
