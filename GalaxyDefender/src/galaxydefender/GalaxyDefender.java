/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxydefender;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;

/**
 *
 * @author Philipp Radler, Matthias Skopek, Bernhard Fröschl
 */
public class GalaxyDefender extends Application {

    static private Coordinates maxPlayingAreaSize;
    static GameManager manager;
    static Pane playingArea = null;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Galaxy Defender");
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        VBox box = initPanes(primaryScreenBounds);

        
        Pane root = new Pane(box);
        root.getStylesheets().add("resource/style.css");
        Scene scene = new Scene(root);
        

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Figure defender = manager.getDefender();
                switch (event.getCode()) {
                    case RIGHT:
                        //defender.setPosition(new Coordinates(defender.getPosition().getX() + 10, defender.getPosition().getY()));
                        defender.setX(defender.getX() + 10);
                        System.out.println("TEST");
                        break;
                    case LEFT:
                        //defender.setPosition(new Coordinates(defender.getPosition().getX() - 10, defender.getPosition().getY()));
                        defender.setX(defender.getX() - 10);
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
                //playingArea = manager.setFiguresToPane(playingArea);
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

    private VBox initPanes(Rectangle2D screenBounds) {
        VBox box = new VBox();
        box.prefHeight(screenBounds.getHeight());
        box.prefWidth(screenBounds.getWidth());
        
        maxPlayingAreaSize = new Coordinates((int)screenBounds.getWidth(), (int)screenBounds.getHeight()-100);
        manager = new GameManager(100, maxPlayingAreaSize);
        
        
        
        // Pane with the Area where the player will see the enemys, ...
        playingArea = manager.setFiguresToPane(new Pane());
        playingArea.setMinSize(maxPlayingAreaSize.getX(), maxPlayingAreaSize.getY());
        playingArea.setId("playingArea");
        manager.setFiguresToPane(playingArea);

        // Pane where the score is shown and some other stuff
        Pane statsArea = new Pane();
        statsArea.setMaxSize(screenBounds.getWidth(), 65);

        Rectangle statsRec = new Rectangle(0, 0, screenBounds.getWidth(), 65);
        statsRec.setId("statsArea");
        statsArea.getChildren().add(statsRec);

        // Pane where some options are shown
        Pane optionsArea = new Pane();
        optionsArea.setMaxSize(screenBounds.getWidth(), 35);

        Rectangle optionsRec = new Rectangle(0, 0, screenBounds.getWidth(), 35);
        optionsRec.setId("optionsArea");
        optionsArea.getChildren().add(optionsRec);

        box.getChildren().addAll(statsArea, playingArea, optionsArea);

        return box;
    }

}
