package com.insa.projet_info.projet_ge2_2023.controleur;

import com.insa.projet_info.projet_ge2_2023.modele.Database;

import com.insa.projet_info.projet_ge2_2023.modele.EtatMachine;
import com.insa.projet_info.projet_ge2_2023.modele.Machine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

public class DemarrerFabricationController implements Initializable {

    @FXML
    private TableView<PosteTravailRowEtat> posteTravailTableView;

    @FXML
    private TableColumn<PosteTravailRowEtat, String> nomPrenomColumn;

    @FXML
    private TableColumn<PosteTravailRowEtat, String> referenceMachineColumn;

    @FXML
    private TableColumn<PosteTravailRowEtat, Integer> idOperationColumn;

    @FXML
    private TableColumn<PosteTravailRowEtat, String> descriptionOperationColumn;

    @FXML
    private TableColumn<PosteTravailRowEtat, String> referenceProduitColumn;

    @FXML
    private TableColumn<PosteTravailRowEtat, Timestamp> debutColumn;

    @FXML
    private TableColumn<PosteTravailRowEtat, Timestamp> finColumn;
    @FXML
    private TableColumn<PosteTravailRowEtat, String> etatColumn;

    private ObservableList<PosteTravailRowEtat> posteTravailEtatRows = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Peupler posteTravailTableView avec les données de Database.listePosteTravailRowEtats()
        List<PosteTravailRowEtat> posteTravailRowEtats = Database.listePosteTravailRowEtats();
        posteTravailTableView.setItems(FXCollections.observableArrayList(posteTravailRowEtats));

        // Créez des colonnes et associez-les aux propriétés appropriées
        nomPrenomColumn.setCellValueFactory(new PropertyValueFactory<>("nomPrenom"));

        referenceMachineColumn.setCellValueFactory(new PropertyValueFactory<>("referenceMachine"));

        idOperationColumn.setCellValueFactory(new PropertyValueFactory<>("idOperation"));

        descriptionOperationColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionOperation"));

        referenceProduitColumn.setCellValueFactory(new PropertyValueFactory<>("referenceProduit"));

        debutColumn.setCellValueFactory(new PropertyValueFactory<>("debut"));

        finColumn.setCellValueFactory(new PropertyValueFactory<>("fin"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));

    }

    @FXML
    private void demarrerFabrication(ActionEvent event) {
        PosteTravailRowEtat selectedRow = posteTravailTableView.getSelectionModel().getSelectedItem();

        if (selectedRow != null) {
            Timestamp debut = new Timestamp(System.currentTimeMillis());

            Machine machine = Database.getMachine(selectedRow.getReferenceMachine());
            int puissanceMachine = machine.getPuissance();
            long duree = (3600 / puissanceMachine) * 1000 * 24; // en milliseconds
            Timestamp fin = new Timestamp(debut.getTime() + duree);

            Database.ajouterEtatMachine(new EtatMachine(machine.getId(), debut, fin));
            selectedRow.setDebut(debut);
            selectedRow.setFin(fin);

            posteTravailTableView.refresh();

            afficherMessageConfirmation("Fabrication démarrée avec succès !");
            List<PosteTravailRowEtat> posteTravailRowEtats = Database.listePosteTravailRowEtats();
            posteTravailTableView.setItems(FXCollections.observableArrayList(posteTravailRowEtats));
        } else {
            afficherMessageErreur("Veuillez sélectionner une ligne pour démarrer la fabrication.");
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
}
