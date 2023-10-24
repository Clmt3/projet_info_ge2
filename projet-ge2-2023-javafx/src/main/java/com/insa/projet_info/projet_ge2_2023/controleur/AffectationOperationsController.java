package com.insa.projet_info.projet_ge2_2023.controleur;

import com.insa.projet_info.projet_ge2_2023.modele.Operation;
import com.insa.projet_info.projet_ge2_2023.modele.Utilisateur;
import com.insa.projet_info.projet_ge2_2023.modele.Produit;
import com.insa.projet_info.projet_ge2_2023.modele.Machine;
import com.insa.projet_info.projet_ge2_2023.modele.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AffectationOperationsController implements Initializable {

    @FXML
    private ComboBox<Produit> produitComboBox;

    @FXML
    private ComboBox<Utilisateur> operateurComboBox;

    @FXML
    private ComboBox<OperationRow> operationComboBox;

    @FXML
    private ComboBox<Machine> machineComboBox;

    @FXML
    private TableView<PosteTravailRow> posteTravailTableView;

    @FXML
    private TableColumn<PosteTravailRow, String> nomPrenomColumn;
  @FXML
    private TableColumn<PosteTravailRow, String> referenceMachineColumn;

    @FXML
    private TableColumn<PosteTravailRow, Integer> idOperationColumn;

    @FXML
    private TableColumn<PosteTravailRow, String> descriptionOperationColumn;
    @FXML
    private TableColumn<PosteTravailRow, String> referenceProduitColumn;

    private ObservableList<PosteTravailRow> posteTravailRows = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Remplir le ComboBox des opérateurs
        List<Utilisateur> operateurs = Database.getUtilisateurs();
        operateurComboBox.getItems().addAll(operateurs);

        // Remplir le ComboBox des produits en utilisant la méthode getListeProduits
        List<Produit> produits = Database.getListeProduits();
        produitComboBox.getItems().addAll(produits);

        // Remplir le ComboBox des machines non affectées
        List<Machine> machinesNonAffectees = Database.getMachinesNonAffectees();
        machineComboBox.getItems().addAll(machinesNonAffectees);

        produitComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                List<OperationRow> operationRows = Database.getOperations(newValue.getId());
                operationComboBox.getItems().setAll(operationRows);
            }
        });

        // Récupérez les données depuis la base de données en utilisant la méthode Database.listePosteTravails()
        posteTravailRows.addAll(Database.listePosteTravails());

        // Associez les colonnes aux propriétés de la classe PosteTravailRow
        nomPrenomColumn.setCellValueFactory(new PropertyValueFactory<>("nomPrenom"));
        referenceMachineColumn.setCellValueFactory(new PropertyValueFactory<>("referenceMachine"));
        idOperationColumn.setCellValueFactory(new PropertyValueFactory<>("idOperation"));
        descriptionOperationColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionOperation"));
        referenceProduitColumn.setCellValueFactory(new PropertyValueFactory<>("referenceProduit"));

        // Remplir la table avec les données
        posteTravailTableView.setItems(posteTravailRows);
    }

    @FXML
    private void affecterOperation(ActionEvent event) {
        Produit produitSelectionne = produitComboBox.getValue();
        Utilisateur operateurSelectionne = operateurComboBox.getValue();
        OperationRow operationSelectionnee = operationComboBox.getValue();
        Machine machineSelectionnee = machineComboBox.getValue();

        if (produitSelectionne != null && operateurSelectionne != null && operationSelectionnee != null && machineSelectionnee != null) {
            // Créez un poste de travail
            String referencePoste = "Poste de Travail num " + machineSelectionnee.getId();
            String descriptionPoste = "Poste Pour la machine " + machineSelectionnee.getReference();

            int idPosteDeTravail = Database.ajouterPosteDeTravail(
                    operateurSelectionne.getId(),
                    machineSelectionnee.getId(),
                    referencePoste,
                    descriptionPoste
            );

            // Ajoutez une entrée dans la table Utilise
            Database.ajouterUtilise(operateurSelectionne.getId(), idPosteDeTravail);

            // Ajoutez une entrée dans la table Realise
            Database.ajouterRealise(machineSelectionnee.getId(), operationSelectionnee.getIdOperation(), 0); // 0 pour statut "en attente"

            // Affichez un message de confirmation
            afficherMessageConfirmation("Opération affectée avec succès !");
            List<Machine> machinesNonAffectees = Database.getMachinesNonAffectees();
            machineComboBox.getItems().clear();
            machineComboBox.getItems().addAll(machinesNonAffectees);
            posteTravailRows.clear();

            // Récupérez les données depuis la base de données en utilisant la méthode Database.listePosteTravails()
            posteTravailRows.addAll(Database.listePosteTravails());
            posteTravailTableView.setItems(posteTravailRows);


        } else {
            // Affichez un message d'erreur si l'une des sélections est manquante
            afficherMessageErreur("Veuillez sélectionner un opérateur, un produit, une opération et une machine non affectée.");
        }
    }

    // Méthode pour afficher un message de confirmation
    private void afficherMessageConfirmation(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Méthode pour afficher un message d'erreur
    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void supprimerAffectation(ActionEvent actionEvent) {
        PosteTravailRow posteTravailSelectionne = posteTravailTableView.getSelectionModel().getSelectedItem();

        if (posteTravailSelectionne != null) {
            // Supprimez l'entrée dans la table Realise
            Database.supprimerRealise(posteTravailSelectionne.getIdMachine(), posteTravailSelectionne.getIdOperation());

            // Supprimez l'entrée dans la table Utilise
            Database.supprimerUtilise(posteTravailSelectionne.getIdUtilisateur(), posteTravailSelectionne.getId());

            // Supprimez l'entrée dans la table PosteDeTravail
            Database.supprimerPosteDeTravail(posteTravailSelectionne.getId());

            // Mettez à jour la ComboBox des machines après la suppression
            machineComboBox.getItems().setAll(Database.getMachinesNonAffectees());
            machineComboBox.getSelectionModel().select(0); // Sélectionnez le premier élément

            // Rafraîchissez la table des postes de travail
            posteTravailTableView.getItems().remove(posteTravailSelectionne);

            // Affichez un message de confirmation
            afficherMessageConfirmation("Affectation supprimée avec succès !");
        } else {
            // Affichez un message d'erreur si aucun poste de travail n'est sélectionné
            afficherMessageErreur("Veuillez sélectionner un poste de travail à supprimer.");
        }
    }


}
