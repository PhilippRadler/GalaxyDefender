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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Philipp Radler, Matthias Skopek, Bernhard Fröschl
 */
public class GalaxyDefender extends Application {
    final GameManager manager = new GameManager(5);

    @Override
    public void start(Stage primaryStage) {
        
        
        
        

        // Pane with the Area where the player will see the enemys, ...
        Pane playingArea = new Pane();
        setFiguresToPane(playingArea);
        // Pane where the score is shown and some other stuff
        Pane statsArea = new Pane();
        
        HBox box = new HBox();
        box.getChildren().addAll(playingArea, statsArea);
        Pane root = new Pane(box);
        Scene scene = new Scene(root, 300, 300);

        primaryStage.setTitle("Galaxy Defender");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setFiguresToPane(Pane playingArea) {
        //Testing with Circles and Rectangles
        playingArea.getChildren().add(new Circle(manager.getDefender().getPosition().getX(), 
                manager.getDefender().getPosition().getY(), 10));
        for(Figure alien : manager.getAliens()){
            playingArea.getChildren().add(new Rectangle(alien.getPosition().getX(), 
                alien.getPosition().getY(), 10, 10));
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }



}
