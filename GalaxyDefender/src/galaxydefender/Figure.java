package galaxydefender;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author philipp
 */
public class Figure extends ImageView{
    Coordinates position = new Coordinates();
    Size size;
    Image image;

    
    public Figure(Coordinates position, Size size) {
        image = new Image("resource/Rakete.png");
        super.setImage(image);
        this.position=position;
        this.size = size;
    } 

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
    

}
