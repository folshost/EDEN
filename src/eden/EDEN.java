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
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
/**
 *
 * @author Maxwell Reeser
 */
public class EDEN extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        
        Button btn2 = new Button();
        btn2.setText("Never!!!!");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        btn.setLayoutX(150);
        btn.setLayoutY(100);
        btn2.setLayoutX(150);
        btn2.setLayoutY(200);
        
        Pane root = new Pane();
        root.getChildren().add(btn);
        root.getChildren().add(btn2);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        AlertType alertType = WeatherMonitor.main();
        if(alertType != null){
            Stage stage = new Stage();
            Group rootGroup = new Group();               
            Scene alertScene = new Scene(rootGroup, 300, 250,Color.GOLD);        
            Text text = new Text(25, 50, "Extreme weather has been detected"
                + "in you area!\n\n\t\tIt is of the type:\n\t\t" + 
                alertType.getTypeDescrip());
        
            rootGroup.getChildren().add(text);
        
            stage.setTitle("Alert!");
            stage.setScene(alertScene);
            stage.show();
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
