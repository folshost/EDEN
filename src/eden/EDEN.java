
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
    ImageView imgV;
    Button btnI;
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
<<<<<<< HEAD

    	PlotDirectory pD = new PlotDirectory();
=======
    	//PlotDirectory pD = new PlotDirectory(); // Not necessary because pD is already declared & initialized in the field
>>>>>>> refs/remotes/origin/master
    	pD.loadPlotList();
    	Button btn;
    	
    	HBox btnBox = new HBox();
    	btnBox.setSpacing(20);
    	btnBox.setPadding(new Insets(20));
    	imgV = new ImageView();
    	
    	BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
    	
    	root.setTop(btnBox);
    	root.setCenter(imgV);
    	
    	for(int i = 0; i <= pD.getList().size() - 1; i++) {
            btn = new Button(pD.getList().get(i).getName());
            btnBox.getChildren().addAll(btn);
            btn.setOnAction(e->{
                try {
                        handleButtonAction(e);
                        root.setBottom(btnI);
                } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                }
            });
    	}
    	
    	Scene scene = new Scene(root, 500, 600);
    	primaryStage.setTitle("EDEN");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
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
    
    private void handleButtonAction(ActionEvent e) throws MalformedURLException {
        Image img;
        Button b = (Button) e.getSource();
        String path = "file:C:\\Users\\Layne\\workspace\\EDEN\\plots\\" + b.getText() + ".bmp";
        img = new Image(path);
        imgV.setImage(img);
        btnI = new Button("Load");
        btnI.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("This should load " + b.getText() + ".");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
