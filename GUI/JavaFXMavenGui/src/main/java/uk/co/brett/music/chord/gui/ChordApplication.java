package uk.co.brett.music.chord.gui;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** Main application class for Chord Demo fxml demo application */
public class ChordApplication extends Application {
  public static void main(String[] args) { launch(args); }
  @Override public void start(Stage stage) throws IOException {
    stage.setTitle("Chords");
   
    AnchorPane layout = FXMLLoader.load(
      new URL(ChordApplication.class.getResource("ChordDemo.fxml").toExternalForm())
    );
    stage.setScene(new Scene(layout));
    stage.show();
  }
}