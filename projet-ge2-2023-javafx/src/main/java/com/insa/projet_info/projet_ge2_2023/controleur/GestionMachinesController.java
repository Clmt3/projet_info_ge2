package com.insa.projet_info.projet_ge2_2023.controleur;

import com.insa.projet_info.projet_ge2_2023.modele.Database;
import com.insa.projet_info.projet_ge2_2023.modele.Machine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GestionMachinesController implements Initializable {

    @FXML
    private TableView<Machine> tableMachines;

    @FXML
    private TableColumn<Machine, Integer> id;

    @FXML
    private TableColumn<Machine, String> reference;

    @FXML
    private TableColumn<Machine, String> description;

    @FXML
    private TableColumn<Machine, Integer> puissance; // Ajouter une colonne pour la puissance

    @FXML
    private TextField referenceMachineField; // Champ pour la référence de la machine

    @FXML
    private TextField descriptionMachineField; // Champ pour la description de la machine

    @FXML
    private TextField puissanceMachineField; // Ajouter un champ pour la puissance

    @FXML
    private Button modifierButton; // Ajout du bouton "Modifier Machine"

    @FXML
    private Button supprimerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialisation de la table avec les colonnes
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        reference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        puissance.setCellValueFactory(new PropertyValueFactory<>("puissance"));

        // Écouteur de sélection de ligne dans la table
        tableMachines.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                supprimerButton.setDisable(false);
                modifierButton.setDisable(false); // Activer le bouton "Modifier Machine"

                // Remplir les champs du formulaire avec les valeurs de la machine sélectionnée
                Machine machineSelectionnee = newSelection;
                referenceMachineField.setText(machineSelectionnee.getReference());
                descriptionMachineField.setText(machineSelectionnee.getDescription());
                puissanceMachineField.setText(Integer.toString(machineSelectionnee.getPuissance()));
            } else {
                supprimerButton.setDisable(true);
                modifierButton.setDisable(true);
            }
        });

        rafraichirTableMachines();
    }


    @FXML
    private void ajouterMachine(ActionEvent event) {
        // Récupérer les données des champs
        String reference = referenceMachineField.getText();
        String description = descriptionMachineField.getText();
        int puissance = Integer.parseInt(puissanceMachineField.getText());

        // ajouter la machine à la base de données
        boolean ajoutReussi = Database.ajouterMachine(reference, description, puissance);

        if (ajoutReussi) {
            rafraichirTableMachines();
            System.out.println("Machine ajoutée avec succès à la base de données.");
        } else {
            System.out.println("Échec de l'ajout de la machine à la base de données.");
        }

        // Réinitialiser les champs
        referenceMachineField.clear();
        descriptionMachineField.clear();
        puissanceMachineField.clear();
    }

    @FXML
    private void modifierMachine(ActionEvent event) {
        Machine machineSelectionnee = tableMachines.getSelectionModel().getSelectedItem();

        if (machineSelectionnee != null) {
            // Récupérer les données des champs du formulaire
            String reference = referenceMachineField.getText();
            String description = descriptionMachineField.getText();

            int puissance = Integer.parseInt(puissanceMachineField.getText());

            boolean modificationReussie = Database.modifierMachine(machineSelectionnee.getId(), reference, description, puissance);

            if (modificationReussie) {
                rafraichirTableMachines();
                System.out.println("Machine modifiée avec succès dans la base de données.");
            } else {
                System.out.println("Échec de la modification de la machine dans la base de données.");
            }

            referenceMachineField.clear();
            descriptionMachineField.clear();
            puissanceMachineField.clear();
        }
    }

    @FXML
    private void supprimerMachine(ActionEvent event) {
        Machine machineSelectionnee = tableMachines.getSelectionModel().getSelectedItem();

        if (machineSelectionnee != null) {
            boolean suppressionReussie = Database.supprimerMachine(machineSelectionnee.getId());

            if (suppressionReussie) {
                rafraichirTableMachines();
                System.out.println("Machine supprimée avec succès de la base de données.");
            } else {
                System.out.println("Échec de la suppression de la machine de la base de données.");
            }
        } else {
            System.out.println("Veuillez sélectionner une machine à supprimer.");
        }
    }

    private void rafraichirTableMachines() {
        tableMachines.getItems().clear();

        List<Machine> machines = Database.getMachines();

        tableMachines.getItems().addAll(machines);
    }
}
