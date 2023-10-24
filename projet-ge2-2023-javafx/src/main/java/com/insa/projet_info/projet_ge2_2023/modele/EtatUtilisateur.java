package com.insa.projet_info.projet_ge2_2023.modele;

import java.sql.Timestamp;

// Classe représentant un État de l'Utilisateur
public class EtatUtilisateur {
    private int id;
    private int id_utilisateur;
    private Timestamp debut;
    private Timestamp fin;

    public EtatUtilisateur(int id, int id_utilisateur, Timestamp debut, Timestamp fin) {
        this.id = id;
        this.id_utilisateur = id_utilisateur;
        this.debut = debut;
        this.fin = fin;
    }

    // Getters et setters pour les attributs

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Timestamp getDebut() {
        return debut;
    }

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }

    public Timestamp getFin() {
        return fin;
    }

    public void setFin(Timestamp fin) {
        this.fin = fin;
    }


}
