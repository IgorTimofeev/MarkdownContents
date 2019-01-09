package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.Collections;

public class Main extends Application {
	public TextArea fromTextArea, toTextArea;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
	    primaryStage.setScene(new Scene(parent));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void initialize() {
		fromTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
			toTextArea.setText("| Contents |\n| - |");
			
			for (String line : fromTextArea.getText().split("\n+")) {
				if (line.matches("^#+\\s.+")) {
					int indexOfSpace = line.indexOf(" ");
					int indexOfBrace = line.indexOf("(");
					
					toTextArea.setText(
						toTextArea.getText() + 
						"\n| [" +
						(indexOfSpace > 1 ? String.join("", Collections.nCopies(indexOfSpace - 1, " ")) : "") +
						(indexOfBrace > 0 ? line.substring(0, indexOfBrace).replace("*", "") : line).substring(indexOfSpace + 1) +
						"](#" +
						line.substring(indexOfSpace + 1).toLowerCase().replace(" ", "-").replaceAll("[^\\s\\-\\w]+", "") +
						") |"
					);
				}
			}
		});
	}
}
