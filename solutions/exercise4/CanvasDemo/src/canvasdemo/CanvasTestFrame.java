/*
 * Demo of use of Canvas node in JavaFX
 */
package canvasdemo;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.control.SliderBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextBuilder;

/**
 * Test frame for Canvas manipulation
 *
 * @author simonri
 */
public class CanvasTestFrame extends Parent implements Runnable {

    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;
    private Canvas canvas;
    private Slider xSlider;
    private Slider ySlider;
    private Slider colourSlider;
    
    private double angle = Math.toRadians(30);
    private int dAngle = 30;
    private GraphicsContext gc;
    private double width;
    private double height;
    private int x = 0;
    private int y = 0;
    private ArrayList<Point2D> trail = new ArrayList<>();

    
    private DoubleBinding redDB;
    private DoubleBinding blueDB;
            
    /**
     * Constructor
     */
    public CanvasTestFrame() {
        
        width = 200;
        height = 200;
        createSliders();
        createBindings();
        createLabels();
        createCanvas();
        
    }

    private void createCanvas() {
        /* Create the canvas and get the GraphicsContext ready for all updates */
        canvas = new Canvas(200, 200);
        canvas.setTranslateX(150);
        canvas.setTranslateY(30);
        getChildren().add(canvas);
        
        //Create the binding with the sliders
        canvas.heightProperty().bind(ySlider.valueProperty());
        canvas.widthProperty().bind(xSlider.valueProperty());
        
        gc = canvas.getGraphicsContext2D();
        updateCanvas();
    }

    private void createLabels(){
        Font f = new Font(14);
        
        /* Label for canvas width slider */
        getChildren().add(TextBuilder.
                create().
                text("Horizontal Size").
                translateX(170).
                translateY(380).
                font(f).
                build());

        /* Label for canvas height slider */
        getChildren().add(TextBuilder.
                create().
                text("Vertical Size").
                translateX(-5).
                translateY(125).
                font(f).
                rotate(270).
                build());

        /* Label for colour control slider */
        getChildren().add(TextBuilder.
                create().
                text("BLUE").
                translateX(355).
                translateY(362).
                font(f).
                build());
        getChildren().add(TextBuilder.
                create().
                text("RED").
                translateX(550).
                translateY(362).
                font(f).
                build());
        getChildren().add(TextBuilder.
                create().
                text("Ball Bolour").
                translateX(440).
                translateY(380).
                font(f).
                build());
    }
    
    private void createBindings(){
        
        //Bind red and blue color to the colourSlider
        redDB = new DoubleBinding() {
            {
                super.bind(colourSlider.valueProperty());
            }
            @Override
            protected double computeValue() {
                return colourSlider.getValue()/100.0;
            }
        };

        blueDB = new DoubleBinding() {
            {
                super.bind(colourSlider.valueProperty());
            }
            @Override
            protected double computeValue() {
                return (100.0 - colourSlider.getValue())/100.0;
            }
        };
    }
    
    
    private void createSliders() {
        
        
        //Create Sliders
        /* Slider to control canvas width */
        xSlider = SliderBuilder.create().
                min(200).
                max(400).
                orientation(Orientation.HORIZONTAL).
                translateX(150).
                translateY(350).
                build();
        
        /* Slider to control canvas height */
        ySlider = SliderBuilder.create().
                min(200).
                max(300).
                orientation(Orientation.VERTICAL).
                translateX(50).
                translateY(50).
                build();
        ySlider.setRotate(180);
        
        /* Slider to control the colour of the ball in the canvas */
        colourSlider = SliderBuilder.create().
                min(0).
                max(100).
                orientation(Orientation.HORIZONTAL).
                translateX(400).
                translateY(350).
                build();
        
        //Add Sliders 
        getChildren().add(xSlider);
        getChildren().add(ySlider);
        getChildren().add(colourSlider);

        
    }


    /**
     * Update the canvas object moving time forward
     */
    private void updateCanvas() {
        
        
        /* Clear the canvas and draw a border */
        width = canvas.getWidth();
        height = canvas.getHeight();
        gc.clearRect(0, 0, width, height);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(1, 1, width - 2, height - 2);

        /* If the ball trail size is 10 remove the first element so we keep it 
         * a constant length
         */
        if (trail.size() == 10) {
            trail.remove(0);
        }

        int i = 1;

        /**
         * Draw the elements of the trail of the ball
         */
        for (Point2D p : trail) {
            double opacity = i * 0.1;
            gc.setFill(new Color(redDB.get(), 0, blueDB.get(), opacity));
            gc.fillOval(p.getX(), p.getY(), 10, 10);
            i++;
        }

        /**
         * Update the position of the ball
         */
        x += (5 * Math.cos(angle));
        y += (5 * Math.sin(angle));
        double degAngle = Math.toDegrees(angle);

        if (x > (width - 10)) {
            if (degAngle < 180) {
                degAngle = 180 - degAngle;
            } else {
                degAngle = 180 + dAngle;
            }
        }

        if (x < 0) {
            if (degAngle > 180) {
                degAngle = 360 - dAngle;
            } else {
                degAngle = dAngle;
            }
        }

        if (y > (height - 10)) {
            if (degAngle > 90) {
                degAngle = 360 - degAngle;
            } else {
                degAngle = 360 - dAngle;
            }
        }

        if (y < 0) {
            if (degAngle > 270) {
                degAngle = dAngle;
            } else {
                degAngle = 180 - dAngle;
            }
        }

        angle = Math.toRadians(degAngle);

        /* Draw the ball */
        gc.setFill(new Color(redDB.get(), 0, blueDB.get(), 1.0));
        gc.fillOval(x, y, 10, 10);
        trail.add(new Point2D(x, y));
    }

    /**
     * Run method executing in its own thread
     */
    @Override
    public void run() {
        /**
         * In order to update things correctly we must use the JavaFX
         * application thread to do the updates. This thread puts a job onto
         * that thread every 40ms. This should probably use the Worker class.
         */
        while (true) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    updateCanvas();
                }
            });
            try {
                Thread.sleep(40);
            } catch (InterruptedException ie) {
                // Ignore
            }
        }
    }
}
