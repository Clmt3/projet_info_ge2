package com.insa.projet_info.projet_ge2_2023.controleur;

import com.insa.projet_info.projet_ge2_2023.modele.Database;
import com.insa.projet_info.projet_ge2_2023.modele.Produit;
import com.insa.projet_info.projet_ge2_2023.modele.TypeOperation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PlanFabricationController implements Initializable {

    @FXML
    private ComboBox<Produit> produitComboBox;

    @FXML
    private ComboBox<TypeOperation> typeOperationComboBox;

    @FXML
    private TableView<OperationRow> tableOperations;

    @FXML
    private TableColumn<OperationRow, Integer> idOperation;

    @FXML
    private TableColumn<OperationRow, String> referenceProduit;

    @FXML
    private TableColumn<OperationRow, String> descriptionTypeOperation;

    @FXML
    private Button supprimerOperationButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialiser les ComboBox avec les données depuis la base de données
        ObservableList<Produit> produits = FXCollections.observableArrayList(Database.getProduits());
        ObservableList<TypeOperation> typesOperations = FXCollections.observableArrayList(Database.getTypesOperations());

        produitComboBox.setItems(produits);
        typeOperationComboBox.setItems(typesOperations);

        supprimerOperationButton.setDisable(true);

        idOperation.setCellValueFactory(new PropertyValueFactory<>("idOperation"));
        referenceProduit.setCellValueFactory(new PropertyValueFactory<>("referenceProduit"));
        descriptionTypeOperation.setCellValueFactory(new PropertyValueFactory<>("descriptionTypeOperation"));

        tableOperations.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                supprimerOperationButton.setDisable(false);
            } else {
                supprimerOperationButton.setDisable(true);
            }
        });

        rafraichirTableOperations();
    }

    @FXML
    private void ajouterOperation(ActionEvent event) {
        Produit produit = produitComboBox.getValue();
        TypeOperation typeOperation = typeOperationComboBox.getValue();

        if(produit != null && typeOperation != null) {
            boolean ajoutReussi = Database.ajouterOperation(produit.getId(), typeOperation.getId());

            if (ajoutReussi) {
                rafraichirTableOperations();
                System.out.println("Opération ajoutée avec succès à la base de données.");
            } else {
                System.out.println("Échec de l'ajout de l'opération à la base de données.");
            }
        }
    }

    @FXML
    private void supprimerOperation(ActionEvent event) {
        // Récupérer l'opération sélectionnée dans la table
        OperationRow operationSelectionnee = tableOperations.getSelectionModel().getSelectedItem();

        if (operationSelectionnee != null) {
            // Appeler la méthode pour supprimer l'opération de la base de données
            boolean suppressionReussie = Database.supprimerOperation(operationSelectionnee.getIdOperation());

            if (suppressionReussie) {
                // Rafraîchir la table des opérations
                rafraichirTableOperations();
                System.out.println("Opération supprimée avec succès de la base de données.");
            } else {
                System.out.println("Échec de la suppression de l'opération de la base de données.");
            }
        }
    }

    private void rafraichirTableOperations() {
        ObservableList<OperationRow> operations = tableOperations.getItems();

        operations.clear();

        ObservableList<OperationRow> operationsList = Database.getOperations();

        operations.addAll(operationsList);
        System.out.println(operations.get(0).getDescriptionTypeOperation());
    }
}
