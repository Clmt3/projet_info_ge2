package com.insa.projet_info.projet_ge2_2023.controleur;

import com.insa.projet_info.projet_ge2_2023.modele.Database;
import com.insa.projet_info.projet_ge2_2023.modele.Produit;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GestionProduitsController implements Initializable {

    @FXML
    private TableView<Produit> tableProduits;

    @FXML
    private TableColumn<Produit, Integer> id;

    @FXML
    private TableColumn<Produit, String> reference;

    @FXML
    private TableColumn<Produit, String> description;

    @FXML
    private TextField referenceField;

    @FXML
    private TextField descriptionField;

    @FXML
    private Button modifierButton;

    @FXML
    private Button supprimerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        reference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableProduits.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                referenceField.setText(newSelection.getReference());
                descriptionField.setText(newSelection.getDescription());

                modifierButton.setDisable(false);
                supprimerButton.setDisable(false);
            } else {
                referenceField.clear();
                descriptionField.clear();

                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
            }
        });

        rafraichirTableProduits();
    }

    @FXML
    private void ajouterProduit(ActionEvent event) {
        String reference = referenceField.getText();
        String description = descriptionField.getText();

        boolean ajoutReussi = Database.ajouterProduit(reference, description);

        if (ajoutReussi) {
            rafraichirTableProduits();
            System.out.println("Produit ajouté avec succès à la base de données.");
        } else {
            System.out.println("Échec de l'ajout du produit à la base de données.");
        }

        referenceField.clear();
        descriptionField.clear();
        rafraichirTableProduits();
    }

    @FXML
    private void modifierProduit(ActionEvent event) {
        Produit produitSelectionne = tableProduits.getSelectionModel().getSelectedItem();

        if (produitSelectionne != null) {
            String nouvelleReference = referenceField.getText();
            String nouvelleDescription = descriptionField.getText();

            boolean modificationReussie = Database.modifierProduit(produitSelectionne.getId(), nouvelleReference, nouvelleDescription);

            if (modificationReussie) {
                rafraichirTableProduits();
                System.out.println("Produit modifié avec succès dans la base de données.");
            } else {
                System.out.println("Échec de la modification du produit dans la base de données.");
            }

            referenceField.clear();
            descriptionField.clear();
            rafraichirTableProduits();
        }
    }

    @FXML
    private void supprimerProduit(ActionEvent event) {
        Produit produitSelectionne = tableProduits.getSelectionModel().getSelectedItem();

        if (produitSelectionne != null) {
            boolean suppressionReussie = Database.supprimerProduit(produitSelectionne.getId());

            if (suppressionReussie) {
                rafraichirTableProduits();
                System.out.println("Produit supprimé avec succès de la base de données.");
            } else {
                System.out.println("Échec de la suppression du produit de la base de données.");
            }
        }
    }

    private void rafraichirTableProduits() {
        ObservableList<Produit> produits = tableProduits.getItems();

        produits.clear();

        List<Produit> produitsList = Database.getProduits();

        produits.addAll(produitsList);
    }
}
