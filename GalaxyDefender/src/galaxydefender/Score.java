/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxydefender;

/**
 *
 * @author Bernhard
 */
public class Score {
    public String name = "";
    public int score = 0;
    
    Score(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
