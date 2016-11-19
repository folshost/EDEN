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
public enum AlertType {
    THUNDERSTORM(212, "Thunderstorm"), HAIL(906,"Hail"),
    EXTREMERAIN(504,"Extreme Rain"), TORNADO(900, "Tornado"),
    TROPICALSTORM(901,"Tropical Storm"), HURRICANE(902, "Hurricane");
    
    private int typeID;
    private String typeDescrip;
    
    private AlertType(int k,String bob){
        typeID = k;
        typeDescrip = bob;
    }
    
    
    public int getTypeID(){
        return typeID;
    }
    
    public String getTypeDescrip(){
        return typeDescrip;
    }
    
    
    public static AlertType getAlertType(int k){
        switch(k){
            case 212:
                return AlertType.THUNDERSTORM; 
            case 906:
                return AlertType.HAIL;
            case 504:
                return AlertType.EXTREMERAIN;
            case 900:
                return AlertType.TORNADO;
            case 901:
                return AlertType.TROPICALSTORM;
            case 902:
                return AlertType.HURRICANE;
            default:
                return null;
        }
    }
}
