package galaxydefender;

import javafx.scene.image.Image;

/**
 *
 * @author philipp
 */
public class Figure{
    Position position = new Position();
    Image backgroundImage;

    
    public Figure() {
        
    } 
    
    public Figure(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
    

}
