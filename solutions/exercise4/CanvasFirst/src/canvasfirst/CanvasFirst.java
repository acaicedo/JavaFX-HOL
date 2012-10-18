/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package canvasfirst;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author angelacaicedo
 */
public class CanvasFirst extends Application {

    @Override
    public void start(Stage primaryStage) {
        final Canvas canvas = new Canvas(250, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLUE);
        gc.fillRect(75, 75, 100, 100);

        


        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello Canvas!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
