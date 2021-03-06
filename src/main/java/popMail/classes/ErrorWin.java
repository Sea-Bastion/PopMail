package popMail.classes;

import popMail.FixHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.IntBuffer;
import java.util.ResourceBundle;

public class ErrorWin {

	private Stage window;
	private String Title;
	private String Description;
	private FixHandler FixFunc;
	private boolean CanContinue;
	
	public ErrorWin(String Title, String Description, boolean CanContinue){
		this(Title, Description, CanContinue, null);
	}

	public ErrorWin(String Title, String Description, boolean CanContinue, FixHandler FixFunc){

		this.Title = Title;
		this.Description = Description;
		this.CanContinue = CanContinue;
		this.FixFunc = FixFunc;

		window = new Stage();
		window.setTitle("ERROR");
		window.setOnCloseRequest(e -> System.exit(1));
		window.setResizable(false);
		window.setAlwaysOnTop(true);

		try {
			URL ErrorPath = getClass().getClassLoader().getResource("ErrorWin.fxml");
			if (ErrorPath == null) throw new NullPointerException();
			FXMLLoader ErrorScene = new FXMLLoader(ErrorPath);
			ErrorScene.setController(new controller());
			window.setScene(new Scene(ErrorScene.load()));
			window.showAndWait();

		}catch(Exception e){
			System.err.println("The Error popup encountered an error LMAO");
			e.printStackTrace();
			System.exit(420);
		}

	}

	public class controller implements Initializable{

		@FXML
		Label TitleLabel;
		@FXML
		Label DescriptionLabel;
		@FXML
		Button FixButton;
		@FXML
		Button KillButton;
		@FXML
		Button ContinueButton;

		@FXML
		void Kill(ActionEvent event){
			System.exit(1);
		}

		@FXML
		void Continue(ActionEvent event){
			window.close();
		}

		@FXML
		void Fix(ActionEvent event){
			final IntBuffer work = IntBuffer.allocate(1);
			new Thread(() ->{
				synchronized (work) {
					work.put(FixFunc.FixHandle(TitleLabel, DescriptionLabel)? 1:0);
					work.notifyAll();
				}
			}).start();


			FixButton.setDisable(true);
			KillButton.setDisable(true);

			synchronized (work) {
				try {
					if (work.position() == 0) {
						work.wait();
					}

				}catch (InterruptedException e){
					Error2("Interrupted", "Interrupted while trying to fix the program" + System.lineSeparator() +
						System.lineSeparator() + e.getMessage());
				}
			}

			if(work.array()[0] == 1) window.close();

		}

		void Error2(String Title, String Description){
			TitleLabel.setText(Title);
			DescriptionLabel.setText(Description);
			FixButton.setVisible(false);
			KillButton.setDisable(false);
		}

		@Override
		public void initialize(URL url, ResourceBundle resourceBundle) {
			TitleLabel.setText(Title);
			DescriptionLabel.setText(Description);

			if(FixFunc != null) FixButton.setVisible(true);
			if(CanContinue) {
				ContinueButton.setVisible(true);
				KillButton.setVisible(false);
			}
		}
	}
}
