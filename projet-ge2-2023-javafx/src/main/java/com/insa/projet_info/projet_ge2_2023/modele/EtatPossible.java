package com.insa.projet_info.projet_ge2_2023.modele;

// Classe représentant un État Possible
public class EtatPossible {
    private int id_etat_utilisateur;
    private String description;

    public EtatPossible(int id_etat_utilisateur, String description) {
        this.id_etat_utilisateur = id_etat_utilisateur;
        this.description = description;
    }

   

    public int getId_etat_utilisateur() {
        return id_etat_utilisateur;
    }

    public void setId_etat_utilisateur(int id_etat_utilisateur) {
        this.id_etat_utilisateur = id_etat_utilisateur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
