/*

	FileName:Main.java
	Author(s): Sebastian Cypert
	Job: It is the starting point of the program. It contains global variables and is responsible for opening the window

 */
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

import static PopMail.Controllers.Browser.LoadBrowser;
import static PopMail.Controllers.Login.LoadLogin;
import static PopMail.Mailing.Login;

public class Main extends Application {

	public static Session session;
	public static Store store;

	public void start(Stage stage) throws Exception {
		LoadLogin(stage);
		stage.show();
	}

	public static void main(String args[]){
		launch(args);
	}
}