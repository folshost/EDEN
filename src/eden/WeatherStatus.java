/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eden;

import java.net.URL;
import java.io.*;
import java.net.URLConnection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author Maxwell
 */
public class WeatherStatus{
    private static String APIKey = "http://api.openweathermap.org/data/2.5/weather?id=4323288&APPID=c21a3eaefc11b898ce1dc12ecdfae4db&mode=xml&units=imperial";
    protected boolean errorCheck; // This will be used to see whether an Alert needs to be created
    protected AlertType type; //Here, EventType should be an enum used to talk about each different
                              // kind of weather event Eden will be looking for

    

    //Maybe this class should actually implement checkAPI in the constructor, as that
   // would make the cohesion higher for this module 
    public static void main(){
        StringBuilder sb = new StringBuilder();
        URLConnection urlConnection = null;
        InputStreamReader input = null;
        try{
            URL url = new URL(WeatherStatus.APIKey); //Credit where credit is due, this is all from 
            urlConnection = url.openConnection();    //https://www.tutorialspoint.com/java/java_url_processing.htm
                                                     //and http://crunchify.com/java-url-example-getting-text-from-url/
            if(urlConnection != null)
                urlConnection.setReadTimeout(60 * 1000);
            if(urlConnection != null && urlConnection.getInputStream() != null){
                input = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader buff = new BufferedReader(input);
                if(buff != null){
                    String line;
                    line = "<current><city id=\"4323288\" name=\"East Baton Rouge Parish\"><coord lon=\"-91.1\" lat=\"30.52\"></coord><country>US</country><sun rise=\"2016-11-10T12:26:42\" set=\"2016-11-10T23:09:42\"></sun></city><temperature value=\"66.2\" min=\"66.2\" max="
                            + "\"66.2\" unit=\"fahrenheit\"></temperature><humidity value=\"82\" unit=\"%\"></humidity><pressure value=\"1022\" unit=\"hPa\"></pressure><wind><speed value=\"4.7\" name=\"Gentle Breeze\"></speed><gusts></gusts><direction value=\"340\" code=\"NNW\" name=\"North-northwest\"></direction></wind><clouds value=\"1\" name=\"clear sky\"></clouds><visibility value=\"16093\"></visibility><precipitation mode=\"no\"></precipitation><weather number=\"504\" value=\"clear sky\" icon=\"01n\"></weather><lastupdate value=\"2016-11-10T00:53:00\"></lastupdate></current>\"";
 
                        System.out.println(line);
                        checkAPIFeedback(line);
                }    
               
            }
            Object string = url.getContent();
            
            System.out.println(string.toString());
            
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }    
    
    
    public static void checkAPIFeedback(String str){
        boolean precip = false;
        boolean highWinds = false;
        if(!str.contains( "precipitation mode=\"no\"" ))
            precip = true;
        int speedSubStringIndex = str.indexOf("weather number=");
        System.out.println(str.substring(speedSubStringIndex));
        int weatherValue = Integer.parseInt(
                str.substring(speedSubStringIndex+16, speedSubStringIndex+19));
        System.out.println(weatherValue);
        AlertType iteration = AlertType.getAlertType(weatherValue);
        if(iteration != null){
            Alert alert = new Alert(iteration);
        }
            
            
            
        
        
    }
    
    
    public boolean getErrorCheck(){
        return errorCheck;
    }
    
    public AlertType getType(){
        return type;
    } 
}
