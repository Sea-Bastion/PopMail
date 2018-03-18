/*

	FileName: Browser.java
	Author(s): Sebastian Cypert
	Jobs: Loads in and controlls the Email browser

 */
package PopMail.Controllers;

import PopMail.Classes.EmailBox;
import PopMail.Classes.ErrorWin;
import PopMail.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.net.URL;
import java.util.ResourceBundle;

public class Browser implements Initializable {

	@FXML
	VBox SelectorBox;
	@FXML
	StackPane EmailView;


	Folder CurrentFolder;

	public static void LoadBrowser(Stage window){
		URL BrowserPath = Browser.class.getClassLoader().getResource("Browser.fxml");

		try{
			if(BrowserPath == null) throw new NullPointerException();
			window.setScene(new Scene(FXMLLoader.load(BrowserPath),900 ,506.25));

		}catch(Exception e){
			e.printStackTrace();
			new ErrorWin("Unable to load Browser window", "Missing file, please uninstall and reinstall program"+
					System.lineSeparator() + "if you still have problems contact Sebastian.cypert@gmail.com", false);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			CurrentFolder = Main.store.getFolder("Inbox");

		}catch(MessagingException e){

			try {
				CurrentFolder = Main.store.getDefaultFolder().list()[0];

			}catch(MessagingException x){
				x.printStackTrace();
				new ErrorWin("Failed to find Default folder",
						"this is most likely to happen if you're Email is set up very strangely please set default folder",
						true);
				return;
			}
		}


		try {
			CurrentFolder.open(Folder.READ_WRITE);

		}catch(MessagingException e){

			try {
				CurrentFolder.open(Folder.READ_ONLY);

			}catch(MessagingException x){
				x.printStackTrace();
				new ErrorWin("Failed to open Email Folder",
						"cannot open Email folder for unknown reason", true);
				return;
			}
		}


		try {
			for (Message m : CurrentFolder.getMessages()) {

				try {
					SelectorBox.getChildren().add(new EmailBox(m));
				} catch (MessagingException e) {
					e.printStackTrace();
					new ErrorWin("Failed to Load Email",
							"unknown problem happened when loading Email", true);
				}
			}
		}catch(MessagingException e){
			e.printStackTrace();
			new ErrorWin("Failed to Load any messages",
					"program cannot load messages from current folder for unknown reason", true);
		}
	}
}
