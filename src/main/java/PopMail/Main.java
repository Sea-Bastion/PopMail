package PopMail;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    private Stage PrimaryStage;

    public void start(Stage stage) throws Exception {
        PrimaryStage = stage;

        try {
            URL LoginPath = getClass()./*getClassLoader().*/getResource("Login.fxml");
            if (LoginPath == null) throw new NullPointerException();
            Parent Login = FXMLLoader.load(LoginPath);
            PrimaryStage.setScene(new Scene(Login, 500, 600));

        }catch(Exception e){
            new ErrorWin("boop", "bop");
        }

        PrimaryStage.show();
    }

    public Stage getPrimaryStage() {
        return PrimaryStage;
    }

    public static void main(String args[]){
        launch(args);
    }
}