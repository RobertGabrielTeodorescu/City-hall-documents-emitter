package start;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationStart extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/frames/login-frame.fxml"));
        Parent root=loader.load();
        primaryStage.setScene(new Scene(root,680,400));
        primaryStage.setTitle("Sign Up & Login");
        primaryStage.show();
    }

    public static void main(String[] args) {

       launch(args);

    }
}
