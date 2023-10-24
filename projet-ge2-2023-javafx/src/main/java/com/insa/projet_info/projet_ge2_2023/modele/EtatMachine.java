package com.insa.projet_info.projet_ge2_2023.modele;

import java.sql.Timestamp;

// Classe représentant un État de Machine
public class EtatMachine {
    private int id;
    private int id_machine;
    private Timestamp debut;
    private Timestamp fin;

    public EtatMachine(int id_machine, Timestamp debut, Timestamp fin) {
        this.id_machine = id_machine;
        this.debut = debut;
        this.fin = fin;
    }

    public EtatMachine(Timestamp debut, Timestamp fin) {
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

    public int getId_machine() {
        return id_machine;
    }

    public void setId_machine(int id_machine) {
        this.id_machine = id_machine;
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
