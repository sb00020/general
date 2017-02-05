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
		
		private boolean correctAnswer = false;
		
		private final Question question;
		
		public QuestionController(Question q){
			this.question = q;
		}
	
		@Override
		public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
	
			
			toggle0.setUserData(true);
			toggle1.setUserData(false);
			toggle2.setUserData(false);
			toggle3.setUserData(false);
			toggle4.setUserData(false);
			
			submitButton.setOnAction((event) -> {
				System.out.println("Submit Logic");
			});
	
			answerGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
					if (answerGroup.getSelectedToggle() != null) {
						System.out.println(answerGroup.getSelectedToggle().getUserData().toString());
						setCorrectAnswer((boolean)answerGroup.getSelectedToggle().getUserData());
						
					}
				}
			});
	
		}

		public boolean isCorrectAnswer() {
			return correctAnswer;
		}

		public void setCorrectAnswer(boolean correctAnswer) {
			System.out.println("Answer is " + correctAnswer);
			this.correctAnswer = correctAnswer;
		}

		public void disable() {
			toggle0.setDisable(true);
			toggle1.setDisable(true);
			toggle2.setDisable(true);
			toggle3.setDisable(true);
			toggle4.setDisable(true);
			submitButton.setDisable(true);
			
			if (correctAnswer){
				submitButton.setStyle("-fx-background-color:#90EE90");
			} else {
				submitButton.setStyle("-fx-background-color:#CC3232");
			}
			
		}
	
	}