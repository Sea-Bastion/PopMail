package PopMail;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ResourceBundle;

public class ErrorWin {

    String Title;
    String Description;
    FixHandler FixFunc;
    boolean CanFix;

    ErrorWin(String Title, String Description, FixHandler FixFunc, boolean CanFix){

        this.Title = Title;
        this.Description = Description;
        this.FixFunc = FixFunc;
        this.CanFix = CanFix;

        Stage window = new Stage();
        window.setTitle("ERROR");

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
            System.exit(9999);
        }

    }

    public class controller implements Initializable{

        @FXML
        Label TitleLabel;
        @FXML
        Label DescriptionLabel;
        @FXML
        HBox Buttons;

        @FXML
        void Kill(ActionEvent event){
            System.exit(1);
        }

        @FXML
        void Fix(ActionEvent event){
            final IntBuffer work = IntBuffer.allocate(1);
            new Thread(() ->{
                synchronized (work) {
                    work.put(FixFunc.FixHandle());
                    work.notify();
                }
            }).start();

            Buttons.setVisible(false);


        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            TitleLabel.setText(Title);
            DescriptionLabel.setText(Description);
        }
    }
}
