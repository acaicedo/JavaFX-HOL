/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.html5;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author angelacaicedo
 */
public class SampleController implements Initializable {
    
    WebEngine engine;
    
    
    @FXML
    private Accordion conferenceAccordion;
    @FXML
    private WebView webView;
            
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createWebView();
        createConferences();
    }   
    
    private void createWebView() {
        engine = webView.getEngine();
        webView.getEngine().load("file:///Users/angelacaicedo/Work/JavaFX/JavaOne/JavaFX-HOLs/solutions/exercise3/JavaFX-HTML5/src/javafx/html5/content.html");
    }

    private void createConferences() {
        final TitledPane sf = createConference("JavaOne SF", 37.775057, -122.416534, "http://steveonjava.com/wp-content/uploads/2010/07/JavaOne-2010-Speaker.png");
        //"JavaOne India", 17.385371, 78.484268, "http://steveonjava.com/wp-content/uploads/2011/03/javaone-india.png");
        conferenceAccordion.getPanes().add(sf);
        conferenceAccordion.getPanes().add(createConference("OSCON", 45.515008, -122.693253, "http://steveonjava.com/wp-content/uploads/2011/05/oscon.png"));
        conferenceAccordion.getPanes().add(createConference("Devoxx", 51.206883, 4.44, "http://steveonjava.com/wp-content/uploads/2010/07/LogoDevoxxNeg150.png"));
        conferenceAccordion.getPanes().add(createConference("J-Fall", 52.219913, 5.474253, "http://steveonjava.com/wp-content/uploads/2011/11/jfall3.png"));
        conferenceAccordion.getPanes().add(createConference("JavaOne India", 17.385371, 78.484268, "http://steveonjava.com/wp-content/uploads/2011/03/javaone-india.png"));
        conferenceAccordion.getPanes().add(createConference("Jazoon", 47.382079, 8.528137, "http://steveonjava.com/wp-content/uploads/2010/04/jazoon.png"));
        conferenceAccordion.getPanes().add(createConference("GeeCON", 50.064633, 19.949799, "http://steveonjava.com/wp-content/uploads/2011/03/geecon.png"));
        sf.setExpanded(true);
        conferenceAccordion.setExpandedPane(sf);
        conferenceAccordion.expandedPaneProperty().addListener(new ChangeListener<TitledPane>() {
            public void changed(ObservableValue<? extends  TitledPane> ov, TitledPane t, TitledPane t1) {
                if (t1 != null) {
                    ((ConferencePane)t1).navigateTo();
                }
            }
        });
    }

    private ConferencePane createConference(String name, final double lat, final double lon, String imageUrl) {
        return new ConferencePane(name, new ImageView(new Image(imageUrl)), lat, lon);
    }

    public class ConferencePane extends TitledPane {
        private final double lat;
        private final double lon;

        private ConferencePane(String label, Node node, double lat, double lon) {
            super(label, node);
            this.lat = lat;
            this.lon = lon;
        }
        public void navigateTo() {
            engine.executeScript("moveMap(" + lat + ", " + lon + ");");
            engine.executeScript("addMarker(" + lat + ", " + lon + ");");
        }
    }
}
