package com.insa.projet_info.projet_ge2_2023.modele;

// Classe représentant un PosteDeTravail
public class PosteDeTravail {
    private int id;
    private int id_utilisateur;
    private int id_machine;
    private String reference;
    private String description;

    public PosteDeTravail(int id, int id_utilisateur, int id_machine, String reference, String description) {
        this.id = id;
        this.id_utilisateur = id_utilisateur;
        this.id_machine = id_machine;
        this.reference = reference;
        this.description = description;
    }

   

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

    public int getId_machine() {
        return id_machine;
    }

    public void setId_machine(int id_machine) {
        this.id_machine = id_machine;
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
}