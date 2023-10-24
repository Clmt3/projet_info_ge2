package com.insa.projet_info.projet_ge2_2023.modele;

// Classe représentant une Opération
public class Operation {
    private int id_produit;
    private int id_type_operation;
    private int id;

    public Operation(int id_produit, int id_type_operation, int id) {
        this.id_produit = id_produit;
        this.id_type_operation = id_type_operation;
        this.id = id;
    }

   

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_type_operation() {
        return id_type_operation;
    }

    public void setId_type_operation(int id_type_operation) {
        this.id_type_operation = id_type_operation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}