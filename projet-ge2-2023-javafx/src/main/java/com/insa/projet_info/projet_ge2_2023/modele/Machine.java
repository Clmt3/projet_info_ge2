package com.insa.projet_info.projet_ge2_2023.modele;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

// Classe représentant une Machine
public class Machine {
    private int id;
    private String reference;
    private String description;
    private int puissance;
    private List<TypeOperation> typesOperations;

    public Machine(int id, String reference, String description, int puissance) {
        this.id = id;
        this.reference = reference;
        this.description = description;
        this.puissance = puissance;
    }

    public List<TypeOperation> getTypesOperations() {
        return typesOperations;
    }

    public void setTypesOperations(List<TypeOperation> typesOperations) {
        this.typesOperations = typesOperations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    @Override
    public String toString() {
        return id + "# " + reference;
    }
}
