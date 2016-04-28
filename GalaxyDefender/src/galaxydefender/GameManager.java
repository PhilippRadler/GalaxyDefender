/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxydefender;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author philipp
 */
public class GameManager {
    final private Figure defender;
    final private ArrayList<Figure> aliens = new ArrayList<Figure>();

    public GameManager(int numberOfAliens) {
        defender = new Figure(new Position(100, 100));
        
        for (int i = 0; i < numberOfAliens; i++) {
            aliens.add(new Figure(new Position(10, (5+i))));
        }
    }


    public Figure getDefender(){
        return defender;
    }
    public ArrayList<Figure> getAliens(){
        return aliens;
    }

}
