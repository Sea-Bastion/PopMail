package PopMail;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

	private Stage PrimaryStage;

	public void start(Stage stage) throws Exception {
		PrimaryStage = stage;

		URL LoginPath = getClass().getClassLoader().getResource("Login.fxml");

		try {
			if (LoginPath == null) throw new NullPointerException();
			Parent Login = FXMLLoader.load(LoginPath);
			PrimaryStage.setScene(new Scene(Login));

		}catch(Exception e){
			new ErrorWin("Unable to load login", "Missing file, please uninstall and reinstall program"+
				System.lineSeparator() + "if you still have problems contact Sebastian.cypert@gmail.com");
		}

		PrimaryStage.show();
	}

	public Stage getPrimaryStage() {
		return PrimaryStage;
	}

	public static void main(String args[]){
		launch(args);
	}
}