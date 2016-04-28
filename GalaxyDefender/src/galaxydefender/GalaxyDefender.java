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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
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
        Pane playingArea = new Pane();
        
        StackPane root = new StackPane();

        Rectangle rec = new Rectangle(50, 50, 100, 100);

        final Arc arc = new Arc(10, 10, 50, 50, 45, 200);
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.GREENYELLOW);
        //dsajfasdklöfjaksf

        root.getChildren().addAll(rec, arc);

        Scene scene = new Scene(root, 300, 300);

        gc.fillRect(((scene.getHeight() / 2) - 50), ((scene.getWidth() / 2) - 50), 50, 50);
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
