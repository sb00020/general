package uk.co.brett.music;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import uk.co.trickster.music.generator.ProgressionVariables;

public class FXMLController implements Initializable {

	ProgressionVariables pv;
    
    
    @FXML
    private TextArea displayBox;
    
    @FXML
    private Button nudgeUp;
    
    @FXML
    private Button nudgeDown;
    
    @FXML
    private Button regenerate;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @FXML
    private void handleRegenerateAction(ActionEvent event) {
    	pv = ProgressionVariables.generateNewKey(pv);
        System.out.println(pv.getKey());
        displayBox.setText(pv.getKey());
    }
    
    @FXML
    private void handleNudgeUpAction(ActionEvent event) {
        System.out.println("Nudge Up!");
    }
    
    @FXML
    private void handleNudgeDownAction(ActionEvent event) {
        System.out.println("Nudge Down!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pv = new ProgressionVariables();
        displayBox.setText(pv.getKey());
        
    }

	public static AnchorPane getLayout() throws MalformedURLException, IOException {
		
		URL var = FXMLController.class.getResource("FXMLController.fxml");
		System.out.println("/ChordUI/src/main/resources/fxml/FXMLController.fxml");
		System.out.println(FXMLController.class);
		System.out.println(var);
		
		
		return FXMLLoader
				.load(new URL(FXMLController.class.getResource("FXMLController.fxml").toExternalForm()));
	}

}
