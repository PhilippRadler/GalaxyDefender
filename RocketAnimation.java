import java.util.concurrent.CountDownLatch;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RocketAnimation extends Application
{
    PathTransition anim2 = new PathTransition();
    PathTransition anim = new PathTransition();
   @Override
   public void start(Stage primaryStage)
   {
       
      ImageView Rocket = new ImageView();
      Rocket.setImage(new Image("Rakete.png"));
      Rocket.setFitHeight(150);
      Rocket.setFitWidth(100);
      Rocket.setX(Rocket.getImage().getWidth() / 2);
      Rocket.setY(0);
     StackPane pane = new StackPane();
      ImageView earth = new ImageView();
      earth.setImage(new Image("Erde.png"));
      earth.setFitHeight(1080);
      earth.setFitWidth(1920);
       ImageView planet = new ImageView();
      planet.setImage(new Image("Planet.png"));
      planet.setFitHeight(1080);
      planet.setFitWidth(1920);
      

      PathElement[] path = 
      {
         new MoveTo(0, 800),
         new LineTo(0, 0),
new MoveTo(0,0),
         new ClosePath()
      };
      Path Line = new Path();
      Line.getElements().addAll(path);
      
      PathElement[] path2 = 
      {
         new MoveTo(0,0),
         new LineTo(0, 0),
         new MoveTo(0,1080),
         new ClosePath()
      };
      Path Line2=new Path();
      Line2.getElements().addAll(path2);
       
      anim2.setNode(earth);
      anim2.setPath(Line);
      anim2.setDuration(new Duration(4000));
      anim2.setCycleCount(0);
      anim2.setRate(1);
      anim2.setInterpolator(Interpolator.EASE_IN);
      anim.setAutoReverse(true);

      anim.setNode(Rocket);
      anim.setPath(Line);
      anim.setDuration(new Duration(4000));
      anim.setCycleCount(0);
      anim.setRate(1);
      anim.setInterpolator(Interpolator.EASE_IN);
      anim.setAutoReverse(false);
      anim.getOnFinished();
      

anim2.play();
anim.play();
   

       Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
       
      Group root = new Group();
      root.getChildren().addAll(Line2,earth,Line,Rocket);
      
      pane.getChildren().add(root);
      
      pane.setMaxHeight(primaryScreenBounds.getHeight());
      System.out.println(primaryScreenBounds.getHeight());
      pane.setMaxWidth(primaryScreenBounds.getWidth());
      System.out.println(primaryScreenBounds.getWidth());
      pane.setId("pane");
      
      Scene scene = new Scene(pane);
      scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
     

      primaryStage.setTitle("PathTransition Demo");
      primaryStage.setScene(scene);
      primaryStage.setFullScreen(true);
      primaryStage.show();
   }
}