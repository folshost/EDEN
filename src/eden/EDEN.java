
package eden;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;


/**
 *
 * @author Maxwell
 */
public class EDEN extends Application {
	PlotDirectory pD = new PlotDirectory();
	Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;
	ImageView imgV;
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
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
    	PlotDirectory pD = new PlotDirectory();
    	pD.loadPlotList();
    	
    	if (pD.getList().get(0) != null)
    		btn1 = new Button(pD.getList().get(0).getName());
    	else
    		btn1 = new Button("Empty");
    	if (pD.getList().get(1) != null)
    		btn2 = new Button(pD.getList().get(1).getName());
    	else
    		btn2 = new Button("Empty");
    	if (pD.getList().get(2) != null)
    		btn3 = new Button(pD.getList().get(2).getName());
    	else
    		btn3 = new Button("Empty");
    	if (pD.getList().get(3) != null)
    		btn4 = new Button(pD.getList().get(3).getName());
    	else
    		btn4 = new Button("Empty");
    	if (pD.getList().get(4) != null)
    		btn5 = new Button(pD.getList().get(4).getName());
    	else
    		btn5 = new Button("Empty");
    	if (pD.getList().get(5) != null)
    		btn6 = new Button(pD.getList().get(5).getName());
    	else
    		btn6 = new Button("Empty");
    	if (pD.getList().get(6) != null)
    		btn7 = new Button(pD.getList().get(6).getName());
    	else
    		btn7 = new Button("Empty");
    	
    	HBox btnBox = new HBox(btn1, btn2, btn3, btn4, btn5, btn6, btn7);
    	btnBox.setSpacing(20);
    	btnBox.setPadding(new Insets(20));
    	imgV = new ImageView();
    	
    	BorderPane root = new BorderPane();
    	root.setPadding(new Insets(10));
    	
    	root.setTop(btnBox);
    	root.setCenter(imgV);
    	
    	Scene scene = new Scene(root, 600, 600);
    	primaryStage.setTitle("EDEN");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    
    //private void handleButtonAction(ActionEvent event) {
    	//Image img;
    	
    	//if(event.getSource() == btn1)
    		//img = new Image("path/to/image" + pD.getList().get(0).getName());
    	//else if(event.getSource() == btn2)
    		//img = new Image("path/to/image" + pD.getList().get(1).getName());
    	//else if(event.getSource() == btn3)
    		//img = new Image("path/to/image" + pD.getList().get(2).getName());
    	//else if(event.getSource() == btn4)
    		//img = new Image("path/to/image" + pD.getList().get(3).getName());
    	//else if(event.getSource() == btn5)
    		//img = new Image("path/to/image" + pD.getList().get(4).getName());
    	//else if(event.getSource() == btn6)
    		//img = new Image("path/to/image" + pD.getList().get(5).getName());
    	//else if(event.getSource() == btn7)
    		//img = new Image("path/to/image" + pD.getList().get(6).getName());
    	//else
    		//img = null;
    	
    	//imgV.setImage(img);
    //}
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
