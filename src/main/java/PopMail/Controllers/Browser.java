package PopMail.Controllers;

import PopMail.Classes.ErrorWin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Browser {

	public static void LoadBrowser(Stage window){
		URL BrowserPath = Browser.class.getClassLoader().getResource("Browser.fxml");

		try{
			if(BrowserPath == null) throw new NullPointerException();
			window.setScene(new Scene(FXMLLoader.load(BrowserPath),900 ,506.25));

		}catch(Exception e){
			new ErrorWin("Unable to load Browser window", "Missing file, please uninstall and reinstall program"+
					System.lineSeparator() + "if you still have problems contact Sebastian.cypert@gmail.com", false);
		}
	}

}
