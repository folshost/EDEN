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
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.*;
import javafx.scene.Parent;


/**
 *
 * @author Maxwell
 */
public class Alert {
    private int groot;

    
    public void alerter() {
        Stage stage = new Stage();
            Button btn = new Button();
            btn.setText("Extreme weather has been detected in your area!");
            btn.setOnAction(new EventHandler<ActionEvent>() {
            
                @Override
                public void handle(ActionEvent event) {
                }
            });
        
            StackPane root = new StackPane();
            root.getChildren().add(btn);
        
            Scene scene = new Scene(root, 300, 250);
        
            stage.setTitle("Hello World!");
            stage.setScene(scene);
            stage.show();
        
        
        
        
    }
    
    
    






    


    public Alert(int kay){
        groot = kay;
        this.alerter();
    }
}
