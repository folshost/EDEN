/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eden;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.*;
import javafx.scene.Parent;
import javafx.scene.paint.Color;


/**
 *
 * @author Maxwell
 */
public class Alert {
    private AlertType groot;

    
    public void alerter() {
        Stage stage = new Stage();       //Help from: 
        //http://www.javaworld.com/article/2074461/core-java/simple-javafx-2-0-text-example.html
        Group rootGroup = new Group();               
        Scene scene = new Scene(rootGroup, 300, 250,Color.GOLD);        
        Text text = new Text(25, 50, "Extreme weather has been detected"
                + "in you area!\n\n\t\tIt is of the type:\n\t\t" + groot.getTypeDescrip());
        
        rootGroup.getChildren().add(text);
        
        stage.setTitle("Hello World!");
        stage.setScene(scene);
        stage.show();        
    }
    
    
    






    


    public Alert(AlertType kay){
        groot = kay;
        this.alerter();
    }
}
