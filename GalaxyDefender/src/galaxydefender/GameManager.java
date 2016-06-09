/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxydefender;

import java.util.ArrayList;
import javafx.scene.layout.Pane;

/**
 *
 * @author philipp
 */
public class GameManager{

    final private Pane figureArea;
    final private Figure defender;
    final private ArrayList<Figure> aliens = new ArrayList<Figure>();
    final private ArrayList<Figure> bullets = new ArrayList<Figure>();
    final private Coordinates maxPlayingArea;
    final private Size defenderSize = new Size(100, 150);
    final private Size alienSize = new Size(20, 20);
    private int scorePerAlien = 10;
    final private Size bulletSize = new Size(5, 10);
    ScoreListener scoreListener = new ScoreListener() {
        @Override
        public void scored(int score) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public enum Direction {
        right, left, up, down
    };

    public GameManager(int numberOfAliens, Coordinates maxPaneSize, ScoreListener scoreListener) {
        defender = new Figure(new Coordinates(maxPaneSize.getX() / 2, maxPaneSize.getY() - 100), defenderSize, false);
        this.figureArea = new Pane();
        this.maxPlayingArea = maxPaneSize;
        this.scoreListener = scoreListener;
        this.scoreListener.scored(scorePerAlien);
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
            Figure bullet = new Figure(new Coordinates((this.defender.getPosition().getX()+(this.defenderSize.getWidth()/4)), this.defender.getPosition().getY()), bulletSize);
            bullets.add(bullet);
        }
    }

    public void moveBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).getPosition().getY() > 0) {
                bullets.get(i).setPosition(new Coordinates(bullets.get(i).getPosition().getX(), bullets.get(i).getPosition().getY() - 1));
                System.out.println(bullets.get(i).getPosition().getY());
            } else {
                //HIT DETECTION
                //....  Bei Hit die methode trigger Score aufrufen
                // triggerScore();
                
                if (bullets.get(i).getPosition().getY() <= 0) {
                    bullets.remove(i);
                    System.out.println("test");
                    
                }
            }
        }
    }
    
    public void triggerScore(){
        this.scoreListener.scored(scorePerAlien);
    }
}
