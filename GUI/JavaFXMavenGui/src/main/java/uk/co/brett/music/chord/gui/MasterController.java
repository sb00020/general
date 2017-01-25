package uk.co.brett.music.chord.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MasterController implements Initializable {

	private static final Logger LOGGER = Logger.getLogger(MasterController.class.getName() );
	
	@FXML
	AnchorPane parentPane;
	
	@FXML
	GridPane gridPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		LOGGER.info("enter class");
		assert parentPane != null;
		assert gridPane !=null;
		
		ChordController cc = new ChordController();
		
		
	
		
		
//		try {
//			LOGGER.info("loading");
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("ChordDemo.fxml"));
//			LOGGER.info("loaded");
//			Parent root = loader.load();
//
//			ChordController cc = (ChordController) loader.getController();
//
//			ArrayList<ChordController> cControllers = new ArrayList<>();
//
//			for (int i = 0; i < 5; i++) {
//
//				cControllers.add(new ChordController());
//
//			}

			// parentPane

//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
