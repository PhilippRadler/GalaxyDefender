package galaxydefender;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 *
 * @author philipp
 */
public class Figure extends Shape{
    Position position = new Position();
    Image backgroundImage;

    
    public Figure(Position position) {
        this.position=position;
    } 
    
    public Figure(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    
    
    
    
    @Override
    public com.sun.javafx.geom.Shape impl_configShape() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}