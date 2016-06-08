/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxydefender;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author philipp
 */
public class GameManager {

    final private Pane figureArea;
    final private Figure defender;
    final private ArrayList<Figure> aliens = new ArrayList<Figure>();
    final private ArrayList<Figure> bullets = new ArrayList<Figure>();
    final private Coordinates maxPlayingArea;
    final private Size defenderSize = new Size(100, 150);
    final private Size alienSize = new Size(20, 20);
    final private Size bulletSize = new Size(5, 10);

    public enum Direction {
        right, left, up, down
    };

    public GameManager(int numberOfAliens, Coordinates maxPaneSize) {
        defender = new Figure(new Coordinates(maxPaneSize.getX() / 2, maxPaneSize.getY() - 100), defenderSize, false);
        this.figureArea = new Pane();
        this.maxPlayingArea = maxPaneSize;

        int y = 0;
        int x = 0;
        for (int i = 0; i < numberOfAliens; i++) {
            if ((maxPaneSize.getX() - 15 * x) <= 10) {
                y += 50;
                x = 0;
            }
            aliens.add(new Figure(new Coordinates(50 * x, 10 + y), alienSize, true));

            x++;
        }
    }

    public Figure getDefender() {
        return defender;
    }

    public ArrayList<Figure> getAliens() {
        return aliens;
    }

    public void setFiguresToPane() {
        figureArea.getChildren().clear();

        defender.setFitHeight(150);
        defender.setFitWidth(100);
        defender.setX(defender.getPosition().getX());
        defender.setY(defender.getPosition().getY());
        figureArea.getChildren().add(defender);

        for (Figure alien : aliens) {
            alien.setFitHeight(50);
            alien.setFitWidth(38);
            alien.setX(alien.getPosition().getX());
            alien.setY(alien.getPosition().getY());
            figureArea.getChildren().add(alien);
        }

    }

    public Pane getFigureArea() {
        this.setFiguresToPane();
        return figureArea;
    }

    public ArrayList<Figure> getBullets() {
        return this.bullets;
    }

    public void addBullet(int type) {
        // Type 1 : Bullet shot from Defender; Type 2: Bullet shot from Aliens   

        if (type == 1) {
            Figure bullet = new Figure(new Coordinates(this.defender.getPosition().getX(), this.defender.getPosition().getY() - 230), bulletSize);
            bullets.add(bullet);
        }
    }

    public void moveBullets() {
        if (bullets.size() > 0) {
            for (Figure bullet : bullets) {
                if (bullet.getPosition().getY() > 0) {
                    bullet.setPosition(new Coordinates(bullet.getPosition().getX(), bullet.getPosition().getY() - 1));
                }
            }
        }
    }
}
