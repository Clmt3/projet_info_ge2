package com.insa.projet_info.projet_ge2_2023.controleur;

import com.insa.projet_info.projet_ge2_2023.modele.Database;
import com.insa.projet_info.projet_ge2_2023.modele.TypeOperation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GestionTypesOperationsController implements Initializable {

    @FXML
    private TableView<TypeOperation> tableTypesOperations;

    @FXML
    private TableColumn<TypeOperation, Integer> id;

    @FXML
    private TableColumn<TypeOperation, String> description;

    @FXML
    private TextField descriptionField;

    @FXML
    private Button modifierButton;

    @FXML
    private Button supprimerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        modifierButton.setDisable(true);
        supprimerButton.setDisable(true);

        tableTypesOperations.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                modifierButton.setDisable(false);
                supprimerButton.setDisable(false);
            } else {
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
            }
        });

        rafraichirTableTypesOperations();
    }

    @FXML
    private void ajouterTypeOperation(ActionEvent event) {
        String description = descriptionField.getText();

        boolean ajoutReussi = Database.ajouterTypeOperation(description);

        if (ajoutReussi) {
            rafraichirTableTypesOperations();
            System.out.println("Type d'opération ajouté avec succès à la base de données.");
        } else {
            System.out.println("Échec de l'ajout du type d'opération à la base de données.");
        }

        descriptionField.clear();
    }

    @FXML
    private void modifierTypeOperation(ActionEvent event) {
        TypeOperation typeOperationSelectionne = tableTypesOperations.getSelectionModel().getSelectedItem();

        if (typeOperationSelectionne != null) {
            String nouvelleDescription = descriptionField.getText();

            boolean modificationReussie = Database.modifierTypeOperation(typeOperationSelectionne.getId(), nouvelleDescription);

            if (modificationReussie) {
                rafraichirTableTypesOperations();
                System.out.println("Type d'opération modifié avec succès dans la base de données.");
            } else {
                System.out.println("Échec de la modification du type d'opération dans la base de données.");
            }

            descriptionField.clear();
        }
    }

    @FXML
    private void supprimerTypeOperation(ActionEvent event) {
        TypeOperation typeOperationSelectionne = tableTypesOperations.getSelectionModel().getSelectedItem();

        if (typeOperationSelectionne != null) {
            boolean suppressionReussie = Database.supprimerTypeOperation(typeOperationSelectionne.getId());

            if (suppressionReussie) {
                rafraichirTableTypesOperations();
                System.out.println("Type d'opération supprimé avec succès de la base de données.");
            } else {
                System.out.println("Échec de la suppression du type d'opération de la base de données.");
            }
        }
    }

    private void rafraichirTableTypesOperations() {
        ObservableList<TypeOperation> typesOperations = tableTypesOperations.getItems();

        typesOperations.clear();

        List<TypeOperation> typesOperationsList = Database.getTypesOperations();

        typesOperations.addAll(typesOperationsList);
    }
}
