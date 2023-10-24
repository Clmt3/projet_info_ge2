package com.insa.projet_info.projet_ge2_2023.modele;

// Classe représentant le Nombre de Commandes
public class NombreCommande {
    private int id_produit;
    private int id_commande;
    private int nombre;

    public NombreCommande(int id_produit, int id_commande, int nombre) {
        this.id_produit = id_produit;
        this.id_commande = id_commande;
        this.nombre = nombre;
    }

    // Getters et setters pour les attributs

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
}