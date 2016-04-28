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
import javafx.stage.Stage;

/**
 *
 * @author Philipp Radler, Matthias Skopek, Bernhard Fröschl
 */
public class GalaxyDefender extends Application {

    private final Canvas canvas = new Canvas(300, 300);
    private GraphicsContext gc = canvas.getGraphicsContext2D();

    @Override
    public void start(Stage primaryStage) {
        GameManager manager = new GameManager();
        
        
        
        

        // Pane with the Area where the player will see the enemys, ...
        Pane playingArea = new Pane();
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
