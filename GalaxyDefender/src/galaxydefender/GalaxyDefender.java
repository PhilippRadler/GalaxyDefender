/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxydefender;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author philipp
 */
public class GalaxyDefender extends Application {
    private final Canvas canvas = new Canvas(300, 300);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        
        Rectangle rec = new Rectangle(50, 50, 100, 100);
        
        
        
        
        root.getChildren().add(rec);
        
        Scene scene = new Scene(root, 300, 300);

        gc.fillRect(((scene.getHeight()/2)-50), ((scene.getWidth()/2)-50), 50, 50);
        primaryStage.setTitle("Galaxy Defender");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
