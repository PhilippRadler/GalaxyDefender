/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxydefender;

import java.util.ArrayList;

/**
 *
 * @author philipp
 */
public class GameManager {
    final private Figure defender;
    final private ArrayList<Figure> aliens = new ArrayList<Figure>();

    public GameManager(int numberOfAliens, Coordinates maxPaneSize) {
        defender = new Figure(new Coordinates(100, 100));
        
        for (int i = 0; i < numberOfAliens; i++) {
            aliens.add(new Figure(new Coordinates(15*i, 10)));
        }
    }


    public Figure getDefender(){
        return defender;
    }
    public ArrayList<Figure> getAliens(){
        return aliens;
    }

}
