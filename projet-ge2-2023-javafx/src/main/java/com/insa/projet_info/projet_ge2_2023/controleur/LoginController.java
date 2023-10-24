package com.insa.projet_info.projet_ge2_2023.controleur;

import com.insa.projet_info.projet_ge2_2023.modele.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button connectButton;

    @FXML
    private Button accountButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Action pour le bouton de connexion
        connectButton.setOnAction(event -> {
            String email = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            try (Connection connection = Database.getConnection()) {
                // Utilisez la connexion pour exécuter des requêtes SQL et vérifier les informations d'identification de l'utilisateur ici
                
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT motdepasse FROM utilisateur WHERE email = ?");
                preparedStatement.setString(1, email); // Utilisez l'index 1 pour le premier paramètre
                ResultSet resultSet = preparedStatement.executeQuery(); 
                
                if(!resultSet.isBeforeFirst()) {
                    System.out.println("l'utilisateur n'a pas été trouvé dans la base de données!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("les informations sont incorrectes!");
                    alert.show();
                 } else { 
                    while (resultSet.next()) {
                        
                        String retrievedMotdepasse = resultSet.getString("motdepasse");
                        
                        // Si l'utilisateur est authentifié, ouvrez la nouvelle fenêtre Accueil
                        if(retrievedMotdepasse.equals(password)){
                            

                            try { 
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/accueil.fxml"));
                                Parent root = loader.load();
                                AccueilController accueilControleur = loader.getController();

                                Stage currentStage = (Stage) connectButton.getScene().getWindow();
                                Scene newScene = new Scene(root);
                                currentStage.setScene(newScene);
                            } catch (IOException e) {
                                 e.printStackTrace();
                                 showAlert(Alert.AlertType.ERROR, "Erreur de chargement", "Erreur lors du chargement de la page de création Accueil.");
                            }
 
                            
                        } else {
                            System.out.println("le mot de passe ne correspond pas!");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("les informations sont incorrectes!");
                            alert.show();
                        } //1:03:08 https://www.youtube.com/watch?v=ltX5AtW9v30&t=313s&ab_channel=WittCode
                    
                }
                
                
                
                }

            } catch (SQLException e) {
                // Gérez les erreurs liées à la base de données ici
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erreur de connexion", "Erreur lors de la connexion à la base de données.");
            }
        });

        // Action pour le bouton de création de compte
        accountButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/compte.fxml"));
                Parent root = loader.load();
                CompteController compteController = loader.getController();

                Stage currentStage = (Stage) accountButton.getScene().getWindow();
                Scene newScene = new Scene(root);
                currentStage.setScene(newScene);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erreur de chargement", "Erreur lors du chargement de la page de création de compte.");
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

/*

package com.insa.projet_info.projet_ge2_2023.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button connectButton;
    
    @FXML
    private Button accountButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameField.setText("admin");
        passwordField.setText("admin");
    }

        @FXML
    private void loginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("admin".equals(username) && "admin".equals(password)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/acceuil.fxml"));
                Parent root = loader.load();
                AcceuilControleur acceuilController = loader.getController();

                Stage currentStage = (Stage) connectButton.getScene().getWindow();
                Scene newScene = new Scene(root);
                currentStage.setScene(newScene);


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Authentification impossible!");
        }
    }
       @FXML
    private void createAccountButtonAction(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/compte.fxml"));
         Parent root = loader.load();
         CompteController compteController = loader.getController();
         
         Stage currentStage = (Stage) accountButton.getScene().getWindow();
         Scene newScene = new Scene(root);
         currentStage.setScene(newScene);
    
    }

}



*/