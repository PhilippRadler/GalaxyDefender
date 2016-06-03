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
    final private ArrayList<Figure> bullets = new ArrayList<Figure>();
    final private Coordinates maxPlayingArea;
    final private Size defenderSize = new Size(100, 150);
    final private Size alienSize = new Size(20, 20);
    final private Size bulletSize = new Size(5, 10);

    
    public enum Direction {
        right, left, up, down
    };

    public GameManager(int numberOfAliens, Coordinates maxPaneSize) {
        defender = new Figure(new Coordinates(maxPaneSize.getX()/2, maxPaneSize.getY()-100), defenderSize, false);
        this.maxPlayingArea = maxPaneSize;
        
        int y = 0;
        int x = 0;
        for (int i = 0; i < numberOfAliens; i++) {
            if((maxPaneSize.getX()-15*x) <=10){
                y += 50;
                x=0;
            }
            aliens.add(new Figure(new Coordinates(50 * x, 10+y), alienSize, true));
            
            x++;
        }
    }

    public Figure getDefender() {
        return defender;
    }

    public ArrayList<Figure> getAliens() {
        return aliens;
    }
    
    public Pane setFiguresToPane(Pane playingArea) {
        playingArea.getChildren().clear();
        //Testing with Circles and Rectangles
//        playingArea.getChildren().add(new Rectangle(defender.getPosition().getX(),
//                defender.getPosition().getY(), defender.getSize().getWidth(), defender.getSize().getHeight()));
//        for(Figure alien : aliens) {
//            playingArea.getChildren().add(new Rectangle(alien.getPosition().getX(),
//                    alien.getPosition().getY(), alien.getSize().getWidth(), alien.getSize().getHeight()));
//        }


        //Testing
        defender.setFitHeight(150);
        defender.setFitWidth(100);
        defender.setX(defender.getPosition().getX());
        defender.setY(defender.getPosition().getY());
        playingArea.getChildren().add(defender);
        
        
        for(Figure alien : aliens) {
            alien.setFitHeight(50);
            alien.setFitWidth(38);
            alien.setX(alien.getPosition().getX());
            alien.setY(alien.getPosition().getY());
            playingArea.getChildren().add(alien);
        }
        
        
        
        return playingArea;
    }
    public void paintBullets(){
        for(Figure bullet : bullets){
            //.getChildren().add(new Line(bullet.getPosition().getX(), bullet.getPosition().getY(), bullet.getPosition().getX(), bullet.getPosition().getY()-1));
        }
    }
    public void generateBullet(int type){
        // Type 1 : Bullet shot from Defender; Type 2: Bullet shot from Aliens   
        
        if(type == 1){
            Figure bullet = new Figure(new Coordinates(this.defender.getPosition().getX(), this.defender.getPosition().getY()-1), bulletSize);
            bullets.add(bullet);
            try{
                new Thread(){
                    @Override
                    public void run(){
                        while(bullet.getPosition().getY() > 0){
                            bullet.setPosition(new Coordinates(bullet.getPosition().getX(), bullet.getPosition().getY()-1));
                            paintBullets();
                            try {
                                wait(100);
                            } catch (InterruptedException ex) {
                                System.out.println("Something with bullet-timeout from defender");
                            }
                        }
                        this.interrupt();
                    }
                };
            }catch(Exception e){
                System.out.println("Problem with the flight of a bullet from defender");
            }
            
        }else{
            Figure bullet = new Figure(new Coordinates(50, 50), bulletSize);
            bullets.add(bullet);
            try{
                new Thread(){
                    @Override
                    public void run(){
                        while(bullet.getPosition().getY() < defender.getPosition().getY()){
                            bullet.setPosition(new Coordinates(bullet.getPosition().getX(), bullet.getPosition().getY()+1));
                            paintBullets();
                            try {
                                wait(100);
                            } catch (InterruptedException ex) {
                                System.out.println("Something with bullet-timeout from alien");
                            }
                        }
                        
                        System.out.println("You are dead!!!!");
                        System.out.println("You are dead!!!!");
                        System.out.println("You are dead!!!!");
                        System.out.println("You are dead!!!!");
                        System.out.println("You are dead!!!!");
                        System.out.println("You are dead!!!!");
                        System.out.println("You are dead!!!!");
                        System.out.println("You are dead!!!!");
                        this.interrupt();
                    }
                };
            }catch(Exception e){
                System.out.println("Problem with the flight of a bullet from alien");
            }
            
            
        }
    }
}
