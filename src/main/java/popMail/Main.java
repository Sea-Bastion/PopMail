/*

	FileName:Main.java
	Author(s): Sebastian Cypert
	Job: It is the starting point of the program. It contains global variables and is responsible for opening the window

 */
package popMail;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.mail.Session;
import javax.mail.Store;

import static popMail.controllers.Browser.LoadBrowser;
import static popMail.Mailing.Login;
import static popMail.controllers.Login.LoadLogin;

public class Main extends Application {

	public static Session session;
	public static Store store;

	public void start(Stage stage) throws Exception {
		LoadLogin(stage);
		LoadBrowser(stage);
		stage.show();
	}

	public static void main(String args[]){
		launch(args);
	}
}