package com.insa.projet_info.projet_ge2_2023;

import com.insa.projet_info.projet_ge2_2023.controleur.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Badaoui Ali—Massotte Clément—Sylvain Lisounda");
        primaryStage.setScene(new Scene(root, 420, 280));

        LoginController controller = loader.getController();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

