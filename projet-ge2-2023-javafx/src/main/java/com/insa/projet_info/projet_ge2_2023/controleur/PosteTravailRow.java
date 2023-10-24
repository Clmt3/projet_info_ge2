package com.insa.projet_info.projet_ge2_2023.controleur;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PosteTravailRow {
    private final SimpleStringProperty nomPrenom;
    private final SimpleStringProperty referenceMachine;
    private final SimpleIntegerProperty idOperation;
    private final SimpleStringProperty descriptionOperation;
    private final SimpleStringProperty referenceProduit;

    private int id;
    private int idUtilisateur;
    private int idMachine;

    public PosteTravailRow(String nomPrenom, String referenceMachine, int idOperation, String descriptionOperation, String referenceProduit) {
        this.nomPrenom = new SimpleStringProperty(nomPrenom);
        this.referenceMachine = new SimpleStringProperty(referenceMachine);
        this.idOperation = new SimpleIntegerProperty(idOperation);
        this.descriptionOperation = new SimpleStringProperty(descriptionOperation);
        this.referenceProduit = new SimpleStringProperty(referenceProduit);
    }

    public String getNomPrenom() {
        return nomPrenom.get();
    }

    public SimpleStringProperty nomPrenomProperty() {
        return nomPrenom;
    }

    public int getIdOperation() {
        return idOperation.get();
    }

    public SimpleIntegerProperty idOperationProperty() {
        return idOperation;
    }

    public String getDescriptionOperation() {
        return descriptionOperation.get();
    }

    public SimpleStringProperty descriptionOperationProperty() {
        return descriptionOperation;
    }

    public String getReferenceMachine() {
        return referenceMachine.get();
    }

    public SimpleStringProperty referenceMachine() {
        return referenceMachine;
    }

    public String getReferenceProduit() {
        return referenceProduit.get();
    }

    public SimpleStringProperty referenceProduit() {
        return referenceProduit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(int idMachine) {
        this.idMachine = idMachine;
    }
}
