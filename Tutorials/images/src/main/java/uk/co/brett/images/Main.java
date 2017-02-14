package uk.co.brett.images;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import gov.nasa.worldwind.WorldWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLJPanel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.stage.WindowEvent;
import uk.co.wwjjavafx.app.controller.WWJController;


/**
 *
 * @author Serge
 */
public class Main extends Application {

    protected static WorldWindow wwd;
    protected WWJController ctrl;
    protected FXMLLoader loader;
    protected Scene scene;
    protected StackPane root;

    public Main() throws MalformedURLException, IOException {
    	loader = new FXMLLoader(new URL(Main.class.getResource("WWJGui.fxml").toExternalForm()));
        try {
            root = FXMLLoader.load(new URL(Main.class.getResource("WWJGui.fxml").toExternalForm()));
            ctrl = loader.getController();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        scene = new Scene(root);
        SwingNode swingNode = new SwingNode();
        wwd = new WorldWindowGLJPanel();
        wwd.setModel(new BasicModel());
        swingNode.setContent((WorldWindowGLJPanel) wwd);
        ctrl.centerStackPane.getChildren().add(swingNode);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent event) -> {
            System.exit(1);
        });
        stage.show();
    }

    public static WorldWindow getWWD() {
        return wwd;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}


//
//public class Main extends Application { 
//
//    
//	public static void main(String[] args) {
//		
//			launch(args);
//		}
//
//		@Override
//		public void start(Stage stage) throws IOException {
//			stage.setTitle("Chords");
//
//			AnchorPane layout = FXMLLoader
//					.load(new URL(Main.class.getResource("MainController.fxml").toExternalForm()));
//			stage.setScene(new Scene(layout));
//			stage.show();
//
//		}
//	
//	
//	
//}
