package com.insa.projet_info.projet_ge2_2023.modele;

// Classe représentant un Type d'Opération
public class TypeOperation {
    private int id;
    private String description;

    public TypeOperation(int id, String description) {
        this.id = id;
        this.description = description;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

}
