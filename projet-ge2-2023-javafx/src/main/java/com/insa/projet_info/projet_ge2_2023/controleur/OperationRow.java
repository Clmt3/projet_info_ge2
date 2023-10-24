package com.insa.projet_info.projet_ge2_2023.controleur;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OperationRow {
    private final SimpleIntegerProperty idOperation;
    private final SimpleStringProperty referenceProduit;
    private final SimpleStringProperty descriptionTypeOperation;

    public OperationRow(int idOperation, String referenceProduit, String descriptionTypeOperation) {
        this.idOperation = new SimpleIntegerProperty(idOperation);
        this.referenceProduit = new SimpleStringProperty(referenceProduit);
        this.descriptionTypeOperation = new SimpleStringProperty(descriptionTypeOperation);
    }

    public int getIdOperation() {
        return idOperation.get();
    }

    public SimpleIntegerProperty idProperty() {
        return idOperation;
    }

    public String getReferenceProduit() {
        return referenceProduit.get();
    }

    public SimpleStringProperty referenceProduitProperty() {
        return referenceProduit;
    }

    public String getDescriptionTypeOperation() {
        return descriptionTypeOperation.get();
    }

    public SimpleStringProperty descriptionTypeOperationProperty() {
        return descriptionTypeOperation;
    }

    @Override
    public String toString() {
        return  "idOp=" + idOperation.get() + ", " + descriptionTypeOperation.get();
    }
}
