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
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    final private Coordinates maxPlayingAreaSize = new Coordinates(500, 425);
    final GameManager manager = new GameManager(100, maxPlayingAreaSize);
    static Pane playingArea = null;

    @Override
    public void start(Stage primaryStage) {
        VBox box = initPanes();

        Pane root = new Pane(box);
        root.getStylesheets().add("resource/style.css");
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Galaxy Defender");
        

//        scene.setOnKeyTyped((KeyEvent event) -> {
//            if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.SPACE) {
//                Figure defender = manager.getDefender();
//                System.out.println("TESTSTSETSETSETWE");
//                if (event.getCode() == KeyCode.LEFT) {
//                    defender.setPosition(new Coordinates(defender.getPosition().getX() - 1, defender.getPosition().getY()));
//                } else if (event.getCode() == KeyCode.RIGHT) {
//                    defender.setPosition(new Coordinates(defender.getPosition().getX() + 1, defender.getPosition().getY()));
//                } else if (event.getCode() == KeyCode.SPACE) {
//                    //Defender's bullet
//                    manager.generateBullet(1);
//                    //Alien's bullet
//                    //manager.generateBullet(2);
//
//                }
//
//            }
//        }
//    );
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Figure defender = manager.getDefender();
                switch (event.getCode()) {
                    case RIGHT:
                        defender.setPosition(new Coordinates(defender.getPosition().getX() + 1, defender.getPosition().getY()));
                        System.out.println("TEST");
                        break;
                    case LEFT:
                        defender.setPosition(new Coordinates(defender.getPosition().getX() - 1, defender.getPosition().getY()));
                        System.out.println("TEST");
                        break;
                    case SPACE:
                        System.out.println("TEST");
                        //Defender's bullet
                        manager.generateBullet(1);
                        //Alien's bullet
                        manager.generateBullet(2);
                        break;
                }
                playingArea = manager.setFiguresToPane(playingArea);
            }
            
        });
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private VBox initPanes() {
        VBox box = new VBox();
        // Pane with the Area where the player will see the enemys, ...
        playingArea = manager.setFiguresToPane(new Pane());
        playingArea.setMinSize(maxPlayingAreaSize.getX(), maxPlayingAreaSize.getY());
        playingArea.setId("playingArea");
        manager.setFiguresToPane(playingArea);

        // Pane where the score is shown and some other stuff
        Pane statsArea = new Pane();
        statsArea.setMaxSize(500, 100);

        Rectangle statsRec = new Rectangle(0, 0, 500, 50);
        statsRec.setId("statsArea");
        statsArea.getChildren().add(statsRec);

        // Pane where some options are shown
        Pane optionsArea = new Pane();
        optionsArea.setMaxSize(500, 50);

        Rectangle optionsRec = new Rectangle(0, 0, 500, 25);
        optionsRec.setId("optionsArea");
        optionsArea.getChildren().add(optionsRec);

        box.getChildren().addAll(statsArea, playingArea, optionsArea);

        return box;
    }

}
