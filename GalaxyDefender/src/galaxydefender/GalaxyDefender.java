/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxydefender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Philipp Radler, Matthias Skopek, Bernhard Fröschl
 */
public class GalaxyDefender extends Application implements ScoreListener {

    static private Coordinates maxPlayingAreaSize;
    static GameManager manager;
    static Pane figureArea = null;
    static Pane bulletArea = null;
    Label statsL;

    @Override
    public void start(Stage primaryStage) {
        final File f = new File("src/resource/Rocket.mp4");

        final Media m = new Media(f.toURI().toString());
        final MediaPlayer mp = new MediaPlayer(m);
        final MediaView mv = new MediaView(mp);

        final DoubleProperty width = mv.fitWidthProperty();
        final DoubleProperty height = mv.fitHeightProperty();

        width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));

        mv.setPreserveRatio(true);

        StackPane root = new StackPane();
        root.getChildren().add(mv);

        final Scene scene = new Scene(root, 960, 540);

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.show();

        mp.play();

        mp.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                showMenu(primaryStage);
            }
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void showMenu(Stage primaryStage) {
        Button play = new Button("Play");
        Button scores = new Button("Scores");
        Button close = new Button("Close");

        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                startGame(primaryStage);
            }
        });
        scores.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showScores(primaryStage);
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
        root.getChildren().add(menu);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Galaxy Defender");
        primaryStage.setScene(scene);
        scene.getStylesheets().add("resource/style.css");
        primaryStage.setFullScreen(false);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private void showScores(Stage primaryStage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        final TableView table = new TableView();
        final TableColumn nameColumn = new TableColumn("Name"),
                priceColumn = new TableColumn("Score");
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<Score, String>("name")
        );
        priceColumn.setCellValueFactory(
                new PropertyValueFactory<Score, Integer>(
                        "score")
        );
        table.getColumns().addAll(nameColumn, priceColumn);

        final ObservableList<Score> data
                = FXCollections.observableArrayList();
        ArrayList<Score> array = readScores();
        for (int i = 0; i < array.size(); i++) {
            data.add(array.get(i));
        }
        table.setItems(data);
        table.setPrefWidth(nameColumn.getWidth() + priceColumn.getWidth());
        Button backtoMenu = new Button("Zurück zum Menü");
        backtoMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMenu(primaryStage);
            }
        });

        Pane root = new Pane();

        VBox box = new VBox();
        box.getChildren().addAll(table, backtoMenu);

        root.getChildren().add(box);

        root.setPrefSize(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        root.setId("background");
        root.getStylesheets().add("resource/style.css");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private ArrayList<Score> readScores() {

        ArrayList<Score> data = new ArrayList<Score>();

        try {
            FileInputStream fis = new FileInputStream("highscores.csv");
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            while (br.ready()) {
                String scoreLine = br.readLine();
                String[] line = scoreLine.split(";");
                data.add(new Score(line[1], Integer.parseInt(line[2])));
            }
            br.close();
            isr.close();
            fis.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return data;
    }

    private void startGame(Stage primaryStage) {
        primaryStage.setFullScreen(false);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        VBox box = initPanes(primaryScreenBounds);

        StackPane root = new StackPane(box, bulletArea);
        root.getStylesheets().add("resource/style.css");
        Scene scene = new Scene(root);

        Task worker = createWorker();
        new Thread(worker, "mythread").start();

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

                        //Alien's bullet  --> not supported yet
                        //manager.generateBullet(2);
                        break;
                }
                figureArea = manager.getFigureArea();
            }

        });

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public void setBulletsToPane(ArrayList<Figure> bullets) {
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
        manager = new GameManager(maxPlayingAreaSize, this);

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

        Rectangle statsRec = new Rectangle(0, 0, screenBounds.getWidth(), 5);
        statsRec.setId("statsArea");
        statsArea.getChildren().add(statsRec);
        HBox labelBox = new HBox();
        statsL = new Label("0");
        statsL.setPrefHeight(60);
        Label textL = new Label("Punkte: ");
        textL.setPrefHeight(60);

        labelBox.getChildren().addAll(textL, statsL);
        VBox statsBox = new VBox();
        statsBox.setPrefHeight(65);
        statsBox.getChildren().addAll(labelBox, statsRec);
        statsL.setId("scoreL");
        textL.setId("scoreL");

        // Pane where some options are shown
        Pane optionsArea = new Pane();
        optionsArea.setMaxSize(screenBounds.getWidth(), 35);

        Rectangle optionsRec = new Rectangle(0, 0, screenBounds.getWidth(), 5);
        HBox buttonBox = new HBox();

        Button exit = new Button("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("tes");
            }
        });

        Button restart = new Button("Restart");
        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });

        buttonBox.setPrefHeight(30);

        exit.setDisable(true);
        restart.setDisable(true);

        buttonBox.getChildren().addAll(exit, restart);
        VBox options = new VBox();
        options.getChildren().addAll(optionsRec, buttonBox);
        optionsRec.setId("optionsArea");
        optionsArea.getChildren().add(options);

        box.getChildren().addAll(statsBox, figureArea, optionsArea);
        box.setId("background");

        return box;
    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Boolean call() {
                while (true) {
                    try {
                        setBulletsToPane(manager.getBullets());
                        manager.moveBullets();

                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GalaxyDefender.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }

        };
    }

    @Override
    public void scored(int score) {
        System.out.println("SCORE!!!!");
        //statsL.setText(Integer.toString((Integer.parseInt(statsL.getText()))+1));
    }
}
