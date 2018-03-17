package PopMail.Controllers;

import static PopMail.Mailing.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;

public class Login {

	@FXML
	CheckBox Remember;
	@FXML
	TextField UserInput;
	@FXML
	TextField PassInput;

	@FXML
	public void LoginButton(ActionEvent event){
		Login(UserInput.getText(), PassInput.getText());
		System.out.println("Logged in");
	}

}
