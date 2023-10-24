/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.insa.projet_info.projet_ge2_2023.controleur;

import com.insa.projet_info.projet_ge2_2023.modele.Database;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author clementmassotte
 */
public class CompteController implements Initializable {

    @FXML
    private Button bouton_valider;
    
    @FXML
    private Button bouton_retour;
    
    @FXML
    private TextField txt_email;
    
    @FXML
    private PasswordField txt_motdepasse; 
    
    @FXML
    private PasswordField txt_motdepasse_conf; 
    
    @FXML
    private TextField txt_prenom;
    
    @FXML
    private TextField txt_nom;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bouton_valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Connection connection = Database.getConnection();
                    
                    // Vérifiez les champs et ajoutez l'utilisateur à la base de données ici
                    if (!txt_motdepasse.getText().trim().isEmpty() && 
                        !txt_motdepasse_conf.getText().trim().isEmpty()&&
                        !txt_email.getText().trim().isEmpty() && 
                        !txt_nom.getText().trim().isEmpty() && 
                        !txt_prenom.getText().trim().isEmpty()) {
                        
                        //String mdp = txt_motdepasse.getText().trim();
                        //String mdp_conf = txt_motdepasse_conf.getText().trim();

                        
                        if (txt_motdepasse_conf.getText().trim().equals(txt_motdepasse.getText().trim())) {
                            Database.ajouterUtilisateur(event, txt_nom.getText(), txt_prenom.getText(), txt_email.getText(), txt_motdepasse.getText());
                        
                            //Lancez une alerte 
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Vous êtes ajouté!");
                            alert.show();
                        } else {
                            System.out.println("confirmation du mot de passe incorrecte!");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Votre mot de passe diffère de sa confirmation!");
                            alert.show();
                        }
                        
                        // Fermez la connexion après avoir effectué vos opérations sur la base de données
                        connection.close();
                    } else {
                        System.out.println("Veuillez remplir l'ensemble des informations!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Veuillez remplir toutes les informations pour continuer!");
                        alert.show();
                    }
                } catch (SQLException e) {
                    // Gérer les exceptions liées à la base de données ici
                    e.printStackTrace(); // C'est généralement une bonne pratique de journaliser l'exception au lieu de simplement imprimer la trace.
                }
            }
        });
    }

    @FXML
    private void bouton_retourAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Parent root = loader.load();
        LoginController loginController = loader.getController();

        Stage currentStage = (Stage) bouton_retour.getScene().getWindow();
        Scene newScene = new Scene(root);
        currentStage.setScene(newScene);
    }
}