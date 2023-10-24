package com.insa.projet_info.projet_ge2_2023.modele;

import java.util.List;

public class Produit {
    private int id;
    private String reference;
    private String description;
    private List<Operation> planFabrication;

    public Produit(int id, String reference, String description) {
        this.id = id;
        this.reference = reference;
        this.description = description;
    }

    // Getters et setters pour les attributs

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

    public List<Operation> getPlanFabrication() {
        return planFabrication;
    }

    public void setPlanFabrication(List<Operation> planFabrication) {
        this.planFabrication = planFabrication;
    }

    @Override
    public String toString() {
        return reference;
    }
}
