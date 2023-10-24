package com.insa.projet_info.projet_ge2_2023.modele;

import java.util.ArrayList;
import java.util.List;

// Classe représentant un Utilisateur
public class Utilisateur {
    private int id;
    private String prenom;
    private String nom;
    private String email;
    private String motdepasse;

    private List<PosteDeTravail> postesDeTravail;

    public Utilisateur(int id, String prenom, String nom, String email, String motdepasse) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.motdepasse = motdepasse;
        this.postesDeTravail = new ArrayList<>();
    }

   


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public List<PosteDeTravail> getPostesDeTravail() {
        return postesDeTravail;
    }

    public void setPostesDeTravail(List<PosteDeTravail> postesDeTravail) {
        this.postesDeTravail = postesDeTravail;
    }

    public void addPosteDeTravail(PosteDeTravail posteDeTravail) {
        postesDeTravail.add(posteDeTravail);
    }

    public void removePosteDeTravail(PosteDeTravail posteDeTravail) {
        postesDeTravail.remove(posteDeTravail);
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }
}
