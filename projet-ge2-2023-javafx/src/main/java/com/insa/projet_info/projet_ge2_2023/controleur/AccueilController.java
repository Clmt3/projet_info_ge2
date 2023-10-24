package com.insa.projet_info.projet_ge2_2023.controleur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

public class AccueilController implements Initializable {

    @FXML
    private Button gestionOperateursButton;
    @FXML
    private Button gestionMachinesButton;
    @FXML
    private Button gestionTypesOperationsButton;
    @FXML
    private Button gestionProduitsButton;
    @FXML
    private Button planFabricationButton;
    @FXML
    private Button affectationOperationsButton;
    @FXML
    private Button fabricationProduitsButton;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    // Méthode générique pour ouvrir une nouvelle fenêtre
    private void ouvrirFenetre(String titre, String fichierFxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + fichierFxml));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titre);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour ouvrir la fenêtre de gestion des opérateurs
    @FXML
    private void ouvrirGestionOperateurs(ActionEvent event) {
        ouvrirFenetre("Gestion des Opérateurs", "GestionOperateurs.fxml");
    }

    // Méthode pour ouvrir la fenêtre de gestion des machines
    @FXML
    private void ouvrirGestionMachines(ActionEvent event) {
        ouvrirFenetre("Gestion des Machines", "GestionMachines.fxml");
    }

    // Méthode pour ouvrir la fenêtre de gestion des types d'opérations
    @FXML
    private void ouvrirGestionTypesOperations(ActionEvent event) {
        ouvrirFenetre("Gestion des Types d'Opérations", "GestionTypesOperations.fxml");
    }

    // Méthode pour ouvrir la fenêtre de gestion des produits
    @FXML
    private void ouvrirGestionProduits(ActionEvent event) {
        ouvrirFenetre("Gestion des Produits", "GestionProduits.fxml");
    }


    public void ouvrirPlanFabrication(ActionEvent actionEvent) {
        ouvrirFenetre("Plan de Fabrication", "PlanFabrication.fxml");
    }

    public void ouvrirAffectationOperations(ActionEvent actionEvent) {
        ouvrirFenetre("Affectation des Operations", "AffectationOperations.fxml");
    }

    public void ouvrirFabricationProduits(ActionEvent actionEvent) {
        ouvrirFenetre("Fabrication des Produits", "FabricationProduits.fxml");
    }


}
