package com.insa.projet_info.projet_ge2_2023.controleur;

import com.insa.projet_info.projet_ge2_2023.modele.Database;
import com.insa.projet_info.projet_ge2_2023.modele.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GestionOperateursController implements Initializable {

    @FXML
    private TableView<Utilisateur> tableOperateurs;

    @FXML
    private TableColumn<Utilisateur, Integer> id;

    @FXML
    private TableColumn<Utilisateur, String> nom;

    @FXML
    private TableColumn<Utilisateur, String> prenom;

    @FXML
    private TableColumn<Utilisateur, String> email;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;
    @FXML
    private Button modifierOperateurButton;

    @FXML
    private Button supprimerOperateurButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableOperateurs.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                modifierOperateurButton.setDisable(false);
                supprimerOperateurButton.setDisable(false);

                Utilisateur operateurSelectionne = newSelection;
                nomField.setText(operateurSelectionne.getNom());
                prenomField.setText(operateurSelectionne.getPrenom());
                emailField.setText(operateurSelectionne.getEmail());
            } else {
                modifierOperateurButton.setDisable(true);
                supprimerOperateurButton.setDisable(true);
            }
        });

        rafraichirTableOperateurs();
    }

    // Méthode pour ajouter un opérateur
    @FXML
    private void ajouterOperateur(ActionEvent event) {
        // Récupérer les données des champs
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String motdepasse = passwordField.getText(); // Récupérer le mot de passe

        // Valider et ajouter l'opérateur à la base de données
        boolean ajoutReussi = Database.ajouterUtilisateur(event, nom, prenom, email, motdepasse);

        if (ajoutReussi) {
            // Rafraîchir la table des opérateurs
            rafraichirTableOperateurs();
            System.out.println("Opérateur ajouté avec succès à la base de données.");
        } else {
            System.out.println("Échec de l'ajout de l'opérateur à la base de données.");
        }
    }


    @FXML
    private void modifierOperateur(ActionEvent event) {
        // Récupérer l'opérateur sélectionné dans la table
        Utilisateur operateurSelectionne = tableOperateurs.getSelectionModel().getSelectedItem();

        if (operateurSelectionne != null) {
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String email = emailField.getText();
            String motdepasse = passwordField.getText();

            boolean modificationReussie = Database.modifierOperateur(operateurSelectionne.getId(), nom, prenom, email, motdepasse);

            if (modificationReussie) {
                rafraichirTableOperateurs();
                System.out.println("Opérateur modifié avec succès dans la base de données.");
            } else {
                System.out.println("Échec de la modification de l'opérateur dans la base de données.");
            }

            nomField.clear();
            prenomField.clear();
            emailField.clear();
            passwordField.clear();
        }
    }

    @FXML
    private void supprimerOperateur(ActionEvent event) {
        // Récupérer l'opérateur sélectionné dans la table
        Utilisateur operateurSelectionne = tableOperateurs.getSelectionModel().getSelectedItem();

        if (operateurSelectionne != null) {
            boolean suppressionReussie = Database.supprimerOperateur(operateurSelectionne.getId());

            if (suppressionReussie) {
                rafraichirTableOperateurs();
                System.out.println("Opérateur supprimé avec succès de la base de données.");
            } else {
                System.out.println("Échec de la suppression de l'opérateur de la base de données.");
            }
        } else {
            System.out.println("Veuillez sélectionner un opérateur à supprimer.");
        }
    }

    // Méthode pour rafraîchir la table des opérateurs
    private void rafraichirTableOperateurs() {
        tableOperateurs.getItems().clear();

        List<Utilisateur> utilisateurs = Database.getUtilisateurs();
        System.out.println(utilisateurs.get(0).getNom());

        tableOperateurs.getItems().addAll(utilisateurs);
    }

}
