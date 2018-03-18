package PopMail.Controllers;

import static PopMail.Mailing.*;
import static PopMail.Controllers.Browser.LoadBrowser;

import PopMail.Classes.ErrorWin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;

public class Login {

	@FXML
	StackPane Pane;
	@FXML
	CheckBox Remember;
	@FXML
	TextField UserInput;
	@FXML
	TextField PassInput;

	public static void LoadLogin(Stage window){
		URL LoginPath = Login.class.getClassLoader().getResource("Login.fxml");

		try {
			if (LoginPath == null) throw new NullPointerException();
			Parent Login = FXMLLoader.load(LoginPath);
			window.setScene(new Scene(Login));

		}catch(Exception e){
			new ErrorWin("Unable to load login window", "Missing file, please uninstall and reinstall program"+
					System.lineSeparator() + "if you still have problems contact Sebastian.cypert@gmail.com", false);
		}
	}

	@FXML
	public void LoginButton(ActionEvent event){

		if(UserInput.getText().isEmpty() || PassInput.getText().isEmpty())
			new ErrorWin("No Username or password",
					"please input both username and password before attempting to log in", true);
		else if(Login(UserInput.getText(), PassInput.getText())){
			LoadBrowser((Stage) Pane.getScene().getWindow());
		}

	}

}
