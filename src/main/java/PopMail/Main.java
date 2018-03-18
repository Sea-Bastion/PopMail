package PopMail;

import PopMail.Classes.ErrorWin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.mail.Session;
import javax.mail.Store;
import java.net.URL;

import static PopMail.Controllers.Login.LoadLogin;

public class Main extends Application {

	static Session session;
	static Store store;

	public void start(Stage stage) throws Exception {
		LoadLogin(stage);
		stage.show();
	}

	public static void main(String args[]){
		launch(args);
	}
}