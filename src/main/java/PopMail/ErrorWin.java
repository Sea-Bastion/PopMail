package PopMail;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ResourceBundle;

public class ErrorWin {

	private Stage window;
	private String Title;
	private String Description;
	private FixHandler FixFunc;
	private boolean CanFix;
	
	ErrorWin(String Title, String Description){
		this(Title, Description, null);
	}

	ErrorWin(String Title, String Description, FixHandler FixFunc){

		this.Title = Title;
		this.Description = Description;
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
		void Kill(ActionEvent event){
			System.exit(1);
		}

		@FXML
		void Fix(ActionEvent event){
			final IntBuffer work = IntBuffer.allocate(1);
			new Thread(() ->{
				synchronized (work) {
					work.put(FixFunc.FixHandle()? 1:0);
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

			int workint = work.array()[0];
			switch (workint){
				case 0:
					Error2("Failure", "Failed to fix program please restart or contact Sebastian.Cypert@gmail.com");
					break;

				case 1:
					window.close();
					break;
			}


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
		}
	}
}
