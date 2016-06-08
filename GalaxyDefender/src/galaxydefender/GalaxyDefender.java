/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxydefender;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 *
 * @author Philipp Radler, Matthias Skopek, Bernhard Fröschl
 */
public class GalaxyDefender extends Application {

    static private Coordinates maxPlayingAreaSize;
    static GameManager manager;
    static Pane figureArea = null;
    static Pane bulletArea = null;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setFullScreen(true);
        Button play = new Button("Play");
        Button scores = new Button("Scores");
        Button close = new Button("Close");

        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startGame(primaryStage);
            }
        });
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        play.setPrefSize(320, 80);
        scores.setPrefSize(320, 80);
        close.setPrefSize(320, 80);

        play.setGraphicTextGap(20);
        play.setFont(Font.font("Arial", 20));
        scores.setFont(Font.font("Arial", 20));
        scores.setGraphicTextGap(20);
        close.setGraphicTextGap(20);
        close.setFont(Font.font("Arial", 20));

        VBox menu = new VBox();
        menu.getChildren().addAll(play, scores, close);
        menu.setSpacing(150);
        menu.alignmentProperty().set(Pos.CENTER);

        StackPane root = new StackPane();
        root.setId("background");
        root.getChildren().addAll(menu);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Galaxy Defender");
        primaryStage.setScene(scene);
        scene.getStylesheets().add("resource/style.css");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void startGame(Stage primaryStage) {

        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Galaxy Defender");

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        VBox box = initPanes(primaryScreenBounds);

        StackPane root = new StackPane(box, bulletArea);
        root.getStylesheets().add("resource/style.css");
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Figure defender = manager.getDefender();
                switch (event.getCode()) {
                    case RIGHT:
                        if (defender.getPosition().getX() < scene.getWidth()) {
                            defender.setPosition(new Coordinates(defender.getPosition().getX() + 15, defender.getPosition().getY()));
                        }

                        //Performancetechnisch Besser -> ImageView X-Position ändern und dann die Zeile 
                        //"playingArea = manager.setFiguresToPane(playingArea);" löschen
                        //defender.setX(defender.getX() + 10);
                        System.out.println("Rechts");
                        break;
                    case LEFT:
                        defender.setPosition(new Coordinates(defender.getPosition().getX() - 15, defender.getPosition().getY()));

                        //Performancetechnisch Besser -> ImageView X-Position ändern und dann die Zeile 
                        //"playingArea = manager.setFiguresToPane(playingArea);" löschen
                        //defender.setX(defender.getX() - 10);
                        System.out.println("Links");
                        break;
                    case SPACE:
                        System.out.println("Schuss");
                        //Defender's bullet

                        manager.addBullet(1);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                while (manager.getBullets().size() > 0) {
                                    manager.moveBullets();
                                    setBulletsToPane(manager.getBullets());
                                    try {
                                        sleep(100);
                                    } catch (InterruptedException ex) {
                                        System.out.println(ex);
                                    }
                                }
                            }
                        });
                        //Alien's bullet  --> not supported yet
                        //manager.generateBullet(2);
                        break;
                }
                figureArea = manager.getFigureArea();
            }

        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setBulletsToPane(ArrayList<Figure> bullets) {
        if (bulletArea.getChildren().size() > 0) {
            bulletArea.getChildren().clear();
        }
        for (Figure bullet : bullets) {
            bulletArea.getChildren().add(new Rectangle(bullet.getPosition().getX(), bullet.getPosition().getY(), 10, 50));
        }

    }

    private VBox initPanes(Rectangle2D screenBounds) {
        VBox box = new VBox();
        box.prefHeight(screenBounds.getHeight());
        box.prefWidth(screenBounds.getWidth());

        maxPlayingAreaSize = new Coordinates((int) screenBounds.getWidth(), (int) screenBounds.getHeight() - 100);
        manager = new GameManager(100, maxPlayingAreaSize);

        // Pane with the Area where the player will see the enemys, ...
        figureArea = manager.getFigureArea();
        figureArea.setMinSize(maxPlayingAreaSize.getX(), maxPlayingAreaSize.getY());
        figureArea.setId("figureArea");

        //Pane with bullets
        bulletArea = new Pane();
        bulletArea.setMinSize(maxPlayingAreaSize.getX(), maxPlayingAreaSize.getY());
        bulletArea.setId("bulletArea");

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

        box.getChildren().addAll(statsArea, figureArea, optionsArea);

        return box;
    }

}
