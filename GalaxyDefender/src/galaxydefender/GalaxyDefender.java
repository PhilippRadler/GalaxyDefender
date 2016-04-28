/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxydefender;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Philipp Radler, Matthias Skopek, Bernhard Fröschl
 */
public class GalaxyDefender extends Application {
    final private Coordinates maxPlayingAreaSize = new Coordinates(200, 300);
    final GameManager manager = new GameManager(5, maxPlayingAreaSize);

    @Override
    public void start(Stage primaryStage) {
        
        
        
        
        // Pane with the Area where the player will see the enemys, ...
        Pane playingArea = manager.setFiguresToPane(new Pane());
        playingArea.setMinSize(maxPlayingAreaSize.getX(), maxPlayingAreaSize.getY());
        playingArea.setId("playingArea");
        manager.setFiguresToPane(playingArea);
        
        
        // Pane where the score is shown and some other stuff
        Pane statsArea = new Pane();
        statsArea.setMaxSize(100, 300);
        
        //testing 
        Rectangle rec= new Rectangle(0, 0, 100, 300);
        rec.setId("statsArea");
        statsArea.getChildren().add(rec);
        
        
        
        HBox box = new HBox();
        box.getChildren().addAll(playingArea, statsArea);
        Pane root = new Pane(box);
        root.getStylesheets().add("resource/style.css");
        Scene scene = new Scene(root, 300, 300);

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
