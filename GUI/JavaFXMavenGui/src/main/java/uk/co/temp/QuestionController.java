package uk.co.temp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

	public class QuestionController implements Initializable {
	
		@FXML
		private ComboBox<String> chordCombo;
	
		@FXML
		private Label questionField;
	
		@FXML
		private Button submitButton;
	
		@FXML
		private ToggleGroup answerGroup;
	
		@FXML
		private RadioButton toggle0, toggle1, toggle2, toggle3, toggle4;
	
		@Override
		public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
	
			submitButton.setOnAction((event) -> {
				System.out.println("Submit Logic");
			});
	
			answerGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
					if (answerGroup.getSelectedToggle() != null) {
						System.out.println(answerGroup.getSelectedToggle().toString());
					}
				}
			});
	
		}
	
	}