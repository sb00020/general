package uk.co.brett.music.chord.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/** JavaFX fxml controller for Chord fxml demo application. */
public class ChordController implements Initializable {

	@FXML // fx:id="chordCombo"
	private ComboBox<String> chordCombo; // Value injected by FXMLLoader

	@FXML // fx:id="selectedChord"
	private Label selectedChord; // Value injected by FXMLLoader

	@FXML // fx:id="upButton"
	private Button upButton; // Value injected by FXMLLoader

	@FXML // fx:id="originButton"
	private Button originButton; // Value injected by FXMLLoader

	@FXML // fx:id="downButton"
	private Button downButton; // Value injected by FXMLLoader

	@Override // This method is called by the FXMLLoader when initialization is
				// complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		assert chordCombo != null : "fx:id=\"chordCombo\" was not injected: check your FXML file 'ChordDemo.fxml'.";
		assert selectedChord != null : "fx:id=\"selectedChord\" was not injected: check your FXML file 'ChordDemo.fxml'.";
		assert upButton != null : "fx:id=\"upButton\" was not injected: check your FXML file 'ChordDemo.fxml'.";
		assert originButton != null : "fx:id=\"originButton\" was not injected: check your FXML file 'ChordDemo.fxml'.";
		assert downButton != null : "fx:id=\"downButton\" was not injected: check your FXML file 'ChordDemo.fxml'.";

		upButton.setOnAction((event) -> {
			System.out.println("Up Button Action");
		});

		downButton.setOnAction((event) -> {
			System.out.println("Down Button Action");
		});

		originButton.setOnAction((event) -> {
			System.out.println("Origin Button Action");
		});

		chordCombo.getItems().addAll(EnumToStringArray(Chords.values()));

		// bind the selected chord label to the selected chord in the combo box.
		selectedChord.textProperty().bind(chordCombo.getSelectionModel().selectedItemProperty());

		// listen for changes to the chord combo box selection and update the
		// displayed chord image accordingly.
		chordCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> selected, String oldChord, String newChord) {

				System.out.println("You have change the selection from " + oldChord + " to " + newChord);

			}
		});
	}

	private String[] EnumToStringArray(Chords[] values) {

		String[] vals = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			vals[i] = values[i].toString();
		}

		return vals;
	}

	public enum Chords {
		I("I"), ii("ii"), iii("iii"), IV("IV"), V("V"), vi("vi"), vii("vii(o)");
		private final String text;

		private Chords(final String text) {
			this.text = text;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return text;
		}

	}

}