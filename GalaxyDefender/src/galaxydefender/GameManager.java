/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxydefender;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author philipp
 */
public class GameManager{

    final private Figure defender;
    final private ArrayList<Figure> aliens = new ArrayList<Figure>();


    
    public enum Direction {
        right, left, up, down
    };

    public GameManager(int numberOfAliens, Coordinates maxPaneSize) {
        defender = new Figure(new Coordinates(100, 100));

        for (int i = 0; i < numberOfAliens; i++) {
            aliens.add(new Figure(new Coordinates(15 * i, 10)));
        }
    }

    public Figure getDefender() {
        return defender;
    }

    public ArrayList<Figure> getAliens() {
        return aliens;
    }
    
    public Pane setFiguresToPane(Pane playingArea) {
        //Testing with Circles and Rectangles
        playingArea.getChildren().add(new Circle(defender.getPosition().getX(),
                defender.getPosition().getY(), 10));
        for (Figure alien : aliens) {
            playingArea.getChildren().add(new Rectangle(alien.getPosition().getX(),
                    alien.getPosition().getY(), 10, 10));
        }
        return playingArea;
    }
}
