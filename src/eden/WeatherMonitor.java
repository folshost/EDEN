/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eden;

import eden.AlertType;

/**
 *
 * @author Maxwell
 */
public class WeatherMonitor {
    private final static String APIKey = "http://api.openweathermap.org/data/2."
            + "5/weather?id=4323288&APPID=c21a3eaefc11b898ce1dc12ecdfae4db&mode"
            + "=xml&units=imperial";
    

    

    //Maybe this class should actually implement checkAPI in the constructor, as that
   // would make the cohesion higher for this module 
    public static AlertType main(){
        URLConnection urlConnection = null;
        InputStreamReader input = null;
        try{
            URL url = new URL(WeatherMonitor.APIKey); //Credit where credit is due, this is all from 
            urlConnection = url.openConnection();    //https://www.tutorialspoint.com/java/java_url_processing.htm
                                                     //and http://crunchify.com/java-url-example-getting-text-from-url/
            if(urlConnection != null)
                urlConnection.setReadTimeout(60 * 1000);
            if(urlConnection != null && urlConnection.getInputStream() != null){
                input = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader buff = new BufferedReader(input);
                if(buff != null){
                    String line;
                    line = "<current><city id=\"4323288\" name=\"East Baton Rouge "
                            + "Parish\"><coord lon=\"-91.1\" lat=\"30.52\"></coord"
                            + "><country>US</country><sun rise=\"2016-11-10T12:26:"
                            + "42\" set=\"2016-11-10T23:09:42\"></sun></city><temp"
                            + "erature value=\"66.2\" min=\"66.2\" max="
                            + "\"66.2\" unit=\"fahrenheit\"></temperature><humi"
                            + "dity value=\"82\" unit=\"%\"></humidity><pressur"
                            + "e value=\"1022\" unit=\"hPa\"></pressure><wind>"
                            + "<speed value=\"4.7\" name=\"Gentle Breeze\"></sp"
                            + "eed><gusts></gusts><direction value=\"340\" code"
                            + "=\"NNW\" name=\"North-northwest\"></direction></"
                            + "wind><clouds value=\"1\" name=\"clear sky\"></cl"
                            + "ouds><visibility value=\"16093\"></visibility><p"
                            + "recipitation mode=\"no\"></precipitation><weathe"
                            + "r number=\"504\" value=\"clear sky\" icon=\"01n"
                            + "\"></weather><lastupdate value=\"2016-11-10T00:5"
                            + "3:00\"></lastupdate></current>\"";
 
                        System.out.println(line);
                        return checkAPIFeedback(line);
                }    
               
            }
            Object string = url.getContent();
            
            System.out.println(string.toString());
            
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }    
    
    
    public static AlertType checkAPIFeedback(String str){
        int speedSubStringIndex = str.indexOf("weather number=");
        System.out.println(str.substring(speedSubStringIndex));
        int weatherValue = Integer.parseInt(
                str.substring(speedSubStringIndex+16, speedSubStringIndex+19));
        System.out.println(weatherValue);
        AlertType alertType = AlertType.getAlertType(weatherValue);
        return alertType;
    } 
    
  
    
    public static void init(){
        //while(true){
            WeatherMonitor.main();
            
        //}
                            
            
        
    }
}
