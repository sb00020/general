package uk.co.temp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class TilePaneController implements Initializable {
	@FXML
	TilePane pane = new TilePane(); 
	
	@FXML
	Label scoreLabel = new Label();
	
	@FXML
	Button submitButton;
	
	QuestionController[] controllers;

	int numQuestions = 8;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

		controllers = new QuestionController[numQuestions];

		try {
			for (int questionNumber = 0; questionNumber < numQuestions; questionNumber++) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionController.fxml"));

				pane.getChildren().add(loader.load());

				controllers[questionNumber] = loader.getController();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		submitButton.setOnAction((event) -> {
			
			int sum =0;
			for (QuestionController question : controllers){
				
				question.disable();
				
				System.out.println(question.isCorrectAnswer());
				
				if (question.isCorrectAnswer()){
					
					sum ++;
				}
			}
			
			System.out.println("Score is " + sum);
			scoreLabel.setText("Score is " + sum + "/" + numQuestions);
			
		});
	}
}
