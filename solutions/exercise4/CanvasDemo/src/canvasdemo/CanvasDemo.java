/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package canvasdemo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author angelacaicedo
 */
public class CanvasDemo extends Application {
    
    public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    CanvasTestFrame tf = new CanvasTestFrame();
    Scene scene = new Scene(tf, 600, 400);
    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent t) {
        System.exit(0);
      }     
    });
    stage.setScene(scene);
    stage.show();
    new Thread(tf).start();
  }
}
