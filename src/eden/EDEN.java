
package eden;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

import java.text.ParseException;
import eden.PlotEditor.*;
/**
 *
 * @author Maxwell
 */
public class EDEN extends Application {
    PlotDirectory pD = new PlotDirectory();
    ImageView imgV;
    Button btnI;
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        try{
            pD.loadPlotList();
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
    	
    	Button btn;
    	
    	//HBox btnBox = new HBox();
    	//btnBox.setSpacing(20);
    	//btnBox.setPadding(new Insets(20));
        
        
        GridPane painBox = new GridPane();
        
    	imgV = new ImageView();
    	
        
        
    	BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
    	
    	//root.setCenter(btnBox);
    	//root.setCenter(imgV);
    	
        Text mainText = new Text(10, 10, "Welcome to Eden!");
        root.setTop(mainText);
        
    	for(int i = 0; i <= pD.getList().size() - 1; i++) {
            btn = new Button(pD.getList().get(i).getName());
            
            painBox.add(btn, 0, 2*i);
            
            Text btnText = new Text("   Last Watered " + pD.getList().get(i).getLastWatered());
            painBox.add(btnText, 20, 2*i);
            int k = i;
            //btnBox.getChildren().addAll(btn);
            btn.setOnAction(e->{
                try {
                        handleButtonAction(e,primaryStage, pD.getList().get(k));
                        root.setBottom(btnI);
                } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                }
            });
    	}
    	root.setCenter(painBox);
    	Scene scene = new Scene(root, 500, 600);
    	primaryStage.setTitle("EDEN");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
        
        Button weatherBtn = new Button("Check Weather");
        weatherBtn.setOnAction(k->{
            
                AlertType alertType = WeatherMonitor.main();
    	        if (alertType != null){
                    Stage stage = new Stage();
                    Group rootGroup = new Group();               
                    Scene alertScene = new Scene(rootGroup, 300, 250,Color.GOLD);        
                    Text text = new Text(25, 50, "Extreme weather has been detected"
                        + " in you area!\n\n\t\tIt is of the type:\n\t\t" + 
                    alertType.getTypeDescrip());
        
                    rootGroup.getChildren().add(text);
        
                    stage.setTitle("Alert!");
                    stage.setScene(alertScene);
                    stage.show();
                
            }
        });
        root.setRight(weatherBtn);
        
    	AlertType alertType = WeatherMonitor.main();
    	if (alertType != null){
            Stage stage = new Stage();
            Group rootGroup = new Group();               
            Scene alertScene = new Scene(rootGroup, 300, 250,Color.GOLD);        
            Text text = new Text(25, 50, "Extreme weather has been detected"
                + " in you area!\n\n\t\tIt is of the type:\n\t\t" + 
                alertType.getTypeDescrip());
        
            rootGroup.getChildren().add(text);
        
            stage.setTitle("Alert!");
            stage.setScene(alertScene);
            stage.show();
        } 
    }
    
    private void handleButtonAction(ActionEvent e, Stage stager, Plot plot) throws MalformedURLException {
        Image img;
        Button b = (Button) e.getSource();
        //String path = "file:C:\\Users\\Layne\\workspace\\EDEN\\plots\\" + b.getText() + ".bmp";
        //img = new Image(path);
        //imgV.setImage(img);
        btnI = new Button("Load");
        
        btnI.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                openPlotGUI(plot, stager);
            }
        });
    }

    
    public static void openPlotGUI(Plot plot, Stage primaryStage){
        
        Stage stage = new Stage();
        GridPane painBox = new GridPane();
                
        ActivePlot actPlot = new ActivePlot(plot);
        String texter = "";       
        Group rootGroup = new Group();               
        Scene activeScene = new Scene(rootGroup, 300, 250,Color.GOLD);
                
        for(int i = 0; i<actPlot.getPlotGrid().getLength();i++){
            System.out.println("Started " + i + " " + actPlot.getPlotGrid().getWidth());
            for(int j = 0; j< actPlot.getPlotGrid().getWidth(); j++){
            System.out.println("Started " + i + " " + j);
                for(Plant k : actPlot.getPlantList()){
                    if(k.getXCoordinate() == i && k.getYCoordinate() == j){
                        Button a = new Button("P");
                        a.setOnAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event){
                                //System.out.println(k.getPlantType().getName());
                            }
                                    
                        });
                        rootGroup.getChildren().add(a);
                        System.out.println("Finished " + i + " " + j);
                    }
                    else{
                        
                    }
                }   
                int f = i;
                int g = j;
                    Button a = new Button("-");
                        a.setOnAction(new EventHandler<ActionEvent>(){
                            @Override
                                public void handle(ActionEvent event){
                                    System.out.println( "Added plant to " + f + " " + g);
                                }
                                    
                        });
                        
                        painBox.add(a, i, j);
                        //painBox.add(a, i, j);
                        System.out.println("Finished " + i + "-" + j);
                
                
               
            }
        }
                
                
                
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        Text text = new Text(plot.getName());
        root.setTop(text);        
        root.setCenter(painBox);
        Scene scene = new Scene(root, 500, 600);
        
        primaryStage.setTitle(plot.getName());
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
           
        
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
